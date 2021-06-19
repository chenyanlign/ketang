package com.mazouri.ketangpai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.entity.*;
import com.mazouri.ketangpai.entity.vo.CommentNoticeVO;
import com.mazouri.ketangpai.entity.vo.NoticeVO;
import com.mazouri.ketangpai.service.CommentNoticeService;
import com.mazouri.ketangpai.service.NoticeService;
import com.mazouri.ketangpai.service.NoticeUserService;
import com.mazouri.ketangpai.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/ketangpai/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeUserService noticeUserService;

    @Autowired
    private CommentNoticeService commentNoticeService;

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "创建公告")
    @PostMapping("/addNotice")
    public R addTopic(@RequestBody Notice notice) {
        return noticeService.save(notice) ? R.ok() : R.error();
    }

    @ApiOperation(value = "删除公告")
    @PostMapping("/removeNotice/{noticeId}")
    public R removeTopic(@PathVariable String noticeId) {
        return noticeService.removeById(noticeId) ? R.ok() : R.error();
    }

    @ApiOperation(value = "更新公告置顶")
    @PostMapping("/updateTop")
    public R updateTop(@RequestParam String noticeId, @RequestParam Integer type) {
        return noticeService.updateById(new Notice().setId(noticeId).setIsTop(type)) ? R.ok() : R.error();
    }

    @ApiOperation(value = "获取某课程下的所有公告")
    @GetMapping("/getAllNoticeByCourseId/{courseId}")
    public R getAllNoticeByCourseId(@PathVariable String courseId) {
        List<NoticeVO> notices = noticeService.getAllNoticeByCourseId(courseId);
        return R.ok().data("notices", notices);
    }

    @ApiOperation(value = "获取读了通知的同学")
    @GetMapping("/getReadNoticeUser/{noticeId}")
    public R getReadNoticeUser(@PathVariable String noticeId) {
        List<SysUser> noticeUsers = sysUserService.getReadNoticeUser(noticeId);
        return R.ok().data("noticeUsers", noticeUsers);
    }

    @ApiOperation(value = "根据通知id获取评论")
    @GetMapping("/getCommentByNoticeId/{noticeId}")
    public R getCommentByNoticeId(@PathVariable String noticeId) {
        List<CommentNoticeVO> commentNotices = commentNoticeService.getCommentByNoticeId(noticeId);
        return R.ok().data("commentNotices", commentNotices);
    }

    @ApiOperation(value = "删除评论")
    @PostMapping("/removeNoticeComment/{commentId}")
    public R removeNoticeComment(@PathVariable String commentId) {
        return noticeService.removeById(commentId) ? R.ok() : R.error();
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("/addNoticeComment")
    public R addNoticeComment(@RequestBody CommentNotice commentNotice) {
        return commentNoticeService.save(commentNotice) ? R.ok() : R.error();
    }
}

