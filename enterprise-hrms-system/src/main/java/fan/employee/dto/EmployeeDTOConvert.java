package fan.employee.dto;

import fan.employee.entity.EmployeeDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName EmployeeDTOConvert
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/14 11:26
 * @Version 1.0
 */
@Component
public class EmployeeDTOConvert {

    public EmployeeDTO convertToEmployeeDTO(EmployeeDO employeeDO){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employeeDO, employeeDTO);
        return employeeDTO;
    }

    public EmployeeDO convertToEmployeeDO(EmployeeDTO employeeDTO){
        EmployeeDO employeeDO = new EmployeeDO();
        BeanUtils.copyProperties(employeeDTO, employeeDO);
        return employeeDO;
    }
}
