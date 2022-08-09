package fan.security.dto;

import lombok.Data;

import java.util.ArrayList;

/**
 * @ClassName RoleConditionDTO
 * @Description TODO
 * @Author Fan
 * @Date 2022/5/10 15:24
 * @Version 1.0
 */
@Data
public class RoleConditionDTO {

    private String roleName;

    private ArrayList<String> roleIds;
}
