package fan.security.dto;

import fan.security.entity.SysMenuDO;
import org.springframework.stereotype.Component;

/**
 * @ClassName SysMenuDTOConvert
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/5/6 14:16
 * @Version 1.0
 */
@Component
public class SysMenuDTOConvert {

    public SysMenuDTO convertToSysMenuDTO(SysMenuDO sysMenuDO) {
        SysMenuDTO sysMenuDTO = new SysMenuDTO();

        sysMenuDTO.setId(sysMenuDO.getMenuId());
        sysMenuDTO.setName(sysMenuDO.getPermission());
        sysMenuDTO.setTitle(sysMenuDO.getMenuName());
        sysMenuDTO.setPath(sysMenuDO.getPath());
        sysMenuDTO.setIcon(sysMenuDO.getIcon());
        sysMenuDTO.setComponent(sysMenuDO.getComponent());

        return sysMenuDTO;
    }
}
