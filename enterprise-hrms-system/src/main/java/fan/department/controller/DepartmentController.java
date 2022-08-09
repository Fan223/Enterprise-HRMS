package fan.department.controller;

import fan.department.dto.DepartmentConditionDTO;
import fan.department.dto.DepartmentDTO;
import fan.department.service.DepartmentService;
import fan.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName DepartmentController
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 10:42
 * @Version 1.0
 */
@RestController
@Api(tags = "部门管理")
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @GetMapping("/getDepartment")
    @ApiOperation(value = "查询部门信息")
    public Result getDepartment(DepartmentConditionDTO conditionDTO){
        return Result.success(departmentService.getDepartment(conditionDTO));
    }

    @PostMapping("/addDepartment")
    @ApiOperation(value = "新增部门信息")
    public Result addDepartment(@RequestBody DepartmentDTO departmentDTO){
        return Result.success(departmentService.addDepartment(departmentDTO));
    }

    @PutMapping("/updateDepartment")
    @ApiOperation(value = "修改部门信息")
    public Result updateDepartment(@RequestBody DepartmentDTO departmentDTO){
        return Result.success(departmentService.updateDepartment(departmentDTO));
    }

    @DeleteMapping("/deleteDepartment")
    @ApiOperation(value = "删除部门信息")
    public Result deleteDepartment(@RequestBody DepartmentConditionDTO conditionDTO){
        return Result.success(departmentService.deleteDepartment(conditionDTO));
    }
}
