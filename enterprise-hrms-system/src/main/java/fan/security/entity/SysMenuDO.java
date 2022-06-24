package fan.security.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Fan
 * @since 2022-05-03
 */
@TableName("sys_menu")
@ApiModel(value = "SysMenu对象", description = "")
@Data
@ToString
public class SysMenuDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单ID")
    @TableId
    private String menuId;

    @ApiModelProperty("父菜单ID，一级菜单为0")
    private String parentId;

    @ApiModelProperty("菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    @ApiModelProperty("菜单URL")
    private String path;

    @ApiModelProperty("权限编码(多个用逗号分隔，如：user:list,user:create)")
    @NotBlank(message = "权限编码不能为空")
    private String permission;

    @ApiModelProperty("菜单组件")
    private String component;

    @ApiModelProperty("类型     0：目录   1：菜单   2：按钮")
    @NotNull(message = "类型不能为空")
    private Integer type;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("排序号")
    private Integer orderNum;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("有效标志")
    private Integer valiFlag;

    @TableField(exist = false)
    private List<SysMenuDO> children = new ArrayList<>();
}
