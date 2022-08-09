package fan.security.filter;

import fan.security.exception.CustomException;
import fan.security.handler.LoginFailureHandler;
import fan.utils.Const;
import fan.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CaptchaFilter
 * @Description TODO
 * @Author Fan
 * @Date 2022/5/5 15:29
 * @Version 1.0
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("验证码过滤器");
        if (request.getRequestURI().equals("/hrms/login") && request.getMethod().equals("POST")) {
            try {
                // 校验验证码
                validate(request);
                System.out.println("验证码校验通过");
                filterChain.doFilter(request, response);
            } catch (CustomException e) {
                // 交给认证失败处理器处理
                System.out.println("验证码错误");
                loginFailureHandler.onAuthenticationFailure(request, response, e);
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }

    // 校验验证码
    private void validate(HttpServletRequest request) {
        String captcha = request.getParameter("captcha");
        String token = request.getParameter("token");

        if (StringUtils.isBlank(captcha) || StringUtils.isBlank(token)) {
            throw new CustomException("验证码不能为空");
        }

        if (!captcha.equals(redisUtil.hashGet(Const.CAPTCHA_KEY, token))) {
            throw new CustomException("验证码错误");
        }

        redisUtil.hashDel(Const.CAPTCHA_KEY, token);
    }
}
