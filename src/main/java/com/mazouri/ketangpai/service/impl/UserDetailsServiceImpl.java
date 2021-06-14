package com.mazouri.ketangpai.service.impl;

import com.mazouri.ketangpai.common.security.entity.SecurityUser;
import com.mazouri.ketangpai.entity.SysUser;
import com.mazouri.ketangpai.service.SysPermissionService;
import com.mazouri.ketangpai.service.SysRoleService;
import com.mazouri.ketangpai.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 * @author mazouri
 * @since 2020-11-08
 */
@Service("userDetailsService")
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysRoleService roleService;


    /***
     * 根据账号获取用户信息
     * @param email:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("进入UserDetailsServiceImpl-----loadUserByUsername----{}", email);
        // 从数据库中取出用户信息
        SysUser user = userService.selectByEmail(email);

        // 返回UserDetails实现类
        SecurityUser securityUser = new SecurityUser();
        BeanUtils.copyProperties(user, securityUser);
        List<String> roles = roleService.selectRoleByEmail(email);
        securityUser.setAuths(roles);
        return securityUser;
    }
}
