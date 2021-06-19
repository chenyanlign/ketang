package com.mazouri.ketangpai.mapper;

import com.mazouri.ketangpai.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mazouri.ketangpai.entity.vo.CommentVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentVO> getCommentsByTopicId(String topicId);
}
