package com.mazouri.ketangpai.common.security.filter;


import com.mazouri.ketangpai.common.jwt.JwtUtils;
import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.common.result.ResponseUtil;
import com.mazouri.ketangpai.common.security.security.TokenManager;
import com.mazouri.ketangpai.service.SysRoleService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 访问过滤器
 * </p>
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的token项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 *  如果校验通过，就认为这是一个取得授权的合法请求。
 * @author qy
 * @since 2019-11-08
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;
    private SysRoleService roleService;


    public TokenAuthenticationFilter(AuthenticationManager authManager, TokenManager tokenManager, RedisTemplate redisTemplate,SysRoleService roleService) {
        super(authManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.roleService = roleService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        logger.info("================="+req.getRequestURI());

        // 针对使用用户名和密码验证的请求按照约定进行了一定的封装：将username赋值到了principal ，
        // 而将password赋值到了credentials,按照流程，将其传递给AuthenticationMananger调用身份验证核心完成相关工作。
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            authentication = getAuthentication(req);
        } catch (Exception e) {
            ResponseUtil.out(res, R.error());
        }

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            ResponseUtil.out(res, R.error());
        }
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // token置于header里
        String token = request.getHeader("token");
        if (token != null && !"".equals(token.trim())) {
            String email = JwtUtils.getEmailByJwtToken(token);

            List<String> roles = roleService.selectRoleByEmail(email);
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            for(String role : roles) {
                if(StringUtils.isEmpty(role)) {
                    continue;
                }
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
                authorities.add(authority);
            }

            if (!StringUtils.isEmpty(email)) {
                return new UsernamePasswordAuthenticationToken(email, token, authorities);
            }
            return null;
        }
        return null;
    }
}