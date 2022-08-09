package fan.attendance.dto;

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
public class AttendanceConditionDTO {

    private String empName;
    private String startTime;
    private String endTime;
    private String attType;

    private List<String> attShIds;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
