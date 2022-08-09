package fan.security.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SysMenuDTO
 * @Description TODO
 * @Author Fan
 * @Date 2022/5/6 13:43
 * @Version 1.0
 */
@Data
public class SysMenuDTO implements Serializable {

    private static final long serialVersionUID = -1L;
    private String id;
    private String name;
    private String title;
    private String icon;
    private String path;
    private String component;
    private List<SysMenuDTO> children;
}
