package fan.department.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ConditionDTO
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 15:40
 * @Version 1.0
 */
@Data
public class DepartmentConditionDTO {

    private String deptId;
    private String deptName;

    private List<String> deptIds;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
