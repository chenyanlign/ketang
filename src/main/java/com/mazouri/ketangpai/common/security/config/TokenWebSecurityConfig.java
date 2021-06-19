package com.mazouri.ketangpai.common.security.config;


import com.mazouri.ketangpai.common.security.exception.MyAccessDeniedHandler;
import com.mazouri.ketangpai.common.security.filter.TokenAuthenticationFilter;
import com.mazouri.ketangpai.common.security.filter.TokenLoginFilter;
import com.mazouri.ketangpai.common.security.interceptor.MyAccessDecisionManager;
import com.mazouri.ketangpai.common.security.interceptor.MyFilterInvocationSecurityMetadataSource;
import com.mazouri.ketangpai.common.security.security.DefaultPasswordEncoder;
import com.mazouri.ketangpai.common.security.security.TokenLogoutHandler;
import com.mazouri.ketangpai.common.security.security.TokenManager;
import com.mazouri.ketangpai.common.security.security.UnauthorizedEntryPoint;
import com.mazouri.ketangpai.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


/**
 * <p>
 * Security配置类
 * </p>
 *
 * @author mazouri
 * @since 2020-11-18
 */
@Configuration
@EnableWebSecurity
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private TokenManager tokenManager;
    private DefaultPasswordEncoder defaultPasswordEncoder;
    private RedisTemplate redisTemplate;
    private MyFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
    private MyAccessDecisionManager accessDecisionManager;
    private MyAccessDeniedHandler myAccessDeniedHandler;
    private SysRoleService sysRoleService;

    @Autowired
    public TokenWebSecurityConfig(UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder,
                                  TokenManager tokenManager, RedisTemplate redisTemplate, MyFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource,
                                  MyAccessDecisionManager accessDecisionManager,MyAccessDeniedHandler myAccessDeniedHandler,SysRoleService sysRoleService) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.filterInvocationSecurityMetadataSource = filterInvocationSecurityMetadataSource;
        this.accessDecisionManager = accessDecisionManager;
        this.myAccessDeniedHandler = myAccessDeniedHandler;
        this.sysRoleService = sysRoleService;
    }

    /**
     * 配置设置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler)
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and().csrf().disable().cors().and()
                .formLogin().and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(filterInvocationSecurityMetadataSource); //动态获取url权限配置
                o.setAccessDecisionManager(accessDecisionManager); //权限判断
                return o;
            }
        })
                .and().logout().logoutUrl("/user/logout")
                .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate))
                .and()
                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate,sysRoleService)).httpBasic();
    }

    /**
     * 密码处理
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    /**
     * 配置哪些请求不拦截
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/course/**","/error","/favicon.ico","/store/**","/admin/**",
                "/**/homework/**","/file/**","/**/topic/**","/**/comment/**", "/**/notice/**","/**/document/**",
                "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"
        );
    }
}