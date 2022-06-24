package fan.config;

import fan.security.filter.CaptchaFilter;
import fan.security.filter.JwtAuthenticationFilter;
import fan.security.handler.*;
import fan.security.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/5/4 10:33
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private CaptchaFilter captchaFilter;

    @Resource
    private UnAccessDeniedHandler unAccessDeniedHandler;

    @Resource
    private UnAuthenticationEntryPoint unAuthenticationEntryPoint;

    @Resource
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Resource
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }

    public static final String[] AUTH_WHITELIST = {
            "/login",
            "/logout",
            "/api/**",
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域访问，关闭csrf防护
        http.csrf().disable().cors();
        // 拦截规则
        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated();
        // 登录配置
        http.formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler);
        // 添加验证码过滤器在登录之前，添加jwt过滤器
        http.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        // 添加自定义异常处理器
        http.exceptionHandling()
                .authenticationEntryPoint(unAuthenticationEntryPoint)
                .accessDeniedHandler(unAccessDeniedHandler);
        // 添加自定义注销处理器
        http.logout().logoutSuccessHandler(customLogoutSuccessHandler);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
