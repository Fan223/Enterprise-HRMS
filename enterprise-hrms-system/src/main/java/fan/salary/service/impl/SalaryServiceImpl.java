package fan.salary.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.employee.entity.EmployeeDO;
import fan.employee.service.EmployeeService;
import fan.salary.dao.SalaryDAO;
import fan.salary.dto.SalaryConditionDTO;
import fan.salary.dto.SalaryDTO;
import fan.salary.dto.SalaryDTOConvert;
import fan.salary.entity.SalaryDO;
import fan.salary.service.SalaryService;
import fan.utils.DateTimeUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SalaryServiceImpl
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 10:43
 * @Version 1.0
 */
@Service
public class SalaryServiceImpl implements SalaryService {

    @Resource
    private SalaryDAO salaryDAO;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private SalaryDTOConvert salaryDTOConvert;

    @Override
    public Page<SalaryDTO> getSalary(SalaryConditionDTO conditionDTO) {

        EmployeeDO employeeDO = new EmployeeDO();
        if (StringUtils.isNotBlank(conditionDTO.getEmpName())) {
            employeeDO = employeeService.getEmpByName(conditionDTO.getEmpName());
            if (ObjectUtils.isEmpty(employeeDO)) {
                return null;
            }
        }

        QueryWrapper<SalaryDO> salaryDOQueryWrapper = new QueryWrapper<>();
        salaryDOQueryWrapper.eq(StringUtils.isNotBlank(employeeDO.getEmpId()), "emp_id", employeeDO.getEmpId());
        if (StringUtils.isNotBlank(conditionDTO.getSalMonth())){
            salaryDOQueryWrapper.like("create_time", DateTimeUtil.parseToYearMonth(DateTimeUtil.parseToLocalDateTime(conditionDTO.getSalMonth())));
        }

        Page<SalaryDO> page = new Page<>(conditionDTO.getPageNum(), conditionDTO.getPageSize());
        Page<SalaryDO> salaryDOPage = salaryDAO.selectPage(page, salaryDOQueryWrapper);

        Page<SalaryDTO> salaryDTOPage = new Page<>();
        List<SalaryDTO> salaryDTOS = new ArrayList<>();
        if (!ObjectUtils.isEmpty(salaryDOPage.getRecords())){
            salaryDOPage.getRecords().stream().forEach(salaryDO ->
                    salaryDTOS.add(salaryDTOConvert.convertToSalaryDTO(salaryDO, employeeService.getEmpById(salaryDO.getEmpId()))));
        }

        BeanUtils.copyProperties(salaryDOPage, salaryDTOPage);
        salaryDTOPage.setRecords(salaryDTOS);
        return salaryDTOPage;
    }

    @Override
    public Integer addSalary(SalaryDTO salaryDTO) {
        SalaryDO SalaryDO = salaryDTOConvert.convertToSalaryDO(salaryDTO);
        EmployeeDO employeeDO = employeeService.getEmpByName(salaryDTO.getEmpName());
        SalaryDO.setEmpId(employeeDO.getEmpId());

        SalaryDO.setSalId(UUID.randomUUID().toString());
        SalaryDO.setCreateTime(LocalDateTime.now());
        SalaryDO.setValiFlag(1);

        return salaryDAO.insert(SalaryDO);
    }

    @Override
    public SalaryDTO getSalaryInfo(SalaryConditionDTO conditionDTO) {
        SalaryDO salaryDO = salaryDAO.selectOne(new QueryWrapper<SalaryDO>().eq("emp_id", conditionDTO.getEmpId())
                .orderByDesc("create_time").last("limit 1"));

        SalaryDTO salaryDTO = salaryDTOConvert.convertToSalaryDTO(salaryDO);
        return salaryDTO;
    }
//
//    @Override
//    public Integer updateSalary(SalaryDTO SalaryDTO) {
//        SalaryDO SalaryDO = SalaryDTOConvert.convertToSalaryDO(SalaryDTO);
//        SalaryDO.setUpdateTime(LocalDateTime.now());
//
//        return SalaryDAO.updateById(SalaryDO);
//    }
//
//    @Override
//    public Integer deleteSalary(SalaryConditionDTO conditionDTO) {
//
//        return SalaryDAO.deleteBatchIds(conditionDTO.getDeptIds());
//    }
}
