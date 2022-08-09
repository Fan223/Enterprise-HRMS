package fan.attendance.controller;

import fan.attendance.dto.AttendanceConditionDTO;
import fan.attendance.dto.AttendanceSheetDTO;
import fan.attendance.service.AttendanceService;
import fan.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName AttendanceController
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 10:42
 * @Version 1.0
 */
@RestController
@Api(tags = "考勤管理")
@RequestMapping("/attendance")
public class AttendanceController {
    @Resource
    private AttendanceService attendanceService;

    @GetMapping("/getAttendance")
    @ApiOperation(value = "查询考勤信息")
    public Result getAttendance(AttendanceConditionDTO conditionDTO){

        return Result.success(attendanceService.getAttendance(conditionDTO));
    }

    @GetMapping("/getAttendanceSheet")
    @ApiOperation(value = "查询考勤单信息")
    public Result getAttendanceSheet(AttendanceConditionDTO conditionDTO){
        return Result.success(attendanceService.getAttendanceSheet(conditionDTO));
    }

    @PostMapping("/addAttendanceSheet")
    @ApiOperation(value = "新增考勤单信息")
    public Result addAttendanceSheet(@RequestBody AttendanceSheetDTO attendanceSheetDTO){
        return Result.success(attendanceService.addAttendanceSheet(attendanceSheetDTO));
    }

    @PutMapping("/updateAttendanceSheet")
    @ApiOperation(value = "修改考勤单信息")
    public Result updateAttendanceSheet(@RequestBody AttendanceSheetDTO attendanceSheetDTO){
        System.out.println(attendanceSheetDTO);
        return Result.success(attendanceService.updateAttendanceSheet(attendanceSheetDTO));
    }

    @DeleteMapping("/deleteAttendanceSheet")
    @ApiOperation(value = "删除考勤单信息")
    public Result deleteAttendanceSheet(@RequestBody AttendanceConditionDTO conditionDTO){

        return Result.success(attendanceService.deleteAttendanceSheet(conditionDTO));
    }

    @PostMapping("/checkAttendanceSheet")
    @ApiOperation(value = "审核考勤单信息")
    public Result checkAttendanceSheet(@RequestBody AttendanceSheetDTO attendanceSheetDTO){

        return Result.success(attendanceService.checkAttendanceSheet(attendanceSheetDTO));
    }
}
