package fan.recruitment.dto;

import fan.recruitment.entiry.RecruitmentDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;

/**
 * @ClassName RecruitmentDTOConvert
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/7 9:24
 * @Version 1.0
 */
@Controller
public class RecruitmentDTOConvert {

    public RecruitmentDTO convertToRecruitmentDTO(RecruitmentDO recruitmentDO){
        RecruitmentDTO recruitmentDTO = new RecruitmentDTO();
        BeanUtils.copyProperties(recruitmentDO, recruitmentDTO);
        return recruitmentDTO;
    }

    public RecruitmentDO convertToRecruitmentDO(RecruitmentDTO recruitmentDTO){
        RecruitmentDO recruitmentDO = new RecruitmentDO();
        BeanUtils.copyProperties(recruitmentDTO, recruitmentDO);
        return recruitmentDO;
    }
}
