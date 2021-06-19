package com.mazouri.ketangpai.service;

import com.mazouri.ketangpai.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mazouri.ketangpai.entity.vo.CommentVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
public interface CommentService extends IService<Comment> {

    List<CommentVO> getCommentsByTopicId(String topicId);

}
