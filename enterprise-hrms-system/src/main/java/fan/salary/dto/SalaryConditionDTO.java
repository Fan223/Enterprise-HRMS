package fan.salary.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ConditionDTO
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/14 15:40
 * @Version 1.0
 */
@Data
public class SalaryConditionDTO {

    private String empId;
    private String empName;
    private String salMonth;

    private List<String> salIds;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
