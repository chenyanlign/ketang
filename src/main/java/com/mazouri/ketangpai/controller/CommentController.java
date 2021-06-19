package com.mazouri.ketangpai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.entity.Comment;
import com.mazouri.ketangpai.entity.vo.CommentVO;
import com.mazouri.ketangpai.entity.vo.TopicVO;
import com.mazouri.ketangpai.service.CommentService;
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
@RequestMapping("/ketangpai/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "获取某话题下的所有的评论")
    @GetMapping("/getCommentsByTopicId/{topicId}")
    public R getCommentsByTopicId(@PathVariable String topicId) {
        List<CommentVO> commentList = commentService.getCommentsByTopicId(topicId);
        return R.ok().data("commentList", commentList);
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("/publicComment")
    public R publicComment(@RequestBody Comment comment) {
        return commentService.save(comment) ? R.ok() : R.error();
    }


    @ApiOperation(value = "添加评论")
    @PostMapping("/removeComment/{commentId}")
    public R removeComment(@PathVariable String commentId) {
        return commentService.removeById(commentId) ? R.ok() : R.error();
    }
}

