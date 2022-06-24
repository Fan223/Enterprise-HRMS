package fan.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fan.security.dao.SysRoleDAO;
import fan.security.entity.SysRoleDO;
import fan.security.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Fan
 * @since 2022-05-03
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDAO, SysRoleDO> implements SysRoleService {

    @Resource
    private SysRoleDAO sysRoleDAO;

    @Override
    public List<String> getRoleIdsByEmpId(String empId) {
        List<String> roleIds = sysRoleDAO.getRoleIds(empId);

        return roleIds;
    }

    @Override
    public List<SysRoleDO> getRoleListByRoleIds(List<String> roleIds) {
        return roleIds.isEmpty() ? null : sysRoleDAO.selectBatchIds(roleIds);
    }

}
