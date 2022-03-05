package com.jmc.codemaker.core;

import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.jmc.codemaker.common.Const;
import com.jmc.io.Files;
import com.jmc.lang.Strs;
import com.jmc.lang.Tries;

import java.io.File;
import java.util.Map;

/**
 * pom文件模板注入类
 * @since 1.0
 * @author Jmc
 */
public class PomTemplateInjectCore {
    /**
     * pom模板路径
     */
    private static final String POM_TEMPLATE_PATH = "templates/pom.xml.ftl";

    /**
     * 把模板注入pom.xml
     * @param modulePath 模块路径
     */
    public static void inject(String modulePath) {
        var pomPath = modulePath + "/pom.xml";
        var pomContent = Files.read(pomPath);

        // 如果pom文件里面存在parent标签，就从parent结束标签之后开始查找项目基本信息，否则直接从头找
        var startStr = pomContent.contains("<parent>") ? "</parent>" : "";

        // pom基本信息从开头字符串截取到 <dependencies> 标签截止
        String pomInfo = Strs.subExclusive(pomContent, startStr, "<dependencies>");
        var paramMap = Map.of(
                "groupId", (Object) Strs.subExclusive(pomInfo, "<groupId>", "</groupId>"),
                "artifactId", Strs.subExclusive(pomInfo, "<artifactId>", "</artifactId>"),
                "version", "1.0.0" // 版本号默认 1.0.0
        );

        var engine = new FreemarkerTemplateEngine() {{
            init(this.getConfigBuilder());
        }};

        Tries.tryThis(() -> engine.writer(paramMap, POM_TEMPLATE_PATH, new File(pomPath)));

        System.out.printf(Const.BLUE_MSG, "CodeMaker: pom文件模板注入完毕！\n");
    }
}
