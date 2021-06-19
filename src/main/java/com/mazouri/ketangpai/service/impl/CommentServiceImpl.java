package com.mazouri.ketangpai.service.impl;

import com.mazouri.ketangpai.entity.Comment;
import com.mazouri.ketangpai.entity.vo.CommentVO;
import com.mazouri.ketangpai.mapper.CommentMapper;
import com.mazouri.ketangpai.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public List<CommentVO> getCommentsByTopicId(String topicId) {

        return baseMapper.getCommentsByTopicId(topicId);
    }
}
