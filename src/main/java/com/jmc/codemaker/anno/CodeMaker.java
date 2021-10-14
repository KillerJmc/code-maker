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
     * 需要导入的数据库表名
     */
    String[] tables();

    /**
     * 数据库表前缀，前缀不加入JavaBean类名
     * @since 1.4
     */
    String[] tablePrefix() default {};

    /**
     * 自动注入层级属性（@autowired）
     * @since 1.1
     */
    boolean autowired() default false;
}
