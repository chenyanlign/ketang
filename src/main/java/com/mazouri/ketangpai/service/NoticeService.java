package com.mazouri.ketangpai.service;

import com.mazouri.ketangpai.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mazouri.ketangpai.entity.vo.NoticeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
public interface NoticeService extends IService<Notice> {

    List<NoticeVO> getAllNoticeByCourseId(String courseId);
}
