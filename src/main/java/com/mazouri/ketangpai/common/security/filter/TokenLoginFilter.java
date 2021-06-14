package com.mazouri.ketangpai.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.common.result.ResponseUtil;
import com.mazouri.ketangpai.common.security.entity.SecurityUser;
import com.mazouri.ketangpai.common.security.security.TokenManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 * 认证操作全靠这个过滤器，默认匹配URL为/login且必须为POST请求
 * </p>
 *
 * @author mazouri
 * @since 2020-06-04
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {
    /**
     * 真正认证操作在AuthenticationManager里面
     */
    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/user/login","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            SecurityUser user = new ObjectMapper().readValue(req.getInputStream(), SecurityUser.class);
            System.out.println(user.getUsername()+"------------------------loginFilter");
            //类UsernamePasswordAuthenticationFilter 的验证方法 attemptAuthentication()
            // 会将用户表单提交过来的用户名和密码封装成对象委托类 AuthenticationManager 的验证方法 authenticate() 进行身份验证。
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 登录成功
     * @param req
     * @param res
     * @param chain
     * @param auth
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) {
        SecurityUser user = (SecurityUser) auth.getPrincipal();
        String token = tokenManager.createToken(user.getUsername());
        redisTemplate.opsForValue().set("token:"+token,"");
        ResponseUtil.out(res, R.ok().data("token", token));
    }

    /**
     * 登录失败
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.out(response, R.error());
    }
}
