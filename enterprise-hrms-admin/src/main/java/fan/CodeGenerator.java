package fan;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @ClassName CodeGenerator
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/5/3 11:00
 * @Version 1.0
 */
public class CodeGenerator {

    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://124.222.118.90:3306/enterprise_hrms?connectTimeout=5000&socketTimeout=10000&autoReconnect=true&failOverReadOnly=false&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "fan223");

    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称")).enableSwagger())
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(scanner.apply("请输入表名，多个表名用,隔开")))
                // 模板配置
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
