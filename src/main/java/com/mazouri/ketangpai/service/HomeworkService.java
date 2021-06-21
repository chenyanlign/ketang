package com.mazouri.ketangpai.service;

import com.mazouri.ketangpai.entity.Homework;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mazouri.ketangpai.entity.vo.HomeworkVO;
import com.mazouri.ketangpai.entity.vo.SubmitHomeworkVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
public interface HomeworkService extends IService<Homework> {

    List<HomeworkVO> getAllHomeworkByHwId(String homeworkId);

    List<Homework> getHomeworkListByCourseId(String courseId);

    List<SubmitHomeworkVO> getAllHomeworkById(String courseId, String userId);
}
