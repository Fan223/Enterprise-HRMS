package fan.attendance.dto;

import fan.attendance.entity.AttendanceDO;
import fan.attendance.entity.AttendanceSheetDO;
import fan.employee.entity.EmployeeDO;
import fan.utils.DateTimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName AttendanceDTOConvert
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 10:57
 * @Version 1.0
 */
@Component
public class AttendanceDTOConvert {

    public AttendanceDTO convertToAttendanceDTO(AttendanceDO attendanceDO, EmployeeDO employeeDO){
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        BeanUtils.copyProperties(attendanceDO, attendanceDTO);
        attendanceDTO.setEmpName(employeeDO.getEmpName());
        attendanceDTO.setCreateTime(DateTimeUtil.parseToSimpleString(attendanceDO.getCreateTime()));
        return attendanceDTO;
    }

    public AttendanceDO convertToAttendanceDO(AttendanceDTO attendanceDTO){
        AttendanceDO attendanceDO = new AttendanceDO();
        BeanUtils.copyProperties(attendanceDTO, attendanceDO);
        return attendanceDO;
    }

    public AttendanceSheetDTO convertToAttendanceSheetDTO(AttendanceSheetDO attendanceSheetDO, EmployeeDO employeeDO){
        AttendanceSheetDTO attendanceSheetDTO = new AttendanceSheetDTO();
        BeanUtils.copyProperties(attendanceSheetDO, attendanceSheetDTO);

        attendanceSheetDTO.setEmpName(employeeDO.getEmpName());
        attendanceSheetDTO.setStartTime(DateTimeUtil.parseToSimpleString(attendanceSheetDO.getStartTime()));
        attendanceSheetDTO.setEndTime(DateTimeUtil.parseToSimpleString(attendanceSheetDO.getEndTime()));
        attendanceSheetDTO.setCreateTime(DateTimeUtil.parseToFullString(attendanceSheetDO.getCreateTime()));

        return attendanceSheetDTO;
    }

    public AttendanceSheetDO convertToAttendanceSheetDO(AttendanceSheetDTO attendanceSheetDTO){
        AttendanceSheetDO attendanceSheetDO = new AttendanceSheetDO();
        BeanUtils.copyProperties(attendanceSheetDTO, attendanceSheetDO);
        attendanceSheetDO.setUpdateTime(LocalDateTime.now());
        return attendanceSheetDO;
    }
}
