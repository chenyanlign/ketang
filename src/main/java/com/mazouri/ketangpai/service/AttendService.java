package com.mazouri.ketangpai.service;

import com.mazouri.ketangpai.entity.Attend;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-20
 */
public interface AttendService extends IService<Attend> {

    List<Attend> getAllAttendByCourseId(String courseId);
}
