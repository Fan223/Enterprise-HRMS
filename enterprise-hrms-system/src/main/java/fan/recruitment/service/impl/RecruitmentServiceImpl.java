package fan.recruitment.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.recruitment.dao.RecruitmentDAO;
import fan.recruitment.dto.RecruitmentConditionDTO;
import fan.recruitment.dto.RecruitmentDTO;
import fan.recruitment.dto.RecruitmentDTOConvert;
import fan.recruitment.entiry.RecruitmentDO;
import fan.recruitment.service.RecruitmentService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RecruitmentServiceImpl
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/6 20:43
 * @Version 1.0
 */
@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    @Resource
    private RecruitmentDAO recruitmentDAO;

    @Resource
    private RecruitmentDTOConvert recruitmentDTOConvert;

    @Override
    public Page<RecruitmentDTO> getRecruitment(RecruitmentConditionDTO conditionDTO) {

        QueryWrapper<RecruitmentDO> recruitmentDOQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(conditionDTO.getRecrTitle())){
            recruitmentDOQueryWrapper.like("recr_title", conditionDTO.getRecrTitle());
        }
        recruitmentDOQueryWrapper.eq("vali_flag", 1);

        Page<RecruitmentDO> page = new Page<>(conditionDTO.getPageNum(), conditionDTO.getPageSize());
        Page<RecruitmentDO> recruitmentDOPage = recruitmentDAO.selectPage(page, recruitmentDOQueryWrapper);

        Page<RecruitmentDTO> recruitmentDTOPage = new Page<>();
        List<RecruitmentDTO> recruitmentDTOS = new ArrayList<>();
        if (!ObjectUtils.isEmpty(recruitmentDOPage.getRecords())){
            recruitmentDOPage.getRecords().stream().forEach(recruitmentDO -> recruitmentDTOS.add(recruitmentDTOConvert.convertToRecruitmentDTO(recruitmentDO)));
        }

        BeanUtils.copyProperties(recruitmentDOPage, recruitmentDTOPage);
        recruitmentDTOPage.setRecords(recruitmentDTOS);
        return recruitmentDTOPage;
    }

    @Override
    public Integer addRecruitment(RecruitmentDTO recruitmentDTO) {
        RecruitmentDO recruitmentDO = recruitmentDTOConvert.convertToRecruitmentDO(recruitmentDTO);
        recruitmentDO.setRecrId(UUID.randomUUID().toString());
        recruitmentDO.setCreateTime(LocalDateTime.now());
        recruitmentDO.setUpdateTime(LocalDateTime.now());
        recruitmentDO.setValiFlag(1);

        return recruitmentDAO.insert(recruitmentDO);
    }

    @Override
    public Integer updateRecruitment(RecruitmentDTO recruitmentDTO) {
        RecruitmentDO recruitmentDO = recruitmentDTOConvert.convertToRecruitmentDO(recruitmentDTO);
        recruitmentDO.setUpdateTime(LocalDateTime.now());

        return recruitmentDAO.updateById(recruitmentDO);
    }

    @Override
    public Integer deleteRecruitment(RecruitmentConditionDTO conditionDTO) {
        return recruitmentDAO.deleteBatchIds(conditionDTO.getRecrIds());
    }
}
