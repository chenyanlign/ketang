package com.mazouri.ketangpai.controller;


import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.entity.Course;
import com.mazouri.ketangpai.entity.CourseUser;
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
@RequestMapping("/ketangpai/course")
public class CourseController {
    @ApiOperation(value = "获取登录用户的所有的课程")
    @GetMapping("/getAllCourseByUserId/{userId}")
    public R getAllCourseByUserId(@PathVariable String userId) {
        return R.ok();
    }

    @ApiOperation(value = "根据加课码加入课程")
    @PostMapping("/joinCourse")
    public R joinCourse(@RequestBody CourseUser courseUser ) {
        return R.ok();
    }

    @ApiOperation(value = "根据id获取课程")
    @GetMapping("/getCourseById/{courseId}")
    public R getCourseById(@PathVariable String courseId) {
        return R.ok();
    }

    @ApiOperation(value = "根据id获取课程")
    @GetMapping("/getAllUser/{courseId}")
    public R getAllUser(@PathVariable String courseId) {
        return R.ok();
    }
    @ApiOperation(value = "获取所有归档课程")
    @GetMapping("/getAllCourseByUserId/{userId}")
    public R getAllArchiveCourse(@PathVariable String userId) {
        return R.ok();
    }

    @ApiOperation(value = "获取所有归档课程")
    @GetMapping("/createCourse")
    public R createCourse(@RequestBody Course course) {
        return R.ok();
    }

    @ApiOperation(value = "归档课程")
    @PostMapping("/archiveCourse")
    public R archiveCourse(String  courseId) {
        return R.ok();
    }

    @ApiOperation(value = "恢复归档课程")
    @PostMapping("/archiveCourse")
    public R recoverArchiveCourse(String  courseId) {
        return R.ok();
    }

    @ApiOperation(value = "删除课程")
    @DeleteMapping("/delete")
    public R deleteCourse(String  courseId) {
        return R.ok();
    }
}

