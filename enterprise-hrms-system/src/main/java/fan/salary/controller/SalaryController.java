package fan.salary.controller;

import fan.salary.dto.SalaryConditionDTO;
import fan.salary.dto.SalaryDTO;
import fan.salary.service.SalaryService;
import fan.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName SalaryController
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/14 10:42
 * @Version 1.0
 */
@RestController
@Api(tags = "薪酬管理")
@RequestMapping("/salary")
public class SalaryController {
    @Resource
    private SalaryService salaryService;

    @GetMapping("/getSalary")
    @ApiOperation(value = "查询薪酬信息")
    public Result getSalary(SalaryConditionDTO conditionDTO){
        return Result.success(salaryService.getSalary(conditionDTO));
    }

    @PostMapping("/addSalary")
    @ApiOperation(value = "新增薪酬信息")
    public Result addSalary(@RequestBody SalaryDTO salaryDTO){
        return Result.success(salaryService.addSalary(salaryDTO));
    }

    @GetMapping("/getSalaryInfo")
    @ApiOperation(value = "查询薪酬信息")
    public Result getSalaryInfo(SalaryConditionDTO conditionDTO){
        return Result.success(salaryService.getSalaryInfo(conditionDTO));
    }
//
//    @PutMapping("/updateSalary")
//    @ApiOperation(value = "修改部门信息")
//    public Result updatesalary(@RequestBody SalaryDTO salaryDTO){
//        return Result.success(salaryService.updateSalary(salaryDTO));
//    }
//
//    @DeleteMapping("/deleteSalary")
//    @ApiOperation(value = "删除部门信息")
//    public Result deletesalary(@RequestBody SalaryConditionDTO conditionDTO){
//        return Result.success(salaryService.deleteSalary(conditionDTO));
//    }
}
