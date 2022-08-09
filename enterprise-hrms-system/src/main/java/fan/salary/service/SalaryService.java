package fan.salary.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.salary.dto.SalaryConditionDTO;
import fan.salary.dto.SalaryDTO;

/**
 * @ClassName SalaryService
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 10:43
 * @Version 1.0
 */
public interface SalaryService {

    Page<SalaryDTO> getSalary(SalaryConditionDTO conditionDTO);

    Integer addSalary(SalaryDTO salaryDTO);

    SalaryDTO getSalaryInfo(SalaryConditionDTO conditionDTO);
//
//    Integer updateSalary(SalaryDTO salaryDTO);
//
//    Integer deleteSalary(SalaryConditionDTO conditionDTO);
}
