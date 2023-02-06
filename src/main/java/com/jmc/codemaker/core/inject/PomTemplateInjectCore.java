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
 * pom文件模板注入类
 * @since 1.0
 * @author Jmc
 */
public class PomTemplateInjectCore {
    /**
     * 把模板注入pom.xml
     * @param modulePath 模块路径
     * @param anno CodeMaker注解
     */
    public static void inject(String modulePath, CodeMaker anno) {
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

        // 通过判断持久化框架类型确定模块路径前缀
        var templatePrefixPath = switch (anno.persistenceFramework()) {
            case JPA -> Const.JPA_TEMPLATE_PATH;
            case MYBATIS_PLUS -> Const.MYBATIS_PLUS_TEMPLATE_PATH;
        };

        // pom模板文件路径
        var pomTemplateFilePath = templatePrefixPath + "pom.xml.ftl";

        Tries.tryThis(() -> engine.writer(paramMap, pomTemplateFilePath, new File(pomPath)));

        System.out.printf(Const.BLUE_MSG, "CodeMaker: pom文件模板注入完毕！\n");
    }
}
