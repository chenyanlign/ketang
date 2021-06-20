package com.mazouri.ketangpai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.entity.Attend;
import com.mazouri.ketangpai.entity.AttendUser;
import com.mazouri.ketangpai.entity.SysUser;
import com.mazouri.ketangpai.entity.vo.AttendUserVO;
import com.mazouri.ketangpai.entity.vo.AttendVO;
import com.mazouri.ketangpai.service.AttendService;
import com.mazouri.ketangpai.service.AttendUserService;
import com.mazouri.ketangpai.service.CourseUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
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
        return R.ok().data("attendUserList", attendUserList);
    }

    @ApiOperation(value = "获取该课程的所有考勤")
    @PostMapping("/createAttend")
    public R startAttend(@RequestBody Attend attend) {
        Random r = new Random();
        String num = r.nextInt(10)+","+ r.nextInt(10)+","+ r.nextInt(10)+","+ r.nextInt(10);
        attend.setNum(num);
        return attendService.save(attend)? R.ok().data("attend",attend) : R.error();
    }


    @ApiOperation(value = "根据attendId删除某一条记录")
    @PostMapping("/removeAttend/{attendId}")
    public R removeAttendByAttendId(@PathVariable String attendId) {
        return attendService.removeById(attendId) ? R.ok() : R.error();
    }

    @ApiOperation(value = "结束考勤状态")
    @PostMapping("/finishAttend/{attendId}")
    public R finishAttend(@PathVariable String attendId) {
        return attendService.updateById(new Attend().setId(attendId).setIsEnd(1)) ? R.ok() : R.error();
    }

    @ApiOperation(value = "放弃考勤")
    @PostMapping("/abandonAttend/{attendId}")
    public R abandonAttend(@PathVariable String attendId) {
        return attendService.removeById(attendId)? R.ok() : R.error();
    }

    @ApiOperation(value = "验证成功后用户考勤成功")
    @PostMapping("/addAttendUser")
    public R addAttendUser(@RequestBody AttendUser attendUser) {
        return attendUserService.save(attendUser)? R.ok() : R.error();
    }


    @ApiOperation(value = "获取最新的考勤人数")
    @GetMapping("/getNewAttendNum/{attendId}")
    public R getNewAttendNum(@PathVariable String attendId) {
        int attendNum = attendUserService.count(new QueryWrapper<AttendUser>().eq("attend_id", attendId));
        return  R.ok().data("attendNum",attendNum);
    }


    @ApiOperation(value = "获取某同学所有的考勤记录")
    @GetMapping("/getUserAttend")
    public R getUserAttend(@RequestParam String courseId,@RequestParam String userId) {
       List<AttendVO> attends =  attendService.getUserAllAttend(userId,courseId);
        return  R.ok().data("attends",attends);
    }

}

