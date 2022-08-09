package fan.attendance.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.attendance.dto.AttendanceConditionDTO;
import fan.attendance.dto.AttendanceDTO;
import fan.attendance.dto.AttendanceSheetDTO;

/**
 * @ClassName AttendanceService
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 10:43
 * @Version 1.0
 */
public interface AttendanceService{

    Page<AttendanceDTO> getAttendance(AttendanceConditionDTO conditionDTO);

    Integer addAttendanceSheet(AttendanceSheetDTO attendanceSheetDTO);

    Integer updateAttendanceSheet(AttendanceSheetDTO attendanceSheetDTO);

    Integer deleteAttendanceSheet(AttendanceConditionDTO conditionDTO);

    Page<AttendanceSheetDTO> getAttendanceSheet(AttendanceConditionDTO conditionDTO);

    Integer checkAttendanceSheet(AttendanceSheetDTO attendanceSheetDTO);
}
