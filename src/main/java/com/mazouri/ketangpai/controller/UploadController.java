package com.mazouri.ketangpai.controller;

import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.common.utils.UUIDUtils;
import com.mazouri.ketangpai.entity.SubmitHomework;
import com.mazouri.ketangpai.service.SubmitHomeworkService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.mazouri.ketangpai.common.constant.ConstantValue.FILE_PATH;

/**
 * @author mazouri
 * @create 2021-06-14 10:54
 */
@RestController
@RequestMapping("/file")
public class UploadController {
    @Autowired
    private SubmitHomeworkService submitHomeworkService;
    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    @RequestMapping("/upload/{id}")
    public R uploadFile(@RequestParam("file") MultipartFile multipartFile, @PathVariable String id, HttpServletRequest req) throws IOException {
        String format = sdf.format(new Date());
       // getRealPath("/")方法返回的是项目在服务器的绝对路径 DocumentRoot.java
        String realPath = req.getServletContext().getRealPath("/")+"/store"+format;
        File fileDir = new File(realPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        if (multipartFile != null) {
            //以原来的名称命名,覆盖掉旧的，这里也可以使用UUID之类的方式命名，这里就没有处理了
            String fileName =  UUIDUtils.getUUID() + multipartFile.getOriginalFilename();
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()+"/store"+format + fileName;
            multipartFile.transferTo(new File(fileDir,fileName));
            SubmitHomework submitHomework = submitHomeworkService.getById(id);
            submitHomework.setFilePath(url);
            submitHomework.setFileName(multipartFile.getOriginalFilename());
            submitHomeworkService.updateById(submitHomework);

            System.out.println("上传的文件：" + multipartFile.getName() + "," + multipartFile.getContentType() + "," + multipartFile.getOriginalFilename()
                    + "，保存的路径为：" + url);

            //前端可以通过状态码，判断文件是否上传成功
            return R.ok().data("path", url);
        }else {
            return R.ok().message("请选中文件");
        }
    }





    /**
     * @param id 提交作业id
     * @param response
     * @return
     */
    @RequestMapping("/download")
    public R downloadFile(@RequestParam String id, HttpServletResponse response) {
        OutputStream os = null;
        InputStream is = null;
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=utf-8");

            //读取流
            String filePath = submitHomeworkService.getById(id).getFilePath();
            //Content-Disposition中指定的类型是文件的扩展名，并且弹出的下载对话框中的文件类型图片是按照文件的扩展名显示的，点保存后，文件以filename的值命名，
            // 保存类型以Content中设置的为准。注意：在设置Content-Disposition头字段之前，一定要设置Content-Type头字段。
            //把文件名按UTF-8取出，并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码，中文不要太多，最多支持17个中文，因为header有150个字节限制。、
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("测试".getBytes(StandardCharsets.UTF_8), "ISO8859-1"));

            File f = new File(filePath);
            is = new FileInputStream(f);
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            return R.error().message("下载附件失败,error:" + e.getMessage());
        }
        //文件的关闭放在finally中
        finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return R.ok().message("下载成功");
    }
}
