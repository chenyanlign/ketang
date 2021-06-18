package com.mazouri.ketangpai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.entity.Homework;
import com.mazouri.ketangpai.entity.SubmitHomework;
import com.mazouri.ketangpai.mapper.SubmitHomeworkMapper;
import com.mazouri.ketangpai.service.SubmitHomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
@Service
public class SubmitHomeworkServiceImpl extends ServiceImpl<SubmitHomeworkMapper, SubmitHomework> implements SubmitHomeworkService {

    @Override
    public List<SubmitHomework> getSubmitHomeworkList(String courseId, String userId) {
        return baseMapper.getSubmitHomeworkList(courseId,userId);
    }

    @Override
    public Integer getHomeworkSubmitNum(String homeworkId) {
        return baseMapper.selectCount(new QueryWrapper<SubmitHomework>().eq("homework_id", homeworkId));
    }

    @Override
    public Integer getHomeworkCheckNum(String homeworkId) {
        return baseMapper.selectCount(new QueryWrapper<SubmitHomework>().eq("homework_id", homeworkId).eq("checked", "1"));
    }
}
