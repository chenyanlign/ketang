package com.mazouri.ketangpai.mapper;

import com.mazouri.ketangpai.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mazouri.ketangpai.entity.vo.CourseVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
public interface CourseMapper extends BaseMapper<Course> {
    List<CourseVO> getAllCourseById(String id);

    CourseVO getCourseByCourseId(String courseId);
}
