package fan.security.handler;

import cn.hutool.json.JSONUtil;
import fan.utils.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName UnAccessDeniedHandler
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/5/6 6:33
 * @Version 1.0
 */
@Component
public class UnAccessDeniedHandler implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("权限失败处理器");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403，未授权，禁止访问
        ServletOutputStream outputStream = response.getOutputStream();

        Result fail = Result.fail(HttpServletResponse.SC_FORBIDDEN, "没有权限访问");
        outputStream.write(JSONUtil.toJsonStr(fail).getBytes(StandardCharsets.UTF_8));

        outputStream.flush();
        outputStream.close();
    }
}
