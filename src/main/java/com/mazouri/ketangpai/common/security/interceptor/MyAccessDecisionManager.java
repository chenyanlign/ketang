package com.mazouri.ketangpai.common.security.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author mazouri
 * @create 2021-06-03 1:36
 */
@Slf4j
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 当前请求需要的权限
        log.info("collection:{}", configAttributes);
        // 当前用户所具有的权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        log.info("principal:{} authorities:{}", authentication.getPrincipal().toString(), authorities);

        for (ConfigAttribute configAttribute : configAttributes) {
            // 当前请求需要的权限
            String needRole = configAttribute.getAttribute();

            // 当前用户所具有的权限
            for (GrantedAuthority grantedAuthority : authorities) {
                log.info("grantedAuthority:{}", grantedAuthority.getAuthority());

                //ROLE_admin具有所有的权限，直接放行
                if ("ROLE_admin".equals(grantedAuthority.getAuthority())){
                    return;
                }

                // 包含其中一个角色即可访问
                if (grantedAuthority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("NO_AUTHORITY!!");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
