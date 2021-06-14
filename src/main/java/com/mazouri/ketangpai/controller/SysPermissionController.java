package com.mazouri.ketangpai.controller;


import com.mazouri.ketangpai.common.result.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
@RestController
@RequestMapping("/admin/permission")
public class SysPermissionController {
    @ApiOperation(value = "获取某用户的权限值")
    @GetMapping("/getPermissionsByUserId/{userId}")
    public R getPermissionsByUserId (@PathVariable String userId) {
        return R.ok();
    }
}

