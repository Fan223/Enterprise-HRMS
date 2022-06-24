package fan.security.service.impl;

import fan.employee.dto.EmployeeDTO;
import fan.employee.service.EmployeeService;
import fan.security.entity.SecurityUser;
import fan.security.exception.CustomException;
import fan.utils.RedisUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/5/6 6:50
 * @Version 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetailsServiceImpl 实现类");
        EmployeeDTO employeeDTO = employeeService.getEmpByCode(username);
        if (employeeDTO == null) {
            throw new CustomException("用户名不存在");
        }

        List<GrantedAuthority> authorities;
        if (redisUtil.hasKey("GrantedAuthority:" + employeeDTO.getEmpName())) {
            System.out.println("UserDetailsServiceImpl从redis中获取权限");
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) redisUtil.get("GrantedAuthority:" + employeeDTO.getEmpName()));
        } else {
            System.out.println("UserDetailsServiceImpl从数据库中获取权限");
            authorities = getAuthorities(employeeDTO);
        }
        return new SecurityUser(employeeDTO, authorities);
    }

    /**
     * @Author 赵俊杰
     * @Description 从数据库获取用户权限，并将权限存入redis
     * @Date 2022/5/10 18:55
     * @param: employeeDTO
     * @return: java.util.List<org.springframework.security.core.GrantedAuthority>
     */
    public List<GrantedAuthority> getAuthorities(EmployeeDTO employeeDTO) {

        String authority = employeeService.getAuthority(employeeDTO.getEmpId());
        redisUtil.set("GrantedAuthority:" + employeeDTO.getEmpName(), authority);

        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
        return authorities;
    }
}
