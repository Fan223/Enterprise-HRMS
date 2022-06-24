package fan.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fan.security.entity.SysRoleDO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Fan
 * @since 2022-05-03
 */
public interface SysRoleDAO extends BaseMapper<SysRoleDO> {

    List<String> getRoleIds(String empId);
}
