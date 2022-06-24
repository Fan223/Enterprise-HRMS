package fan.recruitment.entiry;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName RecruitmentDO
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/6 20:28
 * @Version 1.0
 */
@Data
@TableName(value = "recruitment")
public class RecruitmentDO implements Serializable {

    private static final long serialVersionUID = -1L;

    /** 招聘ID */
    @TableId
    private String recrId ;
    /** 招聘标题 */
    private String recrTitle ;
    /** 招聘要求 */
    private String recrRequire ;
    /** 招聘人数 */
    private Integer recrNumber ;
    /** 有效标志 */
    private Integer valiFlag;
    /** 创建时间 */
    private LocalDateTime createTime ;
    /** 更新时间 */
    private LocalDateTime updateTime ;
}
