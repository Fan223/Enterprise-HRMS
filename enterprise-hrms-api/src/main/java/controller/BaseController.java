package controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.ServletRequestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/5/9 7:20
 * @Version 1.0
 */
public class BaseController {

    @Resource
    HttpServletRequest request;

    public Page getPage() {
        int current = ServletRequestUtils.getIntParameter(request, "current", 10);
        int size = ServletRequestUtils.getIntParameter(request, "size", 10);

        return new Page(current, size);
    }
}
