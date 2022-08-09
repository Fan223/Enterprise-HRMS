package fan.security.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fan.employee.dto.EmployeeDTO;
import fan.security.dao.SysMenuDAO;
import fan.security.dto.SysMenuDTO;
import fan.security.dto.SysMenuDTOConvert;
import fan.security.entity.SysMenuDO;
import fan.security.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDAO, SysMenuDO> implements SysMenuService {

    @Resource
    private SysMenuDAO sysMenuDAO;

    @Resource
    private SysMenuDTOConvert sysMenuDTOConvert;

    /**
     * @Author Fan
     * @Description 根据员工id获取导航菜单列表id
     * @Date 2022/5/10 18:53
     * @param: empId
     * @return: java.util.List<java.lang.String>
     */
    @Override
    public List<String> getNavMenuIds(String empId) {
        return sysMenuDAO.getNavMenuIds(empId);
    }

    /**
     * @Author Fan
     * @Description 获取用户导航菜单列表
     * @Date 2022/5/10 18:53
     * @param: employeeDTO
     * @return: java.util.List<fan.security.dto.SysMenuDTO>
     */
    @Override
    public List<SysMenuDTO> getNavMenu(EmployeeDTO employeeDTO) {
        List<String> navMenuIds = getNavMenuIds(employeeDTO.getEmpId());
        List<SysMenuDO> sysMenuDOS = this.listByIds(navMenuIds);

        // 转树状结构
        List<SysMenuDO> sysMenuDOSTree = navBuildTree(sysMenuDOS);
        // 转DTO
        List<SysMenuDTO> sysMenuDTOS = convert(sysMenuDOSTree);
        return sysMenuDTOS;
    }

    /**
     * @Author Fan
     * @Description 获取所有菜单列表
     * @Date 2022/5/10 18:54
     * @return: java.util.List<fan.security.entity.SysMenuDO>
     */
    @Override
    public List<SysMenuDO> getMenuList(String valiFlag) {

        List<SysMenuDO> sysMenuDOS = this.list(new QueryWrapper<SysMenuDO>().eq(StringUtils.isNotBlank(valiFlag),"vali_flag", valiFlag)
                .orderByAsc("order_num"));

        return buildTree(sysMenuDOS);
    }

    /**
     * @Author Fan
     * @Description 将菜单列表转为树状结构
     * @Date 2022/5/10 18:54
     * @param: sysMenuDOS
     * @return: java.util.List<fan.security.entity.SysMenuDO>
     */
    private List<SysMenuDO> buildTree(List<SysMenuDO> sysMenuDOS){
        List<SysMenuDO> finalMenu = new ArrayList<>();

        for (SysMenuDO sysMenuDO : sysMenuDOS) {

            for (SysMenuDO menuDO : sysMenuDOS) {
                if (sysMenuDO.getMenuId().equals(menuDO.getParentId())) {
                    sysMenuDO.getChildren().add(menuDO);
                }
            }

            if (sysMenuDO.getParentId().equals("0")){
                finalMenu.add(sysMenuDO);
            }
        }
        return finalMenu;
    }

    private List<SysMenuDO> navBuildTree(List<SysMenuDO> sysMenuDOS){
        List<SysMenuDO> finalMenu = new ArrayList<>();

        for (SysMenuDO sysMenuDO : sysMenuDOS) {

            for (SysMenuDO menuDO : sysMenuDOS) {
                if (sysMenuDO.getMenuId().equals(menuDO.getParentId()) && menuDO.getType() == 1) {
                    sysMenuDO.getChildren().add(menuDO);
                }
            }

            if (sysMenuDO.getParentId().equals("0")){
                finalMenu.add(sysMenuDO);
            }
        }
        return finalMenu;
    }

    /**
     * @Author Fan
     * @Description 将树状菜单结构转为DTO
     * @Date 2022/5/10 18:54
     * @param: sysMenuDOSTree
     * @return: java.util.List<fan.security.dto.SysMenuDTO>
     */
    private List<SysMenuDTO> convert(List<SysMenuDO> sysMenuDOSTree) {
        List<SysMenuDTO> sysMenuDTOS = new ArrayList<>();

        sysMenuDOSTree.forEach(sysMenuDO -> {
            SysMenuDTO sysMenuDTO = sysMenuDTOConvert.convertToSysMenuDTO(sysMenuDO);

            if (sysMenuDO.getChildren().size() > 0){
                sysMenuDTO.setChildren(convert(sysMenuDO.getChildren()));
            }

            sysMenuDTOS.add(sysMenuDTO);
        });

        return sysMenuDTOS;
    }
}
