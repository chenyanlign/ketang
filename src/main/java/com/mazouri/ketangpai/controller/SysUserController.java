package com.mazouri.ketangpai.controller;


import com.mazouri.ketangpai.common.result.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
@RestController
@RequestMapping("/admin/user")
public class SysUserController {

    @ApiOperation(value = "根据token获取用户详情信息")
    @GetMapping("/getInfo ")
    public R getInfo () {
        return R.ok();
    }

    @ApiOperation(value = " 退出登录")
    @GetMapping("/logout ")
    public R logout () {
        return R.ok();
    }

    @ApiOperation(value = "注册")
    @GetMapping("/register ")
    public R register () {
        return R.ok();
    }
}

