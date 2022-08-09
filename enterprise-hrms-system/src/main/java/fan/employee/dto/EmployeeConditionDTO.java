package fan.employee.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ConditionDTO
 * @Description TODO
 * @Author Fan
 * @Date 2022/3/14 14:05
 * @Version 1.0
 */
@Data
public class EmployeeConditionDTO {

    private List<String> empIds;

    private String empName;

    private String empCode;

    private Integer valiFlag;

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
