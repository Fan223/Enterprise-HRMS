package fan.security.filter;

import fan.employee.dto.EmployeeDTO;
import fan.employee.service.EmployeeService;
import fan.security.exception.CustomException;
import fan.security.handler.UnAuthenticationEntryPoint;
import fan.security.service.impl.UserDetailsServiceImpl;
import fan.utils.JwtUtil;
import fan.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName JwtAuthenticationFilter
 * @Description TODO
 * @Author Fan
 * @Date 2022/5/6 4:51
 * @Version 1.0
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Resource
    private UnAuthenticationEntryPoint unAuthenticationEntryPoint;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("JWT过滤器" + request.getHeader(jwtUtil.getHeader()));

        String jwt = request.getHeader(jwtUtil.getHeader());
        if (StringUtils.isBlank(jwt)) {
            System.out.println("JWT为空");
            chain.doFilter(request, response);
            return;
        }

        Jws<Claims> claimsJws = jwtUtil.parseJwt(jwt);
        if (claimsJws == null) {
            CustomException customException = new CustomException("JWT异常");
            unAuthenticationEntryPoint.commence(request, response, customException);
            throw customException;
        }
        String username = claimsJws.getBody().getSubject();
        EmployeeDTO employeeDTO = employeeService.getEmpByCode(username);

        List<GrantedAuthority> authorities;
        if (redisUtil.hasKey("GrantedAuthority:" + employeeDTO.getEmpName())) {
            System.out.println("JWT过滤器从redis中获取权限");
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) redisUtil.get("GrantedAuthority:" + employeeDTO.getEmpName()));
        } else {
            System.out.println("JWT过滤器从数据库中获取权限");
            authorities = userDetailsService.getAuthorities(employeeDTO);
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, claimsJws, authorities);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }
}
