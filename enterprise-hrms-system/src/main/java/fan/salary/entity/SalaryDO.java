package fan.salary.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName DepartmentDO
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/14 10:44
 * @Version 1.0
 */
@Data
@TableName(value = "salary")
public class SalaryDO implements Serializable {

    private static final long serialVersionUID = -1L;

    /** 薪酬ID */
    @TableId
    private String salId ;
    /** 员工ID */
    private String empId ;
    /** 基本工资 */
    private BigDecimal baseSal;
    /** 绩效工资 */
    private BigDecimal meritpay ;
    /** 有效标志 */
    private Integer valiFlag ;
    /** 创建时间 */
    private LocalDateTime createTime ;
    /** 更新时间 */
    private LocalDateTime updateTime ;
}
