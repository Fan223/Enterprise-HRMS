package fan.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName SysRoleMenuDO
 * @Description TODO
 * @Author Fan
 * @Date 2022/5/9 7:57
 * @Version 1.0
 */
@TableName("sys_role_menu")
@Data
@ToString
public class SysRoleMenuDO implements Serializable {


    private static final long serialVersionUID = 1L;

    @TableId
    private String roleMenuId;

    private String roleId;

    private String menuId;
}
