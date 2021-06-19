package com.mazouri.ketangpai.service.impl;

import com.mazouri.ketangpai.entity.CommentNotice;
import com.mazouri.ketangpai.entity.vo.CommentNoticeVO;
import com.mazouri.ketangpai.mapper.CommentNoticeMapper;
import com.mazouri.ketangpai.service.CommentNoticeService;
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
public class CommentNoticeServiceImpl extends ServiceImpl<CommentNoticeMapper, CommentNotice> implements CommentNoticeService {

    @Override
    public List<CommentNoticeVO> getCommentByNoticeId(String noticeId) {
        return baseMapper.getCommentByNoticeId(noticeId);
    }
}
