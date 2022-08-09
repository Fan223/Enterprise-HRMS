package fan.employee.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.employee.dto.EmployeeConditionDTO;
import fan.employee.dto.EmployeeDTO;
import fan.employee.entity.EmployeeDO;

/**
 * @ClassName EmployeeService
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 11:24
 * @Version 1.0
 */
public interface EmployeeService {
    
    

    EmployeeDTO getEmpByCode(String username);

    /**
     * @Author Fan
     * @Description 根据用户ID获取权限
     * @Date 2022/5/10 19:04
     * @param: empId
     * @return: java.lang.String
     */
    String getAuthority(String empId);

    /**
     * @Author Fan
     * @Description 根据用户名清除缓存
     * @Date 2022/5/10 19:05
     * @param: username
     */
    void clearUserAuthority(String username);

    /**
     * @Author Fan
     * @Description 根据角色ID清除缓存
     * @Date 2022/5/10 19:05
     * @param: roleId
     */
    void clearUserAuthorityByRoleId(String roleId);

    /**
     * @Author Fan
     * @Description 根据菜单ID清除缓存
     * @Date 2022/5/10 19:05
     * @param: menuId
     */
    void clearUserAuthorityByMenuId(String menuId);

    Page<EmployeeDTO> getEmployeeList(EmployeeConditionDTO conditionDTO);

    Integer addEmployee(EmployeeDTO employeeDTO);

    Integer updateEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(EmployeeConditionDTO conditionDTO);

    EmployeeDO getEmpById(String empId);

    EmployeeDO getEmpByName(String empName);

    Integer resetPassword(EmployeeDO employeeDO);
}
