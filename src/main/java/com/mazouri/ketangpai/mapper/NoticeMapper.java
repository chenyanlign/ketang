package com.mazouri.ketangpai.mapper;

import com.mazouri.ketangpai.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mazouri.ketangpai.entity.vo.NoticeVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    List<NoticeVO> getAllNoticeByCourseId(String courseId);
}
