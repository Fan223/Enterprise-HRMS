package fan.security.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import controller.BaseController;
import fan.employee.service.EmployeeService;
import fan.security.dto.RoleConditionDTO;
import fan.security.entity.SysEmployeeRoleDO;
import fan.security.entity.SysRoleDO;
import fan.security.entity.SysRoleMenuDO;
import fan.security.service.SysEmployeeRoleService;
import fan.security.service.SysRoleMenuService;
import fan.security.service.SysRoleService;
import fan.utils.Const;
import fan.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Fan
 * @since 2022-05-03
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Resource
    private SysEmployeeRoleService sysEmployeeRoleService;

    @GetMapping("/getRoleList")
    @PreAuthorize("hasAnyAuthority('sys:role:list')")
    public Result getRoleList(String roleName, String valiFlag) {

        Page<SysRoleDO> sysRoleDOPage = sysRoleService.page(getPage(), new QueryWrapper<SysRoleDO>()
                .eq(StringUtils.isNotBlank(valiFlag), "vali_flag", valiFlag)
                .like(StringUtils.isNotBlank(roleName), "role_name", roleName));

        sysRoleDOPage.getRecords().forEach(sysRoleDO -> {
            List<SysRoleMenuDO> sysRoleMenuDOS = sysRoleMenuService.list(new QueryWrapper<SysRoleMenuDO>().eq("role_id", sysRoleDO.getRoleId()));
            List<String> menuIds = sysRoleMenuDOS.stream().map(p -> p.getMenuId()).collect(Collectors.toList());
            sysRoleDO.setMenuIds(menuIds);
        });
        return Result.success(sysRoleDOPage);
    }

    @PostMapping("/addRole")
    @PreAuthorize("hasAuthority('sys:role:add')")
    public Result addRole(@Validated @RequestBody SysRoleDO sysRoleDO) {
        sysRoleDO.setCreateTime(LocalDateTime.now());
        sysRoleDO.setUpdateTime(LocalDateTime.now());
        sysRoleDO.setValiFlag(Const.STATUS_ON);
        return Result.success("添加角色成功", sysRoleService.save(sysRoleDO));
    }

    @PutMapping("/updateRole")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public Result updateRole(@Validated @RequestBody SysRoleDO sysRoleDO) {
        sysRoleDO.setUpdateTime(LocalDateTime.now());
        sysRoleService.updateById(sysRoleDO);
        employeeService.clearUserAuthorityByRoleId(sysRoleDO.getRoleId());
        return Result.success("修改角色成功");
    }

    @DeleteMapping("/deleteRole")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @Transactional
    public Result deleteRole(@RequestBody RoleConditionDTO roleConditionDTO) {

        sysRoleService.removeByIds(roleConditionDTO.getRoleIds());

        // 删除中间表
        sysEmployeeRoleService.remove(new QueryWrapper<SysEmployeeRoleDO>().in("role_id", roleConditionDTO.getRoleIds()));
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenuDO>().in("role_id", roleConditionDTO.getRoleIds()));

        // 清除缓存
        roleConditionDTO.getRoleIds().forEach(roleId -> employeeService.clearUserAuthorityByRoleId(roleId));

        return Result.success("删除角色成功");
    }

    @PostMapping("/assignPermissions/{roleId}")
    @PreAuthorize("hasAuthority('sys:role:permission')")
    @Transactional
    public Result assignPermissions(@PathVariable("roleId") String roleId, @RequestBody String[] menuIds) {
        ArrayList<SysRoleMenuDO> sysRoleMenuDOS = new ArrayList<>();

        Arrays.stream(menuIds).forEach(menuId -> {
            SysRoleMenuDO sysRoleMenuDO = new SysRoleMenuDO();
            sysRoleMenuDO.setRoleId(roleId);
            sysRoleMenuDO.setMenuId(menuId);

            sysRoleMenuDOS.add(sysRoleMenuDO);
        });

        // 先删除原来的记录，再添加新的记录
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenuDO>().eq("role_id", roleId));
        sysRoleMenuService.saveBatch(sysRoleMenuDOS);

        // 清除缓存
        employeeService.clearUserAuthorityByRoleId(roleId);
        return Result.success("分配权限成功");
    }
}
