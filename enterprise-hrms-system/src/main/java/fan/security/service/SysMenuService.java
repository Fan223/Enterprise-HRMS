package fan.security.service;


import com.baomidou.mybatisplus.extension.service.IService;
import fan.employee.dto.EmployeeDTO;
import fan.security.dto.SysMenuDTO;
import fan.security.entity.SysMenuDO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Fan
 * @since 2022-05-03
 */
public interface SysMenuService extends IService<SysMenuDO> {

    /**
     * @Author 赵俊杰
     * @Description 根据员工id获取导航菜单列表Id
     * @Date 2022/5/10 18:51
     * @param: empId
     * @return: java.util.List<java.lang.String>
     */
    List<String> getNavMenuIds(String empId);

    /**
     * @Author 赵俊杰
     * @Description 获取用户导航菜单列表
     * @Date 2022/5/10 18:52
     * @param: employeeDTO
     * @return: java.util.List<fan.security.dto.SysMenuDTO>
     */
    List<SysMenuDTO> getNavMenu(EmployeeDTO employeeDTO);

    /**
     * @Author 赵俊杰
     * @Description 获取所有菜单列表
     * @Date 2022/5/10 18:52
     * @return: java.util.List<fan.security.entity.SysMenuDO>
     */
    List<SysMenuDO> getMenuList(String valiFlag);
}
