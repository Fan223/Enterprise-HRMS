package fan.department.entity;

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
@TableName(value = "department")
public class DepartmentDO implements Serializable {

    private static final long serialVersionUID = -1L;

    /** 部门ID */
    @TableId
    private String deptId ;
    /** 部门名称 */
    private String deptName ;
    /** 部门职位 */
    private String position;
    /** 有效标志 */
    private Integer valiFlag ;
    /** 创建时间 */
    private LocalDateTime createTime ;
    /** 更新时间 */
    private LocalDateTime updateTime ;
}
