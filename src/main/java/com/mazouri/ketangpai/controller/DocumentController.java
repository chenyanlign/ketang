package com.mazouri.ketangpai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.common.utils.UUIDUtils;
import com.mazouri.ketangpai.entity.Document;
import com.mazouri.ketangpai.service.DocumentService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
@RestController
@RequestMapping("/ketangpai/document")
@Slf4j
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    @ApiOperation(value = "添加文件信息到数据库")
    @PostMapping("/addFile")
    public R addFile(@RequestBody Document document) {
        return documentService.save(document) ? R.ok().data("docu", document) : R.error();
    }

    @ApiOperation(value = "删除资料")
    @PostMapping("/removeFile")
    public R removeFile(@RequestBody String[] documentId) {
        return documentService.removeByIds(Arrays.asList(documentId)) ? R.ok() : R.error();
    }

    @ApiOperation(value = "获取所有的文件")
    @GetMapping("/getAllFile/{courseId}")
    public R getAllFile(@PathVariable String courseId) {
        List<Document> documentList = documentService.list(new QueryWrapper<Document>().eq("course_id", courseId));
        return R.ok().data("documentList", documentList);
    }

    @ApiOperation(value = "上传文件到本地服务器")
    @PostMapping("/uploadFile/{documentId}")
    public R uploadFile(@RequestParam("file") MultipartFile multipartFile, @PathVariable String documentId, HttpServletRequest req) {
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/") + "/document" + format;
        System.out.println(realPath);
        File fileDir = new File(realPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        if (multipartFile != null) {
            //以原来的名称命名,覆盖掉旧的，这里也可以使用UUID之类的方式命名，这里就没有处理了
            String[] strings = multipartFile.getOriginalFilename().split("\\.");
            String fileType = strings[strings.length - 1];
            String fileName = UUIDUtils.getUUID() + multipartFile.getOriginalFilename();
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/document" + format + fileName;
            try {
                multipartFile.transferTo(new File(fileDir, fileName));
            } catch (IOException e) {
                e.printStackTrace();
                log.debug("transferTo出现异常");
            }

            documentService.updateById(new Document().setId(documentId).setFilePath(url)
                    .setFileName(multipartFile.getOriginalFilename()).setFileType(fileType));
            log.info("上传的文件：" + multipartFile.getName() + "," + multipartFile.getContentType() + "," + multipartFile.getOriginalFilename()
                    + "，保存的路径为：" + url);

            //前端可以通过状态码，判断文件是否上传成功
            return R.ok().data("path", url);
        } else {
            return R.ok().message("请检查文件是否有问题！！！");
        }
    }

}

