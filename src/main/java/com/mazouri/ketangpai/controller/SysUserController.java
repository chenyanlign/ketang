package com.mazouri.ketangpai.controller;


import com.mazouri.ketangpai.common.jwt.JwtUtils;
import com.mazouri.ketangpai.common.jwt.MD5;
import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.entity.SysUser;
import com.mazouri.ketangpai.entity.SysUserRole;
import com.mazouri.ketangpai.service.SysUserRoleService;
import com.mazouri.ketangpai.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Autowired
    private SysUserRoleService userRoleService;

    @ApiOperation(value = "根据token获取用户详情信息")
    @GetMapping("/getInfo")
    public R getInfo() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //获取当前登录用户用户名
        String email = JwtUtils.getEmailByJwtToken(request);
        Map<String, Object> userInfo = userService.getUserInfo(email);
        return R.ok().data(userInfo);
    }

    @ApiOperation(value = " 退出登录")
    @GetMapping("/logout")
    public R logout() {
        return R.ok();
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register/{type}")
    public R register(@RequestBody SysUser sysUser, @PathVariable String type) {


        List<String> accountList = userService.list().stream().map(SysUser::getAccount).collect(Collectors.toList());

        if (accountList.contains(sysUser.getAccount())) {
            return R.error().message("该账号已被注册，请重新检查注册信息");
        }
        sysUser.setAvatar("https://img2.baidu.com/it/u=3649178992,1821853682&fm=26&fmt=auto&gp=0.jpg");
        sysUser.setPassword(MD5.encrypt(sysUser.getPassword()));
        userService.save(sysUser);
        //teacher
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(sysUser.getId());
        if ("1".equals(type)) {
            userRole.setRoleId("1");
        } else {
            userRole.setRoleId("2");
        }
        userRoleService.save(userRole);
        return R.ok();
    }
}

