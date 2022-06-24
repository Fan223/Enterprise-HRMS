package fan.security.handler;

import cn.hutool.json.JSONUtil;
import fan.utils.JwtUtil;
import fan.utils.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName LoginSuccessHandler
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/5/5 15:03
 * @Version 1.0
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("登录成功处理器");
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();

        // 生成 jwt，并放到响应头中
        String jwt = jwtUtil.generateJwt(authentication.getName());
        response.setHeader(jwtUtil.getHeader(), jwt);

        Result success = Result.success("登录成功");
        outputStream.write(JSONUtil.toJsonStr(success).getBytes(StandardCharsets.UTF_8));

        outputStream.flush();
        outputStream.close();
    }
}
