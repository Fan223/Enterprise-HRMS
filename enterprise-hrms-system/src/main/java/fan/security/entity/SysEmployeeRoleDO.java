package fan.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName EmployeeRoleDO
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/5/10 15:53
 * @Version 1.0
 */
@Data
@ToString
@TableName("sys_employee_role")
public class SysEmployeeRoleDO implements Serializable {

    private static final long serialVersionUID = -1L;
    
    @TableId
    private String empRoleId;

    private String empId;

    private String roleId;
}
