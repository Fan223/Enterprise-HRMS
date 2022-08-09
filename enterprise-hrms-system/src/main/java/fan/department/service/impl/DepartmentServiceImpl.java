package fan.department.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.department.dao.DepartmentDAO;
import fan.department.dto.DepartmentConditionDTO;
import fan.department.dto.DepartmentDTO;
import fan.department.dto.DepartmentDTOConvert;
import fan.department.entity.DepartmentDO;
import fan.department.service.DepartmentService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DepartmentServiceImpl
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 10:43
 * @Version 1.0
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentDAO departmentDAO;

    @Resource
    private DepartmentDTOConvert departmentDTOConvert;

    @Override
    public Page<DepartmentDTO> getDepartment(DepartmentConditionDTO conditionDTO) {
        QueryWrapper<DepartmentDO> departmentDOQueryWrapper = new QueryWrapper<>();
        departmentDOQueryWrapper.like(StringUtils.isNotBlank(conditionDTO.getDeptName()),
                "dept_name", conditionDTO.getDeptName());

        Page<DepartmentDO> page = new Page<>(conditionDTO.getPageNum(), conditionDTO.getPageSize());
        Page<DepartmentDO> departmentDOPage = departmentDAO.selectPage(page, departmentDOQueryWrapper);

        Page<DepartmentDTO> departmentDTOPage = new Page<>();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        if (!ObjectUtils.isEmpty(departmentDOPage.getRecords())){
            departmentDOPage.getRecords().stream().forEach(departmentDO ->
                    departmentDTOS.add(departmentDTOConvert.convertToDepartmentDTO(departmentDO)));
        }

        BeanUtils.copyProperties(departmentDOPage, departmentDTOPage);
        departmentDTOPage.setRecords(departmentDTOS);
        return departmentDTOPage;
    }

    @Override
    public Integer addDepartment(DepartmentDTO departmentDTO) {
        DepartmentDO departmentDO = departmentDTOConvert.convertToDepartmentDO(departmentDTO);

        departmentDO.setDeptId(UUID.randomUUID().toString());
        departmentDO.setCreateTime(LocalDateTime.now());
        departmentDO.setUpdateTime(LocalDateTime.now());
        departmentDO.setValiFlag(1);

        return departmentDAO.insert(departmentDO);
    }

    @Override
    public Integer updateDepartment(DepartmentDTO departmentDTO) {
        DepartmentDO departmentDO = departmentDTOConvert.convertToDepartmentDO(departmentDTO);
        departmentDO.setUpdateTime(LocalDateTime.now());

        return departmentDAO.updateById(departmentDO);
    }

    @Override
    public Integer deleteDepartment(DepartmentConditionDTO conditionDTO) {

        return departmentDAO.deleteBatchIds(conditionDTO.getDeptIds());
    }
}
