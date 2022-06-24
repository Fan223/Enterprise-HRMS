package fan.department.dto;

import fan.department.entity.DepartmentDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName DepartmentDTOConvert
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/14 10:57
 * @Version 1.0
 */
@Component
public class DepartmentDTOConvert {

    public DepartmentDTO convertToDepartmentDTO(DepartmentDO departmentDO){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(departmentDO, departmentDTO);
        return departmentDTO;
    }

    public DepartmentDO convertToDepartmentDO(DepartmentDTO departmentDTO){
        DepartmentDO departmentDO = new DepartmentDO();
        BeanUtils.copyProperties(departmentDTO, departmentDO);
        return departmentDO;
    }
}
