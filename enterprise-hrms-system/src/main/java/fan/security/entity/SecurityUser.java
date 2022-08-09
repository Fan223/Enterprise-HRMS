package fan.security.entity;

import fan.employee.dto.EmployeeDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName SecurityUser
 * @Description TODO
 * @Author Fan
 * @Date 2022/5/6 7:02
 * @Version 1.0
 */
@Data
public class SecurityUser implements UserDetails {

    private transient EmployeeDTO employeeDTO;
    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUser(EmployeeDTO employeeDTO, List<GrantedAuthority> authorities) {
        System.out.println("SecurityUser 权限类");
        this.employeeDTO = employeeDTO;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return employeeDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return employeeDTO.getEmpCode();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
