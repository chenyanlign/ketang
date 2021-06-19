package com.mazouri.ketangpai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.entity.CommentNotice;
import com.mazouri.ketangpai.entity.Notice;
import com.mazouri.ketangpai.entity.NoticeUser;
import com.mazouri.ketangpai.entity.vo.NoticeVO;
import com.mazouri.ketangpai.mapper.NoticeMapper;
import com.mazouri.ketangpai.service.CommentNoticeService;
import com.mazouri.ketangpai.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mazouri.ketangpai.service.NoticeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Autowired
    private CommentNoticeService commentNoticeService;

    @Autowired
    private NoticeUserService noticeUserService;

    @Override
    public List<NoticeVO> getAllNoticeByCourseId(String courseId) {
        List<NoticeVO> noticeVOList = baseMapper.getAllNoticeByCourseId(courseId);
        noticeVOList.forEach(notice -> notice
                .setCommentNum(commentNoticeService.count(new QueryWrapper<CommentNotice>()
                        .eq("notice_id", notice.getId())))
                .setReadNum(noticeUserService.count(new QueryWrapper<NoticeUser>()
                        .eq("notice_id", notice.getId()))));
        return noticeVOList;
    }
}
