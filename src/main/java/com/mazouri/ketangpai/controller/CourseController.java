package com.mazouri.ketangpai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.common.jwt.MD5;
import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.common.utils.GenerateCourseCode;
import com.mazouri.ketangpai.entity.Course;
import com.mazouri.ketangpai.entity.CourseUser;
import com.mazouri.ketangpai.entity.SysUser;
import com.mazouri.ketangpai.entity.vo.CourseVO;
import com.mazouri.ketangpai.service.CourseService;
import com.mazouri.ketangpai.service.CourseUserService;
import com.mazouri.ketangpai.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
@RestController
@RequestMapping("/ketangpai/course")
public class CourseController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseUserService courseUserService;

    @ApiOperation(value = "获取登录用户的所有的课程")
    @GetMapping("/getAllCourseByUserId/{userId}")
    public R getAllCourseByUserId(@PathVariable String userId) {
        List<CourseVO> courseList = courseService.getAllCourseById(userId);
        return R.ok().data("course", courseList);
    }

    @ApiOperation(value = "根据加课码加入课程")
    @PostMapping("/joinCourse")
    public R joinCourse(@RequestParam String code, @RequestParam String userId) {
        Course course = courseService.getOne(new QueryWrapper<Course>().eq("code", code));
        CourseUser courseUser = new CourseUser();
        courseUser.setUserType(3).setCourseId(course.getId()).setUserId(userId);

        return courseUserService.save(courseUser) ? R.ok() : R.error();
    }

    @ApiOperation(value = "根据id获取课程")
    @GetMapping("/getCourseById/{courseId}")
    public R getCourseById(@PathVariable String courseId) {
        CourseVO course = courseService.getCourseByCourseId(courseId);
        int courseNum = courseUserService.list(new QueryWrapper<CourseUser>().eq("course_id", courseId)).size();
        course.setCourseNum(courseNum);
        return R.ok().data("course", course);
    }

    @ApiOperation(value = "获取该课程的所有学生")
    @GetMapping("/getAllStudent/{courseId}")
    public R getAllStudent(@PathVariable String courseId) {
        List<SysUser> studentList = userService.getAllStudentByCourseId(courseId);
        return R.ok().data("studentList", studentList);
    }

    @ApiOperation(value = "获取该课程的所有学生")
    @GetMapping("/getAllTeacher/{courseId}")
    public R getAllTeacher(@PathVariable String courseId) {
        List<SysUser> teacherList = userService.getAllTeacherByCourseId(courseId);
        return R.ok().data("teacherList", teacherList);
    }

    @ApiOperation(value = "获取所有归档课程")
    @GetMapping("/getAllArchiveCourse/{userId}")
    public R getAllArchiveCourse(@PathVariable String userId) {
        return R.ok();
    }

    @ApiOperation(value = "教师创建课程")
    @PostMapping("/createCourse")
    public R createCourse(@RequestBody Course course) {
        List<String> codes = courseService.list().stream().map(Course::getCode).collect(Collectors.toList());
        //设置选课码 唯一
        course.setCode(GenerateCourseCode.getCode(codes));
        CourseUser courseUser = new CourseUser();
        if (courseService.save(course)) {
            String insertedCourseId = courseService.getOne(new QueryWrapper<Course>().eq("code", course.getCode())).getId();
            //把老师加到course_user表
            courseUser.setCourseId(insertedCourseId).setUserId(course.getCreateTeacherId()).setUserType(1);
        }
        return courseUserService.save(courseUser) ? R.ok() : R.error();
    }

    @ApiOperation(value = "归档课程")
    @PostMapping("/archiveCourse")
    public R archiveCourse(@RequestParam String courseId, @RequestParam String userId) {
        CourseUser courseUser = courseUserService.getOne(new QueryWrapper<CourseUser>().eq("course_id", courseId).eq("user_id", userId));
        return courseUserService.updateById(courseUser.setArchived(1)) ? R.ok() : R.error();
    }

    @ApiOperation(value = "恢复归档课程")
    @PostMapping("/recoverArchiveCourse")
    public R recoverArchiveCourse(@RequestParam String courseId, @RequestParam String userId) {
        CourseUser courseUser = courseUserService.getOne(new QueryWrapper<CourseUser>().eq("course_id", courseId).eq("user_id", userId));
        return courseUserService.updateById(courseUser.setArchived(0)) ? R.ok() : R.error();
    }

    @ApiOperation(value = "退课")
    @PostMapping("/delete")
    public R deleteCourse(@RequestParam String courseId, @RequestParam String userId) {
        CourseUser courseUser = courseUserService.getOne(new QueryWrapper<CourseUser>().eq("course_id", courseId).eq("user_id", userId));
        return courseUserService.removeById(courseUser.getId()) ? R.ok() : R.error();
    }



    @ApiOperation(value = "老师删除课程")
    @PostMapping("/deleteCourse")
    public R removeCourseById(@RequestParam String courseId, @RequestParam String password) {
        String teacherId = courseService.getOne(new QueryWrapper<Course>().eq("id", courseId)).getCreateTeacherId();
        if (userService.getById(teacherId).getPassword().equals(MD5.encrypt(password))) {
            List<String> courseUserIds = courseUserService.list(new QueryWrapper<CourseUser>().eq("course_id", courseId))
                    .stream().map(CourseUser::getId).collect(Collectors.toList());
            return courseService.removeById(courseId) && courseUserService.removeByIds(courseUserIds) ? R.ok() : R.error();
        } else {
            return R.error().message("密码错误");
        }
    }
}

