package fan.security.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fan.employee.dto.EmployeeDTO;
import fan.employee.service.EmployeeService;
import fan.security.dto.SysMenuDTO;
import fan.security.entity.SysMenuDO;
import fan.security.entity.SysRoleMenuDO;
import fan.security.service.SysMenuService;
import fan.security.service.SysRoleMenuService;
import fan.utils.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Fan
 * @since 2022-05-03
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    /**
     * @Author Fan
     * @Description 获取导航菜单
     * @Date 2022/5/10 18:59
     * @param: principal
     * @return: fan.utils.Result
     */
    @GetMapping("/getNavMenu")
    public Result getNavMenu(Principal principal) {
        // 获取权限信息
        EmployeeDTO employeeDTO = employeeService.getEmpByCode(principal.getName());
        String authority = employeeService.getAuthority(employeeDTO.getEmpId());
        String[] permissionList = StringUtils.tokenizeToStringArray(authority, ",");

        // 获取导航栏菜单
        List<SysMenuDTO> menuList = sysMenuService.getNavMenu(employeeService.getEmpByCode(principal.getName()));
        return Result.success("获取导航栏菜单成功", MapUtil.builder().put("menuList", menuList).put("permissionList", permissionList).build());
    }

    /**
     * @Author Fan
     * @Description 获取所有菜单列表
     * @Date 2022/5/10 18:59
     * @param: principal
     * @return: fan.utils.Result
     */
    @GetMapping("/getMenuList")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public Result getMenuList(String valiFlag) {
        return Result.success("获取菜单列表成功", sysMenuService.getMenuList(valiFlag));
    }

    /**
     * @Author Fan
     * @Description 新增菜单
     * @Date 2022/5/10 18:59
     * @param: sysMenuDO
     * @return: fan.utils.Result
     */
    @PostMapping("/addMenu")
    @PreAuthorize("hasAuthority('sys:menu:add')")
    public Result addMenu(@Validated @RequestBody SysMenuDO sysMenuDO) {
        if (org.apache.commons.lang3.StringUtils.isBlank(sysMenuDO.getParentId())) {
            sysMenuDO.setParentId("0");
        }
        sysMenuDO.setMenuId(UUID.randomUUID().toString());
        sysMenuDO.setCreateTime(LocalDateTime.now());
        sysMenuDO.setUpdateTime(LocalDateTime.now());
        return Result.success("添加菜单成功", sysMenuService.save(sysMenuDO));
    }

    /**
     * @Author Fan
     * @Description 修改菜单
     * @Date 2022/5/10 18:59
     * @param: sysMenuDO
     * @return: fan.utils.Result
     */
    @PutMapping("/updateMenu")
    @PreAuthorize("hasAuthority('sys:menu:update')")
    public Result updateMenu(@Validated @RequestBody SysMenuDO sysMenuDO) {

        sysMenuDO.setUpdateTime(LocalDateTime.now());
        sysMenuService.updateById(sysMenuDO);
        // 清除所有与菜单相关的缓存
        employeeService.clearUserAuthorityByMenuId(sysMenuDO.getMenuId());
        return Result.success("更新菜单成功");
    }

    /**
     * @Author Fan
     * @Description 删除菜单
     * @Date 2022/5/10 18:59
     * @param: menuId
     * @return: fan.utils.Result
     */
    @DeleteMapping("/deleteMenu/{menuId}")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    public Result deleteMenu(@PathVariable("menuId") String menuId) {
        long parent_id = sysMenuService.count(new QueryWrapper<SysMenuDO>().eq("parent_id", menuId));
        if (parent_id > 0) {
            return Result.fail("该菜单下存在子菜单，不能删除");
        }

        sysMenuService.removeById(menuId);
        // 清除所有与菜单相关的缓存
        employeeService.clearUserAuthorityByMenuId(menuId);

        // 同步删除中间关联表
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenuDO>().eq("menu_id", menuId));
        return Result.success("删除菜单成功");
    }
}
