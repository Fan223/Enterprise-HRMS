package fan.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName AttendanceSheetDO
 * @Description TODO
 * @Author Fan
 * @Date 2022/5/18 22:32
 * @Version 1.0
 */
@Data
@TableName("attendance_sheet")
public class AttendanceSheetDO implements Serializable {

    private static final long serialVersionUID = -1L;
    
    @TableId
    private String attShId;

    private String empId;

    private Integer attType;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer checkStatus;

    private String remark;

    private Integer valiFlag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
