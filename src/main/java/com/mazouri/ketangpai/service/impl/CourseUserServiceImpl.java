package com.mazouri.ketangpai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.entity.CourseUser;
import com.mazouri.ketangpai.entity.Homework;
import com.mazouri.ketangpai.mapper.CourseUserMapper;
import com.mazouri.ketangpai.service.CourseUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
@Service
public class CourseUserServiceImpl extends ServiceImpl<CourseUserMapper, CourseUser> implements CourseUserService {
    @Override
    public Integer getHomeworkNum(String courseId){
        return baseMapper.selectCount(new QueryWrapper<CourseUser>()
                .eq("course_id",courseId).eq("user_type",3));
    }
}
