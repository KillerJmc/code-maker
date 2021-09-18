package com.jmc.codemaker.core;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Map;

/**
 * CodeMaker核心类
 * @since 1.0
 * @author Jmc
 */
public class CodeMakerCore {
    public static void make(DataSourceConfig dsc, String modulePath, String packageName,
                            String authorName, String[] tableNames, boolean autowired) {
        // 自动代码生成器
        var mpg = new AutoGenerator();

        // 设置dataSource
        mpg.setDataSource(dsc);

        // 设置全局参数
        mpg.setGlobalConfig(new GlobalConfig() {{
            // java代码输出目录
            setOutputDir(modulePath + "/src/main/java");
            // 设置作者名称
            setAuthor(authorName);
            // service类统一命名为xxxService
            setServiceName("%sService");
            // 生成完不自动打开系统资源管理器
            setOpen(false);
        }});

        // 设置模板：禁止产生Mapper.xml文件
        mpg.setTemplate(new TemplateConfig().disable(TemplateType.XML));

        // 设置包配置
        mpg.setPackageInfo(new PackageConfig() {{
            // 设置根包名
            setParent(packageName);
            // 设置数据层包名为pojo
            setEntity("pojo");
        }});

        // 设置策略配置
        mpg.setStrategy(new StrategyConfig() {{
            // 设置需要导入的数据库表名
            setInclude(tableNames);
            // 设置列名转化为对象时驼峰代替下划线
            setColumnNaming(NamingStrategy.underline_to_camel);
        }});

        // 设置自定义属性注入
        mpg.setCfg(new InjectionConfig() {
            @Override
            public void initMap() {
                // 用户是否自动注入
                this.setMap(Map.of("autowired", autowired));
            }
        });

        // 设置模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 开始自动生成代码
        mpg.execute();
    }
}
