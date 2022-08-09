package fan.employee.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.employee.dto.EmployeeConditionDTO;
import fan.employee.dto.EmployeeDTO;
import fan.employee.entity.EmployeeDO;
import fan.employee.service.EmployeeService;
import fan.security.entity.SysEmployeeRoleDO;
import fan.security.service.SysEmployeeRoleService;
import fan.security.service.SysRoleService;
import fan.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName EmployeeController
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 11:23
 * @Version 1.0
 */
@RestController
@Api(tags = "员工管理")
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysEmployeeRoleService sysEmployeeRoleService;

    /**
     * @Author Fan
     * @Description 查询员工信息
     * @Date 2022/5/10 18:57
     * @param: conditionDTO
     * @return: java.util.List<fan.employee.dto.EmployeeDTO>
     */
    @GetMapping("/getEmployeeList")
    @ApiOperation(value = "查询员工信息")
    @PreAuthorize("hasAuthority('employee:list')")
    public Result getEmployeeList(EmployeeConditionDTO conditionDTO){
        Page<EmployeeDTO> employeeDTOS = employeeService.getEmployeeList(conditionDTO);

        employeeDTOS.getRecords().forEach(employeeDTO -> {
            List<String> roleIds = sysRoleService.getRoleIdsByEmpId(employeeDTO.getEmpId());
            employeeDTO.setRoleIds(roleIds);
            employeeDTO.setSysRoleDOS(sysRoleService.getRoleListByRoleIds(roleIds));
        });
        return Result.success("查询员工信息成功", employeeDTOS);
    }

    /**
     * @Author Fan
     * @Description 新增员工信息
     * @Date 2022/5/10 18:58
     * @param: employeeDTO
     * @return: java.lang.Integer
     */
    @PostMapping("/addEmployee")
    @ApiOperation(value = "新增员工信息")
    @PreAuthorize("hasAuthority('employee:add')")
    public Result addEmployee(@RequestBody EmployeeDTO employeeDTO){
        // 根据身份证生成初始密码
        String password = employeeDTO.getIdcardNo().substring(employeeDTO.getIdcardNo().length() - 6);
        employeeDTO.setPassword(passwordEncoder.encode(password));

        return Result.success("新增员工成功", employeeService.addEmployee(employeeDTO));
    }

    /**
     * @Author Fan
     * @Description 修改员工信息
     * @Date 2022/5/10 18:58
     * @param: employeeDTO
     * @return: java.lang.Integer
     */
    @PutMapping("/updateEmployee")
    @ApiOperation(value = "修改员工信息")
    @PreAuthorize("hasAuthority('employee:update')")
    public Result updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return Result.success("修改员工成功", employeeService.updateEmployee(employeeDTO));
    }

    /**
     * @Author Fan
     * @Description 删除员工信息
     * @Date 2022/5/10 18:58
     * @param: conditionDTO
     * @return: java.lang.Integer
     */
    @DeleteMapping("/deleteEmployee")
    @ApiOperation(value = "删除员工信息")
    @PreAuthorize("hasAuthority('employee:delete')")
    public Result deleteEmployee(@RequestBody EmployeeConditionDTO conditionDTO){
        employeeService.deleteEmployee(conditionDTO);
        return Result.success("删除员工成功");
    }

    /**
     * @Author Fan
     * @Description 获取个人信息
     * @Date 2022/5/14 1:56
     * @param: principal
     * @return: fan.utils.Result
     */
    @GetMapping("/getUserInfo")
    public Result userInfo(Principal principal) {
        EmployeeDTO employeeDTO = employeeService.getEmpByCode(principal.getName());

        return Result.success(employeeDTO);
    }

    /**
     * @Author Fan
     * @Description 更新个人信息
     * @Date 2022/5/14 1:56
     * @param: employeeDTO
     * @return: fan.utils.Result
     */
    @PutMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestBody EmployeeDTO employeeDTO) {
        return Result.success("更新个人信息成功", employeeService.updateEmployee(employeeDTO));
    }

    /**
     * @Author Fan
     * @Description 修改密码
     * @Date 2022/5/14 1:56
     * @param: employeeDTO
     * @return: fan.utils.Result
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody EmployeeDTO employeeDTO) {

        if (!passwordEncoder.matches(employeeDTO.getCurrentPassword(), employeeDTO.getPassword())) {
            return Result.fail("原密码错误");
        }
        employeeDTO.setPassword(passwordEncoder.encode(employeeDTO.getNewPassword()));
        return Result.success("修改密码成功", employeeService.updateEmployee(employeeDTO));
    }
    /**
     * @Author Fan
     * @Description 分配角色
     * @Date 2022/5/11 1:34
     * @param: empId
     * @param: roleIds
     * @return: fan.utils.Result
     */
    @PostMapping("/assignRoles/{empId}")
    @PreAuthorize("hasAuthority('employee:role')")
    public  Result assignRoles(@PathVariable("empId") String empId, @RequestBody String[] roleIds) {
        ArrayList<SysEmployeeRoleDO> sysEmployeeRoleDOS = new ArrayList<>();

        Arrays.stream(roleIds).forEach(roleId -> {
            SysEmployeeRoleDO sysEmployeeRoleDO = new SysEmployeeRoleDO();
            sysEmployeeRoleDO.setEmpId(empId);
            sysEmployeeRoleDO.setRoleId(roleId);

            sysEmployeeRoleDOS.add(sysEmployeeRoleDO);
        });

        sysEmployeeRoleService.remove(new QueryWrapper<SysEmployeeRoleDO>().eq("emp_id", empId));
        sysEmployeeRoleService.saveBatch(sysEmployeeRoleDOS);
        // 清除缓存
        EmployeeDO employeeDO = employeeService.getEmpById(empId);
        employeeService.clearUserAuthority(employeeDO.getEmpName());

        return Result.success("分配角色成功");
    }

    /**
     * @Author Fan
     * @Description 重置密码
     * @Date 2022/5/11 1:34
     */
    @PostMapping("/resetPassword")
    @PreAuthorize("hasAuthority('employee:resetPassword')")
    public Result resetPassword(@RequestBody String empId) {
        EmployeeDO employeeDO = employeeService.getEmpById(empId);
        employeeDO.setPassword(passwordEncoder.encode(
                employeeDO.getIdcardNo().substring(employeeDO.getIdcardNo().length() - 6)));

        return Result.success("重置密码成功", employeeService.resetPassword(employeeDO));
    }

    @GetMapping("/getEmpByName")
    public Result getEmpByName(String empName) {

        return Result.success("通过用户名查询员工成功", employeeService.getEmpByName(empName));
    }
}
