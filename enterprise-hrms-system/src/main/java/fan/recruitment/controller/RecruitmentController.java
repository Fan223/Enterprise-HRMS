package fan.recruitment.controller;

import fan.recruitment.dto.RecruitmentConditionDTO;
import fan.recruitment.dto.RecruitmentDTO;
import fan.recruitment.service.RecruitmentService;
import fan.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName RecruitmentController
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/6 20:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/recruitment")
@Api(tags = "招聘管理")
public class RecruitmentController {

    @Resource
    private RecruitmentService recruitmentService;

    @GetMapping("/getRecruitment")
    public Result getRecruitment(RecruitmentConditionDTO conditionDTO){
        return Result.success(recruitmentService.getRecruitment(conditionDTO));
    }

    @PostMapping("/addRecruitment")
    public Result addRecruitment(@RequestBody RecruitmentDTO recruitmentDTO){
        return Result.success(recruitmentService.addRecruitment(recruitmentDTO));
    }

    @PutMapping("/updateRecruitment")
    public Result updateRecruitment(@RequestBody RecruitmentDTO recruitmentDTO){
        return Result.success(recruitmentService.updateRecruitment(recruitmentDTO));
    }

    @DeleteMapping("/deleteRecruitment")
    public Result deleteRecruitment(@RequestBody RecruitmentConditionDTO conditionDTO){
        return Result.success(recruitmentService.deleteRecruitment(conditionDTO));
    }
}
