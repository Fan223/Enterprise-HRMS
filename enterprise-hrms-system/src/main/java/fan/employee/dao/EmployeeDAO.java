package fan.employee.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fan.employee.entity.EmployeeDO;
import fan.security.entity.SysRoleDO;

import java.util.List;

/**
 * @ClassName EmployeeDAO
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 11:25
 * @Version 1.0
 */
public interface EmployeeDAO extends BaseMapper<EmployeeDO> {

    List<SysRoleDO> getAuthority(String empId);

    List<String> getEmpIdsByRoleId(String roleId);

    List<EmployeeDO> getEmpsByMenuId(String menuId);

    void deleteEmployee(List<String> empIds);
}
