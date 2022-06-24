package fan.attendance.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName DepartmentDTO
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/14 10:44
 * @Version 1.0
 */
@Data
public class AttendanceDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    /** 考勤ID */
    private String attId ;
    /** 员工ID */
    private String empId ;
    /** 员工姓名 */
    private String empName ;
    /** 打卡时间 */
    private LocalDateTime puchTime;
    /** 考勤类型 */
    private Integer attType;
    /** 备注 */
    private String remark;
    /** 有效标志 */
    private Integer valiFlag;
    /** 创建时间 */
    private String createTime ;

    private LocalDateTime[] attTime;
}
