package com.mazouri.ketangpai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.entity.Attend;
import com.mazouri.ketangpai.entity.SysUser;
import com.mazouri.ketangpai.entity.vo.AttendUserVO;
import com.mazouri.ketangpai.service.AttendService;
import com.mazouri.ketangpai.service.AttendUserService;
import com.mazouri.ketangpai.service.CourseUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mazouri
 * @since 2021-06-20
 */
@RestController
@RequestMapping("/ketangpai/attend")
public class AttendController {
    @Autowired
    private AttendService attendService;

    @Autowired
    private AttendUserService attendUserService;

    @Autowired
    private CourseUserService courseUserService;

    @ApiOperation(value = "获取该课程的所有考勤")
    @GetMapping("/getAllAttendByCourseId/{courseId}")
    public R getAllAttendByCourseId(@PathVariable String courseId) {
        List<Attend> attends = attendService.getAllAttendByCourseId(courseId);
        return R.ok().data("attends", attends);
    }

    @ApiOperation(value = "获取该课程的所有考勤")
    @GetMapping("/getAllUserByAttendId/{attendId}")
    public R getAllUserByAttendId(@PathVariable String attendId) {
        List<AttendUserVO> attendUserList = attendUserService.getAllUserByAttendId(attendId);
        return R.ok().data("attendUserList",attendUserList);
    }
}

