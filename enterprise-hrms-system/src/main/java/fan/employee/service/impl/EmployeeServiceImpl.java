package fan.employee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.employee.dao.EmployeeDAO;
import fan.employee.dto.EmployeeConditionDTO;
import fan.employee.dto.EmployeeDTO;
import fan.employee.dto.EmployeeDTOConvert;
import fan.employee.entity.EmployeeDO;
import fan.employee.service.EmployeeService;
import fan.security.entity.SysMenuDO;
import fan.security.entity.SysRoleDO;
import fan.security.service.SysMenuService;
import fan.utils.Const;
import fan.utils.RedisUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName EmployeesericeImpl
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 11:25
 * @Version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeDAO employeeDAO;

    @Resource
    private EmployeeDTOConvert employeeDTOConvert;

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public Page<EmployeeDTO> getEmployeeList(EmployeeConditionDTO conditionDTO) {

        QueryWrapper<EmployeeDO> employeeDOQueryWrapper = new QueryWrapper<>();

        if (!StringUtils.isBlank(conditionDTO.getEmpName())) {
            employeeDOQueryWrapper.like("emp_name", conditionDTO.getEmpName());
        }
        if (!StringUtils.isBlank(conditionDTO.getEmpCode())) {
            employeeDOQueryWrapper.eq("emp_code", conditionDTO.getEmpCode());
        }
        if (conditionDTO.getValiFlag() != null) {
            employeeDOQueryWrapper.eq("vali_flag", conditionDTO.getValiFlag());
        }

        Page<EmployeeDO> page = new Page<>(conditionDTO.getPageNum(), conditionDTO.getPageSize());
        Page<EmployeeDO> employeeDOPage = employeeDAO.selectPage(page, employeeDOQueryWrapper);

        Page<EmployeeDTO> employeeDTOPage = new Page<>();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        if (!ObjectUtils.isEmpty(employeeDOPage.getRecords())) {
            employeeDOPage.getRecords().stream().forEach(employeeDO -> employeeDTOS.add(employeeDTOConvert.convertToEmployeeDTO(employeeDO)));
        }

        BeanUtils.copyProperties(employeeDOPage, employeeDTOPage);
        employeeDTOPage.setRecords(employeeDTOS);
        return employeeDTOPage;
    }

    @Override
    public Integer addEmployee(EmployeeDTO employeeDTO) {
        EmployeeDO employeeDO = employeeDTOConvert.convertToEmployeeDO(employeeDTO);
        employeeDO.setCreateTime(LocalDateTime.now());
        employeeDO.setUpdateTime(LocalDateTime.now());
        employeeDO.setValiFlag(1);

        // 根据身份证号生成工号
        employeeDO.setEmpCode(employeeDTO.getIdcardNo().substring(6, 14));
        // 设置默认头像
        employeeDO.setAvatar(Const.AVATAR);

        return employeeDAO.insert(employeeDO);
    }

    @Override
    public Integer updateEmployee(EmployeeDTO employeeDTO) {
        EmployeeDO employeeDO = employeeDTOConvert.convertToEmployeeDO(employeeDTO);
        employeeDO.setUpdateTime(LocalDateTime.now());

        return employeeDAO.updateById(employeeDO);
    }

    @Override
    public Integer resetPassword(EmployeeDO employeeDO) {

        employeeDO.setUpdateTime(LocalDateTime.now());

        return employeeDAO.updateById(employeeDO);
    }

    @Override
    public EmployeeDO getEmpByName(String empName) {
        return employeeDAO.selectOne(new QueryWrapper<EmployeeDO>().eq("emp_name", empName));
    }

    @Override
    public EmployeeDO getEmpById(String empId) {
        return employeeDAO.selectById(empId);
    }

    @Override
    public void deleteEmployee(EmployeeConditionDTO conditionDTO) {
        employeeDAO.deleteEmployee(conditionDTO.getEmpIds());
    }

    @Override
    public EmployeeDTO getEmpByCode(String username) {
        QueryWrapper<EmployeeDO> queryWrapper = new QueryWrapper<EmployeeDO>().eq("emp_code", username);
        EmployeeDO employeeDO = employeeDAO.selectOne(queryWrapper);
        if (employeeDO == null) {
            return null;
        } else {
            EmployeeDTO employeeDTO = employeeDTOConvert.convertToEmployeeDTO(employeeDO);
            return employeeDTO;
        }
    }

    @Override
    public String getAuthority(String empId) {

        String authority = "";

        // 获取角色列表
        List<SysRoleDO> sysRoleDOS = employeeDAO.getAuthority(empId);
        if (sysRoleDOS.size() > 0) {
            String roleCodes = sysRoleDOS.stream().map(sysRoleDO -> "ROLE_" + sysRoleDO.getCode()).collect(Collectors.joining(","));
            authority = roleCodes.concat(",");
        }

        // 获取菜单权限列表
        List<String> navMenuIds = sysMenuService.getNavMenuIds(empId);
        if (navMenuIds.size() > 0) {
            List<SysMenuDO> sysMenuDOS = sysMenuService.list(new QueryWrapper<SysMenuDO>().eq("vali_flag", 1).in("menu_id", navMenuIds));
            String permissions = sysMenuDOS.stream().map(sysMenuDO -> sysMenuDO.getPermission()).collect(Collectors.joining(","));
            authority = authority.concat(permissions);
        }
        return authority;
    }

    @Override
    public void clearUserAuthority(String username) {
        redisUtil.del("GrantedAuthority:" + username);
    }

    @Override
    public void clearUserAuthorityByRoleId(String roleId) {
        List<String> empIds = employeeDAO.getEmpIdsByRoleId(roleId);
        if (empIds.size() > 0) {
            employeeDAO.selectBatchIds(empIds).forEach(employeeDO -> {
                System.out.println("根据角色Id清除用户权限缓存，姓名为：" + employeeDO.getEmpName());
                clearUserAuthority(employeeDO.getEmpName());
            });
        }
    }

    @Override
    public void clearUserAuthorityByMenuId(String menuId) {
        employeeDAO.getEmpsByMenuId(menuId).forEach(employeeDO -> {
            if (employeeDO != null) {
                System.out.println("根据菜单Id清除用户权限缓存，姓名为：" + employeeDO.getEmpName());
                clearUserAuthority(employeeDO.getEmpName());
            }
        });
    }
}
