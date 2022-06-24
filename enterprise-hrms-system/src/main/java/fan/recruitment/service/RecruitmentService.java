package fan.recruitment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.recruitment.dto.RecruitmentConditionDTO;
import fan.recruitment.dto.RecruitmentDTO;

/**
 * @ClassName RecruitmentService
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/6 20:43
 * @Version 1.0
 */
public interface RecruitmentService {

    Page<RecruitmentDTO> getRecruitment(RecruitmentConditionDTO conditionDTO);

    Integer addRecruitment(RecruitmentDTO recruitmentDTO);

    Integer updateRecruitment(RecruitmentDTO recruitmentDTO);

    Integer deleteRecruitment(RecruitmentConditionDTO conditionDTO);
}
