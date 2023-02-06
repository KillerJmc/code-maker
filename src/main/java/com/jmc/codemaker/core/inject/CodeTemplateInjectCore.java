package com.jmc.codemaker.core.inject;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.jmc.codemaker.anno.CodeMaker;
import com.jmc.codemaker.common.Const;
import com.jmc.codemaker.config.DataSourceProperties;

import java.util.Map;

/**
 * 代码模板注入核心类
 * @since 1.0
 * @author Jmc
 */
public class CodeTemplateInjectCore {
    /**
     * 执行代码生成
     *
     * @param prop        数据源信息类
     * @param modulePath  模块路径
     * @param packageName 模块根包名
     * @param authorName  作者名称
     * @param anno        CodeMaker注解类
     */
    public static void make(DataSourceProperties prop,
                            String modulePath,
                            String packageName,
                            String authorName,
                            CodeMaker anno)
    {
        FastAutoGenerator
                // 连接数据库
                .create(prop.getUrl(), prop.getUsername(), prop.getPassword())
                // 全局配置
                .globalConfig(builder ->
                        builder
                                // java代码输出目录
                                .outputDir(modulePath + "/src/main/java")
                                // 设置作者名称
                                .author(authorName)
                                // 生成完不自动打开系统资源管理器
                                .disableOpenDir()
                )
                // 包配置
                .packageConfig(builder ->
                        builder
                                // 设置根包名
                                .parent(packageName)
                                // 设置数据层包名为pojo
                                .entity("pojo")
                                // 设置数据访问层包名为dao
                                .mapper("dao")
                )
                // 策略配置
                .strategyConfig(builder ->
                        builder
                                // 设置需要导入的数据库表名
                                .addInclude(anno.include())
                                // 设置需要排除的数据库表名
                                .addExclude(anno.exclude())
                                // 设置表前缀
                                .addTablePrefix(anno.tablePrefix())
                                // service类命名为xxxService
                                .serviceBuilder().formatServiceFileName("%sService")
                                // dao类命名为xxxDao
                                .mapperBuilder().formatMapperFileName("%sDao")
                                // 列名转化对象时用驼峰代替下划线
                                .entityBuilder().naming(NamingStrategy.underline_to_camel)

                )
                // 自定义注入配置
                .injectionConfig(builder ->
                        // 配置自定义注入
                        builder.customMap(Map.of(
                            // 是否自动注入层级架构
                            "autowired", anno.autowired()
                        ))
                )
                // 模板配置
                .templateConfig(builder -> {
                    // 模板前缀路径
                    var prefixPath = switch (anno.persistenceFramework()) {
                        case JPA -> Const.JPA_TEMPLATE_PATH;
                        case MYBATIS_PLUS -> Const.MYBATIS_PLUS_TEMPLATE_PATH;
                    };

                    builder
                            // entity类模板路径
                            .entity(prefixPath + "entity.java")
                            // dao类模板路径
                            .mapper(prefixPath + "dao.java")
                            // service类模板路径
                            .service(prefixPath + "service.java")
                            // serviceImpl类模板路径
                            .serviceImpl(prefixPath + "serviceImpl.java")
                            // controller类模板路径
                            .controller(prefixPath + "controller.java")
                            // 禁止产生Mapper.xml文件
                            .disable(TemplateType.XML);
                })
                // 设置模板引擎
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

        System.out.printf(Const.BLUE_MSG, "CodeMaker: 代码自动生成完毕！\n");
    }
}
