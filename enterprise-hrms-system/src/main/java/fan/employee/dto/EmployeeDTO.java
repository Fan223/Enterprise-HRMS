package fan.employee.dto;

import fan.security.entity.SysRoleDO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EmployeeDTO
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/14 11:22
 * @Version 1.0
 */
@Data
public class EmployeeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
    private String empId;
    /**
     * 员工姓名
     */
    private String empName;
    /**
     * 员工工号
     */
    private String empCode;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 身份证号
     */
    private String idcardNo;
    /**
     * 性别
     */
    private String gender;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 有效标志
     */
    private Integer valiFlag;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private List<String> roleIds;

    private List<SysRoleDO> sysRoleDOS = new ArrayList<>();

    private String currentPassword;

    private String newPassword;
}
