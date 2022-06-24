package fan.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fan.security.entity.SysRoleDO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Fan
 * @since 2022-05-03
 */
public interface SysRoleService extends IService<SysRoleDO> {

    List<String> getRoleIdsByEmpId(String empId);

    List<SysRoleDO> getRoleListByRoleIds(List<String> roleIds);
}
