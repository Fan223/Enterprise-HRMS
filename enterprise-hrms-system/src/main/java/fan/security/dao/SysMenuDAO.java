package fan.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fan.security.entity.SysMenuDO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Fan
 * @since 2022-05-03
 */
public interface SysMenuDAO extends BaseMapper<SysMenuDO> {

    List<String> getNavMenuIds(String empId);
}
