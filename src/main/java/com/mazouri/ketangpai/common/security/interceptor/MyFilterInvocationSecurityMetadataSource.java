package com.mazouri.ketangpai.common.security.interceptor;

import com.mazouri.ketangpai.service.SysRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @author mazouri
 * @create 2021-06-03 1:32
 */
@Slf4j
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private SysRolePermissionService rolePermissionService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        Set<ConfigAttribute> set = new HashSet<>();
        HashMap<String, List<String>> permissionRoles = rolePermissionService.getPermissionRoles();
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        System.out.println(requestUrl);
        if (antPathMatcher.match("/admin/user/**", requestUrl)) {
            set.add(new SecurityConfig("student"));
            set.add(new SecurityConfig("teacher"));
            set.add(new SecurityConfig("svip"));
        } else {
            Set<String> urls = permissionRoles.keySet();
            for (String url : urls) {
                if (antPathMatcher.match(url, requestUrl)) {
                    List<String> roles = permissionRoles.get(url);
                    roles.forEach(s -> {
                        SecurityConfig securityConfig = new SecurityConfig(s);
                        set.add(securityConfig);
                    });
                }
            }
        }

        //如果不加这个，没有权限的会直接执行
        if (ObjectUtils.isEmpty(set)) {
            return SecurityConfig.createList("ROLE_NO_AUTHORITY");
        }


        return set;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}

