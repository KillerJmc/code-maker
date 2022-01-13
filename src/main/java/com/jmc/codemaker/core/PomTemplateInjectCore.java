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
        String pomPath = modulePath + "/pom.xml";

        String pomInfo = Strs.subExclusive(Files.read(pomPath), "</parent>", "<dependencies>");
        var paramMap = Map.of(
                "groupId", (Object) Strs.subExclusive(pomInfo, "<groupId>", "</groupId>"),
                "artifactId", Strs.subExclusive(pomInfo, "<artifactId>", "</artifactId>"),
                "version", "1.0.0", // 版本号默认 1.0.0
                "name", Strs.subExclusive(pomInfo, "<name>", "</name>")
        );

        var engine = new FreemarkerTemplateEngine() {{
            init(this.getConfigBuilder());
        }};

        Tries.tryThis(() -> engine.writer(paramMap, POM_TEMPLATE_PATH, new File(pomPath)));

        System.out.printf(Const.BLUE_MSG, "CodeMaker: pom文件模板注入完毕！\n");
    }
}
