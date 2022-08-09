package fan.attendance.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.attendance.dao.AttendanceDAO;
import fan.attendance.dao.AttendanceSheetDAO;
import fan.attendance.dto.AttendanceConditionDTO;
import fan.attendance.dto.AttendanceDTO;
import fan.attendance.dto.AttendanceDTOConvert;
import fan.attendance.dto.AttendanceSheetDTO;
import fan.attendance.entity.AttendanceDO;
import fan.attendance.entity.AttendanceSheetDO;
import fan.attendance.service.AttendanceService;
import fan.employee.entity.EmployeeDO;
import fan.employee.service.EmployeeService;
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
 * @ClassName AttendanceServiceImpl
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 10:43
 * @Version 1.0
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Resource
    private AttendanceDAO attendanceDAO;

    @Resource
    private AttendanceSheetDAO attendanceSheetDAO;

    @Resource
    private EmployeeService employeeService;


    @Resource
    private AttendanceDTOConvert attendanceDTOConvert;

    @Override
    public Page<AttendanceDTO> getAttendance(AttendanceConditionDTO conditionDTO) {

        EmployeeDO employeeDO = new EmployeeDO();
        if (StringUtils.isNotBlank(conditionDTO.getEmpName())) {
            employeeDO = employeeService.getEmpByName(conditionDTO.getEmpName());
            if (ObjectUtils.isEmpty(employeeDO)) {
                return null;
            }
        }

        QueryWrapper<AttendanceDO> attendanceDOQueryWrapper = new QueryWrapper<>();
        attendanceDOQueryWrapper.eq(StringUtils.isNotBlank(employeeDO.getEmpId()), "emp_id", employeeDO.getEmpId())
                .eq(StringUtils.isNotBlank(conditionDTO.getAttType()), "att_type", conditionDTO.getAttType())
                .between(StringUtils.isNotBlank(conditionDTO.getStartTime()), "create_time", conditionDTO.getStartTime(), conditionDTO.getEndTime());

        Page<AttendanceDO> page = new Page<>(conditionDTO.getPageNum(), conditionDTO.getPageSize());
        Page<AttendanceDO> attendanceDOPage = attendanceDAO.selectPage(page, attendanceDOQueryWrapper);

        Page<AttendanceDTO> attendanceDTOPage = new Page<>();
        List<AttendanceDTO> attendanceDTOS = new ArrayList<>();
        if (!ObjectUtils.isEmpty(attendanceDOPage.getRecords())) {
            attendanceDOPage.getRecords().stream().forEach(attendanceDO ->
                    attendanceDTOS.add(attendanceDTOConvert.convertToAttendanceDTO(attendanceDO, employeeService.getEmpById(attendanceDO.getEmpId()))));
        }

        BeanUtils.copyProperties(attendanceDOPage, attendanceDTOPage);
        attendanceDTOPage.setRecords(attendanceDTOS);
        return attendanceDTOPage;
    }

    @Override
    public Page<AttendanceSheetDTO> getAttendanceSheet(AttendanceConditionDTO conditionDTO) {

        EmployeeDO employeeDO = new EmployeeDO();
        if (StringUtils.isNotBlank(conditionDTO.getEmpName())) {
            employeeDO = employeeService.getEmpByName(conditionDTO.getEmpName());
            if (ObjectUtils.isEmpty(employeeDO)) {
                return null;
            }
        }

        QueryWrapper<AttendanceSheetDO> attendanceSheetDOQueryWrapper = new QueryWrapper<>();
        attendanceSheetDOQueryWrapper.eq(StringUtils.isNotBlank(employeeDO.getEmpId()), "emp_id", employeeDO.getEmpId())
                .between(StringUtils.isNotBlank(conditionDTO.getStartTime()), "create_time", conditionDTO.getStartTime(), conditionDTO.getEndTime());

        Page<AttendanceSheetDO> page = new Page<>(conditionDTO.getPageNum(), conditionDTO.getPageSize());
        Page<AttendanceSheetDO> attendanceSheetDOPage = attendanceSheetDAO.selectPage(page, attendanceSheetDOQueryWrapper);

        Page<AttendanceSheetDTO> attendanceSheetDTOPage = new Page<>();
        List<AttendanceSheetDTO> attendanceSheetDTOS = new ArrayList<>();
        attendanceSheetDOPage.getRecords().forEach(attendanceSheetDO ->
                attendanceSheetDTOS.add(attendanceDTOConvert.convertToAttendanceSheetDTO(attendanceSheetDO, employeeService.getEmpById(attendanceSheetDO.getEmpId()))));

        BeanUtils.copyProperties(attendanceSheetDOPage, attendanceSheetDTOPage);
        attendanceSheetDTOPage.setRecords(attendanceSheetDTOS);
        return attendanceSheetDTOPage;
    }

    @Override
    public Integer checkAttendanceSheet(AttendanceSheetDTO attendanceSheetDTO) {
        AttendanceSheetDO attendanceSheetDO = attendanceDTOConvert.convertToAttendanceSheetDO(attendanceSheetDTO);
        attendanceSheetDAO.updateById(attendanceSheetDO);

        if (attendanceSheetDTO.getCheckStatus() == 1){
            LocalDateTime startTime = DateTimeUtil.parseToLocalDate(attendanceSheetDTO.getStartTime());
            LocalDateTime endTime = DateTimeUtil.parseToLocalDate(attendanceSheetDTO.getEndTime());

            AttendanceDO attendanceDO = new AttendanceDO();
            attendanceDO.setEmpId(employeeService.getEmpByName(attendanceSheetDTO.getEmpName()).getEmpId());
            attendanceDO.setAttType(attendanceSheetDTO.getAttType());
            attendanceDO.setRemark(attendanceSheetDTO.getRemark());
            attendanceDO.setValiFlag(1);
            attendanceDO.setUpdateTime(LocalDateTime.now());

            while (startTime.isBefore(endTime.plusDays(1))) {
                attendanceDO.setAttId(UUID.randomUUID().toString());
                attendanceDO.setCreateTime(startTime);
                attendanceDAO.insert(attendanceDO);

                startTime = startTime.plusDays(1);
            }
        }

        return 1;
    }

    @Override
    public Integer addAttendanceSheet(AttendanceSheetDTO attendanceSheetDTO) {

        AttendanceSheetDO attendanceSheetDO = attendanceDTOConvert.convertToAttendanceSheetDO(attendanceSheetDTO);
        EmployeeDO employeeDO = employeeService.getEmpByName(attendanceSheetDTO.getEmpName());
        attendanceSheetDO.setEmpId(employeeDO.getEmpId());

        attendanceSheetDO.setAttShId(UUID.randomUUID().toString());
        attendanceSheetDO.setCreateTime(LocalDateTime.now());
        attendanceSheetDO.setCheckStatus(0);
        attendanceSheetDO.setValiFlag(1);
        attendanceSheetDO.setStartTime(DateTimeUtil.parseToLocalDateTime(attendanceSheetDTO.getStartTime()));
        attendanceSheetDO.setEndTime(DateTimeUtil.parseToLocalDateTime(attendanceSheetDTO.getEndTime()));

        return attendanceSheetDAO.insert(attendanceSheetDO);
    }

    @Override
    public Integer updateAttendanceSheet(AttendanceSheetDTO attendanceSheetDTO) {

        AttendanceSheetDO attendanceSheetDO = attendanceDTOConvert.convertToAttendanceSheetDO(attendanceSheetDTO);
        attendanceSheetDO.setCheckStatus(0);

        if (attendanceSheetDTO.getStartTime().length() > 22) {
            attendanceSheetDO.setStartTime(DateTimeUtil.parseToLocalDateTime(attendanceSheetDTO.getStartTime()));
            attendanceSheetDO.setEndTime(DateTimeUtil.parseToLocalDateTime(attendanceSheetDTO.getEndTime()));
        }
        return attendanceSheetDAO.updateById(attendanceSheetDO);
    }

    @Override
    public Integer deleteAttendanceSheet(AttendanceConditionDTO conditionDTO) {

        return attendanceSheetDAO.deleteBatchIds(conditionDTO.getAttShIds());
    }
}
