package com.mazouri.ketangpai.service;

import com.mazouri.ketangpai.entity.SubmitHomework;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
public interface SubmitHomeworkService extends IService<SubmitHomework> {

    List<SubmitHomework> getSubmitHomeworkList(String courseId, String userId);
}
