package com.mazouri.ketangpai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.entity.Course;
import com.mazouri.ketangpai.entity.Homework;
import com.mazouri.ketangpai.entity.SubmitHomework;
import com.mazouri.ketangpai.entity.SysUser;
import com.mazouri.ketangpai.entity.vo.HomeworkVO;
import com.mazouri.ketangpai.entity.vo.SubmitHomeworkVO;
import com.mazouri.ketangpai.mapper.HomeworkMapper;
import com.mazouri.ketangpai.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private HomeworkService homeworkService;




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
        List<Homework> homeworkList = baseMapper.selectList(new QueryWrapper<Homework>().eq("course_id", courseId));
        homeworkList.forEach(homework -> refreshNum(courseId, homework.getId()));
        return baseMapper.selectList(new QueryWrapper<Homework>().eq("course_id", courseId));
    }

    @Override
    public List<SubmitHomeworkVO> getAllHomeworkById(String courseId, String userId) {
        List<SubmitHomeworkVO> submitHomeworkList =   baseMapper.getAllHomeworkById(courseId);
        Integer total = homeworkService.count(new QueryWrapper<Homework>().eq("course_id",courseId));
        SysUser user = sysUserService.getById(userId);
        submitHomeworkList.forEach(submitHomework -> {
            SubmitHomework hw = submitHomeworkService.getOne(new QueryWrapper<SubmitHomework>().eq("homework_id", submitHomework.getId()).eq("user_id", userId));
            if (hw!=null){
                submitHomework.setTotal(total).setGrade(hw.getGrade()).setFilePath(hw.getFilePath())
                        .setUsername(user.getUsername()).setAccount(user.getAccount()).setCreateTime(hw.getCreateTime());

            }else {
                submitHomework.setTotal(total).setGrade("未交").setUsername(user.getUsername()).setAccount(user.getAccount());
            }

        });

        Integer noSubmitCount = (int) submitHomeworkList.stream().filter(submitHomework -> "未交".equals(submitHomework.getGrade())).count();
        Integer noCheckCount = (int) submitHomeworkList.stream().filter(submitHomework -> "未批".equals(submitHomework.getGrade())).count();
        Integer checkNum = total - noCheckCount-noSubmitCount;
        submitHomeworkList.forEach(sh -> sh.setTotal(total).setCheckNum(checkNum).setNoCheckNum(noCheckCount).setNoSubmitNum(noSubmitCount));

        return submitHomeworkList;
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
