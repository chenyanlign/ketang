package com.mazouri.ketangpai.mapper;

import com.mazouri.ketangpai.entity.Homework;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mazouri.ketangpai.entity.vo.HomeworkVO;
import com.mazouri.ketangpai.entity.vo.SubmitHomeworkVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
public interface HomeworkMapper extends BaseMapper<Homework> {
    List<HomeworkVO> getAllUserWork(String homeworkId);

    List<SubmitHomeworkVO> getAllHomeworkById(String courseId);
}
