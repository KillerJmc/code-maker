package com.jmc.codemaker.anno;

import com.jmc.codemaker.config.CodeMakerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启自动生成代码的注解类
 * @since 1.0
 * @author Jmc
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CodeMakerConfiguration.class)
public @interface CodeMaker {
    /**
     * 需要导入的数据库表名（为空则导入所有表）
     * @return 被导入的表名数组
     */
    String[] include() default {};

    /**
     * 需要排除的数据库表名，和{@link #include}二选一
     * @return 被排除的表名数组
     * @since 1.6
     */
    String[] exclude() default {};

    /**
     * 数据库表前缀，前缀不加入JavaBean类名
     * @return 表前缀数组
     * @since 1.4
     */
    String[] tablePrefix() default {};

    /**
     * 自动注入上层属性（如Service注入上层Dao）
     * @return 是否自动注入
     * @since 1.1
     */
    boolean autowired() default true;
}
