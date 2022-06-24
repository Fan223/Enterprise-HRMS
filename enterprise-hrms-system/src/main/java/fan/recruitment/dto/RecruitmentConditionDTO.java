package fan.recruitment.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ConditionDTO
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/14 15:47
 * @Version 1.0
 */
@Data
public class RecruitmentConditionDTO {

    /** 招聘ID */
    private String recrId ;
    /** 招聘标题 */
    private String recrTitle ;
    /** 批量删除 Id 数组 */
    private List<String> recrIds;

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
