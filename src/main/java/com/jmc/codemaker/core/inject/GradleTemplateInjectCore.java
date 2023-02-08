package com.jmc.codemaker.core.inject;

import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.jmc.codemaker.anno.CodeMaker;
import com.jmc.codemaker.common.Const;
import com.jmc.io.Files;
import com.jmc.lang.Strs;
import com.jmc.lang.Tries;

import java.io.File;
import java.util.Map;

/**
 * gradle文件模板注入类
 * @since 2.0
 * @author Jmc
 */
public class GradleTemplateInjectCore {
    /**
     * 把模板注入gradle文件
     * @param modulePath 模块路径
     * @param anno CodeMaker注解
     */
    public static void inject(String modulePath, CodeMaker anno) {
        var buildGradlePath = modulePath + "/build.gradle";
        var buildGradleContent = Files.read(buildGradlePath);

        // 从gradle原配置中获取group属性
        var group = (Object) Strs.subExclusive(buildGradleContent, "group = '", "'");

        var paramMap = Map.of(
                "group", group,
                "version", "1.0.0" // 版本号默认 1.0.0
        );

        var engine = new FreemarkerTemplateEngine() {{
            init(this.getConfigBuilder());
        }};

        // 通过判断持久化框架类型确定模块路径前缀
        var templatePrefixPath = switch (anno.persistenceFramework()) {
            case JPA -> Const.JPA_TEMPLATE_PATH;
            case MYBATIS_PLUS -> Const.MYBATIS_PLUS_TEMPLATE_PATH;
        };

        // build.gradle模板文件路径
        var buildGradleTemplateFilePath = templatePrefixPath + "build.gradle.ftl";

        Tries.tryThis(() -> engine.writer(paramMap, buildGradleTemplateFilePath, new File(buildGradlePath)));

        // settings.gradle路径
        var settingsGradlePath = modulePath + "/settings.gradle";

        // 将其中的单引号替换成双引号
        Files.out(
                Files.read(settingsGradlePath).replace("'", "\""),
                settingsGradlePath
        );

        System.out.printf(Const.BLUE_MSG, "CodeMaker: gradle文件模板注入完毕！\n");
    }
}
