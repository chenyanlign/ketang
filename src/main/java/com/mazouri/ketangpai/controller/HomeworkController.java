package com.mazouri.ketangpai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.entity.Homework;
import com.mazouri.ketangpai.entity.SubmitHomework;
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
    private SubmitHomeworkService submitHomeworkService;

    @ApiOperation(value = "提交作业")
    @PostMapping("/submitHomework")
    public R submitHomework(@RequestBody SubmitHomework homework) {
        SubmitHomework submitWork = submitHomeworkService.getOne(new QueryWrapper<SubmitHomework>()
                .eq("user_id", homework.getUserId()).eq("homework_id", homework.getHomeworkId()));

        if (submitWork!=null){
            submitHomeworkService.updateById(submitWork);
        }else {
            submitHomeworkService.save(homework);
        }

        return R.ok().data("submitWork",submitHomeworkService.getById(submitWork.getId()));
//        System.out.println(homework);
//        if(submitHomeworkService.save(homework)){
//            SubmitHomework submitWork = submitHomeworkService.getOne(new QueryWrapper<SubmitHomework>()
//                    .eq("user_id", homework.getUserId()).eq("homework_id", homework.getHomeworkId()));
//            return R.ok().data("submitWork",submitWork);
//        }else {
//            return R.error();
//        }
    }

    @ApiOperation(value = "根据课程号和人员id获取交的作业")
    @GetMapping("/getSubmitHomework")
    public R getSubmitHomework(@RequestParam String homeworkId, @RequestParam String userId) {
        SubmitHomework submitHomeWork = submitHomeworkService.getOne(new QueryWrapper<SubmitHomework>().eq("homework_id", homeworkId).eq("user_id", userId));
        return (submitHomeWork !=null) ? R.ok().data("submitHomework",submitHomeWork) : R.error();

    }

    @ApiOperation(value = "根据课程号获取作业")
    @GetMapping("/getHomeworksByCourseId/{courseId}")
    public R getHomeworkByCourseId(@PathVariable String courseId) {
        List<Homework> homeworkList = homeworkService.list(new QueryWrapper<Homework>().eq("course_id", courseId));
        return R.ok().data("homework",homeworkList);
    }

    @ApiOperation(value = "根据id获取homework打分")
    @PostMapping("/mark")
    public R mark(@RequestParam String submitHomeworkId, @RequestParam double grade) {
        return R.ok();
    }

    @ApiOperation(value = "批阅作业")
    @GetMapping("/readHomework/{submitHomeworkId}")
    public R readHomework(@PathVariable String submitHomeworkId) {
        return R.ok();
    }

    @ApiOperation(value = "获取没有批阅的作业")
    @GetMapping("/getNoChecked")
    public R getNoChecked() {
        return R.ok();
    }

    @ApiOperation(value = "获取批阅的作业")
    @GetMapping("/getChecked")
    public R getChecked() {
        return R.ok();
    }

    @ApiOperation(value = "获取批阅的作业")
    @GetMapping("/getNeedSubmitStudents")
    public R getNeedSubmitStudents() {
        return R.ok();
    }


}

