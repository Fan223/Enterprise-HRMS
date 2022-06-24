package fan.department.dto;

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
public class DepartmentDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    /** 部门ID */
    private String deptId ;
    /** 部门名称 */
    private String deptName ;
    /** 部门职位 */
    private String position;
    /** 有效标志 */
    private Integer valiFlag ;
    /** 创建时间 */
    private LocalDateTime createTime ;
}
