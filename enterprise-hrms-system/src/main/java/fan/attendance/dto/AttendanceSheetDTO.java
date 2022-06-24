package fan.attendance.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AttendanceSheetDTO
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/5/18 22:36
 * @Version 1.0
 */
@Data
public class AttendanceSheetDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String attShId;

    private String empName;

    private Integer attType;

    private String startTime;

    private String endTime;

    private Integer checkStatus;

    private Integer valiFlag;

    private String remark;

    private String createTime;
}
