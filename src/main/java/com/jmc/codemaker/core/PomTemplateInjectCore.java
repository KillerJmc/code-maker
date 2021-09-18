package com.jmc.codemaker.core;

import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.jmc.io.Files;
import com.jmc.lang.extend.Strs;
import com.jmc.lang.extend.Tries;

import java.util.Map;

/**
 * pom文件模板注入类
 * @since 1.0
 * @author Jmc
 */
public class PomTemplateInjectCore {
    private static final String POM_TEMPLATE_PATH = "templates/pom.xml.ftl";

    public static void inject(String modulePath) {
        String pomPath = modulePath + "/pom.xml";

        String pomInfo = Strs.subExclusive(Files.read(pomPath), "</parent>", "<dependencies>");
        var paramMap = Map.of(
                "groupId", (Object) Strs.subExclusive(pomInfo, "<groupId>", "</groupId>"),
                "artifactId", Strs.subExclusive(pomInfo, "<artifactId>", "</artifactId>"),
                // 版本号默认 1.0.0
                "version", "1.0.0",
                "name", Strs.subExclusive(pomInfo, "<name>", "</name>")
        );

        var engine = new FreemarkerTemplateEngine() {{
            init(null);
        }};

        Tries.tryThis(() -> engine.writer(paramMap, POM_TEMPLATE_PATH, pomPath));
    }
}
