package com.mazouri.ketangpai.controller;


import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.entity.SubmitHomework;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
@RestController
@RequestMapping("/ketangpai/homework")
public class HomeworkController {

    @ApiOperation(value = "提交作业")
    @PostMapping("/submitHomework")
    public R submitHomework(@RequestBody SubmitHomework homework) {
        return R.ok();
    }

    @ApiOperation(value = "根据课程号获取作业")
    @GetMapping("/getByCourseId/{courseId}")
    public R getHomeworkByCourseId(@PathVariable String courseId) {
        return R.ok();
    }

    @ApiOperation(value = "根据id获取homework打分")
    @PostMapping("/mark/{submitHomeworkId}")
    public R mark(@PathVariable String submitHomeworkId,@RequestBody double grade) {
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

