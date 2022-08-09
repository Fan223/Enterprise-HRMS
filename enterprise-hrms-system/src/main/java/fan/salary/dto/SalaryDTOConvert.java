package fan.salary.dto;

import fan.employee.entity.EmployeeDO;
import fan.salary.entity.SalaryDO;
import fan.utils.DateTimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName SalaryDTOConvert
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 10:57
 * @Version 1.0
 */
@Component
public class SalaryDTOConvert {

    public SalaryDTO convertToSalaryDTO(SalaryDO salaryDO, EmployeeDO employeeDO){
        SalaryDTO salaryDTO = new SalaryDTO();
        BeanUtils.copyProperties(salaryDO, salaryDTO);
        salaryDTO.setEmpName(employeeDO.getEmpName());
        salaryDTO.setCreateTime(DateTimeUtil.parseToYearMonth(salaryDO.getCreateTime()));
        return salaryDTO;
    }

    public SalaryDTO convertToSalaryDTO(SalaryDO salaryDO){
        SalaryDTO salaryDTO = new SalaryDTO();
        BeanUtils.copyProperties(salaryDO, salaryDTO);
        return salaryDTO;
    }

    public SalaryDO convertToSalaryDO(SalaryDTO salaryDTO){
        SalaryDO salaryDO = new SalaryDO();
        BeanUtils.copyProperties(salaryDTO, salaryDO);
        salaryDO.setUpdateTime(LocalDateTime.now());
        return salaryDO;
    }
}
