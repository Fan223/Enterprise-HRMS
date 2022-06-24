package fan.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName DepartmentDO
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/14 10:44
 * @Version 1.0
 */
@Data
@TableName(value = "attendance")
public class AttendanceDO implements Serializable {

    private static final long serialVersionUID = -1L;

    /** 考勤ID */
    @TableId
    private String attId ;
    /** 员工ID */
    private String empId ;
    /** 打卡时间 */
    private LocalDateTime puchTime;
    /** 考勤类型 */
    private Integer attType;
    /** 备注 */
    private String remark;
    /** 有效标志 */
    private Integer valiFlag;
    /** 创建时间 */
    private LocalDateTime createTime ;
    /** 更新时间 */
    private LocalDateTime updateTime ;
}
