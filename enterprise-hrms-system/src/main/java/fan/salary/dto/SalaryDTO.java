package fan.salary.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName DepartmentDTO
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/14 10:44
 * @Version 1.0
 */
@Data
public class SalaryDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    /** 薪酬ID */
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
    private String createTime ;

    private String empName;
}
