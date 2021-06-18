package com.mazouri.ketangpai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.entity.Homework;
import com.mazouri.ketangpai.entity.SubmitHomework;
import com.mazouri.ketangpai.entity.vo.HomeworkVO;
import com.mazouri.ketangpai.mapper.HomeworkMapper;
import com.mazouri.ketangpai.service.CourseUserService;
import com.mazouri.ketangpai.service.HomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mazouri.ketangpai.service.SubmitHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
@Service
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework> implements HomeworkService {
    @Autowired
    private SubmitHomeworkService submitHomeworkService;

    @Autowired
    private CourseUserService courseUserService;




    @Override
    public List<HomeworkVO> getAllHomeworkByHwId(String homeworkId) {
        List<HomeworkVO> allUserWork = baseMapper.getAllUserWork(homeworkId);
        List<SubmitHomework> submitHomeworkList = submitHomeworkService.list(new QueryWrapper<SubmitHomework>()
                .eq("homework_id", homeworkId));

        for (HomeworkVO homework : allUserWork) {
            for (SubmitHomework submitHomework : submitHomeworkList) {
                if (submitHomework.getUserId().equals(homework.getUserId())) {
                    homework.setFinishWorkId(submitHomework.getId()).setFilePath(submitHomework.getFilePath())
                            .setCreateTime(submitHomework.getCreateTime()).setGrade(submitHomework.getGrade());
                }
            }
        }

        allUserWork.forEach(homeworkVO -> {
            if (homeworkVO.getGrade() == null) {
                homeworkVO.setGrade("未交");
            }
        });

        return allUserWork;
    }

    @Override
    public List<Homework> getHomeworkListByCourseId(String courseId) {
        //        List<Homework> homeworkList = homeworkService.list(new QueryWrapper<Homework>().eq("course_id", courseId));


        List<Homework> homeworkList = baseMapper.selectList(new QueryWrapper<Homework>().eq("course_id", courseId));
        homeworkList.forEach(homework -> refreshNum(courseId, homework.getId()));

        return baseMapper.selectList(new QueryWrapper<Homework>().eq("course_id", courseId));
    }

    public void refreshNum(String courseId, String homeworkId) {
        Integer homeworkTotalNum = courseUserService.getHomeworkNum(courseId);
        int submitNum = submitHomeworkService.getHomeworkSubmitNum(homeworkId);

        Integer checkNum = submitHomeworkService.getHomeworkCheckNum(homeworkId);
        Integer noSubmitNum = homeworkTotalNum - submitNum;
        Integer noCheckNum = submitNum - checkNum;

        baseMapper.updateById(new Homework().setId(homeworkId).setCheckNum(checkNum)
                .setNoSubmitNum(noSubmitNum).setNoCheckNum(noCheckNum));

    }
}
