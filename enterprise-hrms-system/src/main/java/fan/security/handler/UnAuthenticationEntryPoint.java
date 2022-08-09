package fan.security.handler;

import cn.hutool.json.JSONUtil;
import fan.utils.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName UnAuthenticationEntryPoint
 * @Description TODO
 * @Author Fan
 * @Date 2022/5/6 6:34
 * @Version 1.0
 */
@Component
public class UnAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("认证失败处理器");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401，未认证
        ServletOutputStream outputStream = response.getOutputStream();

        Result fail = Result.fail(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage().equals("JWT异常") ? authException.getMessage() : "请先登录");
        outputStream.write(JSONUtil.toJsonStr(fail).getBytes(StandardCharsets.UTF_8));

        outputStream.flush();
        outputStream.close();
    }
}
