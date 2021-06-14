package com.mazouri.ketangpai.controller;


import com.mazouri.ketangpai.common.jwt.JwtUtils;
import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    @Autowired
    private SysUserService userService;

    @ApiOperation(value = "根据token获取用户详情信息")
    @GetMapping("/getInfo")
    public R getInfo () {
        HttpServletRequest request =
                ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //获取当前登录用户用户名
        String email = JwtUtils.getEmailByJwtToken(request);
        Map<String, Object> userInfo = userService.getUserInfo(email);
        return R.ok().data(userInfo);
    }

    @ApiOperation(value = " 退出登录")
    @GetMapping("/logout")
    public R logout () {
        return R.ok();
    }

    @ApiOperation(value = "注册")
    @GetMapping("/register")
    public R register () {
        return R.ok();
    }
}

