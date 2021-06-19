package com.mazouri.ketangpai.mapper;

import com.mazouri.ketangpai.entity.CommentNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mazouri.ketangpai.entity.vo.CommentNoticeVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
public interface CommentNoticeMapper extends BaseMapper<CommentNotice> {

    List<CommentNoticeVO> getCommentByNoticeId(String noticeId);

}
