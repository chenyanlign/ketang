package com.mazouri.ketangpai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.entity.Course;
import com.mazouri.ketangpai.entity.Homework;
import com.mazouri.ketangpai.entity.SubmitHomework;
import com.mazouri.ketangpai.entity.vo.HomeworkVO;
import com.mazouri.ketangpai.service.CourseService;
import com.mazouri.ketangpai.service.CourseUserService;
import com.mazouri.ketangpai.service.HomeworkService;
import com.mazouri.ketangpai.service.SubmitHomeworkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
@RestController
@RequestMapping("/ketangpai/homework")
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseUserService courseUserService;

    @Autowired
    private SubmitHomeworkService submitHomeworkService;

    @ApiOperation(value = "提交作业")
    @PostMapping("/submitHomework")
    public R submitHomework(@RequestBody SubmitHomework homework) {
        SubmitHomework submitWork = submitHomeworkService.getOne(new QueryWrapper<SubmitHomework>()
                .eq("user_id", homework.getUserId()).eq("homework_id", homework.getHomeworkId()));

        if (submitWork != null) {
            submitHomeworkService.updateById(submitWork);
            return R.ok().data("submitWork",submitHomeworkService.getById(submitWork.getId()));
        } else {
            homework.setGrade("未批");
            submitHomeworkService.save(homework);
            return R.ok().data("submitWork",submitHomeworkService.getById(homework.getId()));
        }
    }

    @ApiOperation(value = "老师创建作业")
    @PostMapping("/createHomework")
    public R createHomework(@RequestBody Homework homework) {
        homework.setCheckNum(0).setNoCheckNum(0)
                .setNoSubmitNum(courseUserService.getHomeworkNum(homework.getCourseId()));
        homeworkService.save(homework);
        courseService.update(new UpdateWrapper<Course>().eq("id", homework.getCourseId())
                .set("recent_work", homework.getId()));
        return R.ok();
    }

    @ApiOperation(value = "老师删除作业")
    @PostMapping("/removeHomework")
    public R removeHomework(@RequestParam String homeworkId) {
        return homeworkService.removeById(homeworkId) ? R.ok() : R.error();
    }

    @ApiOperation(value = "根据课程号和人员id获取交的作业")
    @GetMapping("/getSubmitHomework")
    public R getSubmitHomework(@RequestParam String homeworkId, @RequestParam String userId) {
        SubmitHomework submitHomeWork = submitHomeworkService.getOne(new QueryWrapper<SubmitHomework>().eq("homework_id", homeworkId).eq("user_id", userId));
        return R.ok().data("submitHomework", submitHomeWork) ;
    }

    @ApiOperation(value = "根据课程号和用户id获取交的所有作业")
    @GetMapping("/getSubmitHomeworkList")
    public R getSubmitHomeworkList(@RequestParam String courseId, @RequestParam String userId) {
       List<SubmitHomework> submitHomeworkList =  submitHomeworkService.getSubmitHomeworkList(courseId,userId);
        return R.ok().data("submitHomeworkList",submitHomeworkList);
    }

    @ApiOperation(value = "根据课程号获取作业")
    @GetMapping("/getHomeworksByCourseId/{courseId}")
    public R getHomeworkByCourseId(@PathVariable String courseId) {
        List<Homework> homeworkList =homeworkService.getHomeworkListByCourseId(courseId);
        return R.ok().data("homeworks", homeworkList);
    }

    @ApiOperation(value = "根据id获取作业")
    @GetMapping("/getHomeworkById/{homeworkId}")
    public R getHomeworkById(@PathVariable String homeworkId) {
        Homework homework = homeworkService.getById(homeworkId);
        return R.ok().data("homework", homework);
    }

    @ApiOperation(value = "根据作业id获取所有学生的作业")
    @GetMapping("/getAllSubmitHomeworkByHwId/{homeworkId}")
    public R getAllSubmitHomeworkByHwId(@PathVariable String homeworkId) {
        List<HomeworkVO> homeworkVOList = homeworkService.getAllHomeworkByHwId(homeworkId);
        return R.ok().data("homeworkList",homeworkVOList);
    }

    @ApiOperation(value = "根据id获取homework打分")
    @PostMapping("/mark")
    public R mark(@RequestParam String submitHomeworkId, @RequestParam String grade) {
        submitHomeworkService.updateById(new SubmitHomework().setId(submitHomeworkId).setGrade(grade).setChecked(1));
        return R.ok();
    }

    @ApiOperation(value = "打回作业")
    @PostMapping("/backHomework/{submitHomeworkId}")
    public R backHomework(@PathVariable String submitHomeworkId) {
        submitHomeworkService.updateById(new SubmitHomework().setId(submitHomeworkId).setGrade("被打回"));
        return R.ok();
    }
}

