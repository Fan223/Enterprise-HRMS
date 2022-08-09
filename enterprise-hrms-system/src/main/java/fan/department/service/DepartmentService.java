package fan.department.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.department.dto.DepartmentConditionDTO;
import fan.department.dto.DepartmentDTO;

/**
 * @ClassName DepartmentService
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 10:43
 * @Version 1.0
 */
public interface DepartmentService{

    Page<DepartmentDTO> getDepartment(DepartmentConditionDTO conditionDTO);

    Integer addDepartment(DepartmentDTO departmentDTO);

    Integer updateDepartment(DepartmentDTO departmentDTO);

    Integer deleteDepartment(DepartmentConditionDTO conditionDTO);
}
