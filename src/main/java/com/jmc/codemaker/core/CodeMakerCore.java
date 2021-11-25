package com.jmc.codemaker.core;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.jmc.codemaker.common.Const;
import com.jmc.codemaker.config.DataSourceProperties;

import java.util.Map;

/**
 * CodeMaker核心类
 * @since 1.0
 * @author Jmc
 */
public class CodeMakerCore {
    /**
     * 执行代码生成
     * @param prop 数据源信息类
     * @param modulePath 模块路径
     * @param packageName 模块根包名
     * @param authorName 作者名称
     * @param tableNames 需映射的表名
     * @param tablePrefix 表前缀
     * @param autowired 是否自动注入
     */
    public static void make(DataSourceProperties prop, String modulePath, String packageName,
                            String authorName, String[] tableNames, String[] tablePrefix, boolean autowired) {
        FastAutoGenerator
                .create(prop.getUrl(), prop.getUsername(), prop.getPassword())   // 连接数据库
                .globalConfig(builder ->                                         // -------- 全局配置 ---------
                        builder.outputDir(modulePath + "/src/main/java")         // java代码输出目录
                               .author(authorName)                               // 设置作者名称
                               .disableOpenDir()                                 // 生成完不自动打开系统资源管理器
                )
                .packageConfig(builder ->                                        // -------- 包配置  ----------
                        builder.parent(packageName)                              // 设置根包名
                               .entity("pojo")                                   // 设置数据层包名为pojo
                               .mapper("dao")                                    // 设置数据访问层包名为dao
                )
                .strategyConfig(builder ->                                       // -------- 策略配置 ---------
                        builder.addInclude(tableNames)                           // 设置需要导入的数据库表名
                               .addTablePrefix(tablePrefix)                      // 设置表前缀
                               .serviceBuilder()
                                   .formatServiceFileName("%sService")           // service类命名为xxxService
                               .mapperBuilder()
                                   .formatMapperFileName("%sDao")                // dao类命名为xxxDao
                               .entityBuilder()
                                   .naming(NamingStrategy.underline_to_camel)    // 列名转化对象时用驼峰代替下划线

                )
                .injectionConfig(builder ->                                      // ------ 自定义注入配置-------
                        builder.customMap(Map.of(                                // 配置自定义注入
                            "autowired", autowired                           // map属性：是否自动注入层级架构
                        ))
                )                                                                // --------------------------
                .templateConfig(builder -> builder.disable(TemplateType.XML))    // 模板配置 -> 禁止产生Mapper.xml文件
                .templateEngine(new FreemarkerTemplateEngine())                  // 模板引擎配置 -> 设置模板引擎
                .execute();                                                      // 执行

        System.out.printf(Const.BLUE_MSG, "CodeMaker: 代码自动生成完毕！\n");
    }
}
