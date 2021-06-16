package com.mazouri.ketangpai.service;

import com.mazouri.ketangpai.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mazouri.ketangpai.entity.vo.CourseVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
public interface CourseService extends IService<Course> {
    List<CourseVO> getAllCourseById(String id);

    CourseVO getCourseByCourseId(String courseId);
}
