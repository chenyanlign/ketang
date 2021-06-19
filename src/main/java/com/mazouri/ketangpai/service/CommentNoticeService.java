package com.mazouri.ketangpai.service;

import com.mazouri.ketangpai.entity.CommentNotice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mazouri.ketangpai.entity.vo.CommentNoticeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
public interface CommentNoticeService extends IService<CommentNotice> {

    List<CommentNoticeVO> getCommentByNoticeId(String noticeId);
}
