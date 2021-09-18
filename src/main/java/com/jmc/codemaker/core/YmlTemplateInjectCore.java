package com.jmc.codemaker.core;

import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.jmc.codemaker.config.DataSourceProperties;
import com.jmc.io.Files;
import com.jmc.lang.extend.Strs;
import com.jmc.lang.extend.Tries;
import org.apache.ibatis.io.Resources;

import java.nio.file.Path;
import java.util.Map;

/**
 * yml文件注入类
 * @since 1.0
 * @author Jmc
 */
public class YmlTemplateInjectCore {
    /**
     * 本模块中yml文件模板的路径
     */
    private static final String YML_TEMPLATE_PATH = "templates/application.yml.ftl";

    /**
     * 创建代码的模块中yml文件路径
     */
    private static final String TARGET_YML_PATH = "/src/main/resources/application.yml";

    public static void inject(DataSourceProperties dataSourceProperties, String modulePath) {
        var ymlFile = Path.of(modulePath, TARGET_YML_PATH);

        // 删除默认的properties文件
        Files.findDels(ymlFile.getParent().toFile(), ".properties");

        var paramMap = Map.of(
                "url", (Object) dataSourceProperties.getUrl(),
                "username", dataSourceProperties.getUsername(),
                "password", dataSourceProperties.getPassword()
        );

        var engine = new FreemarkerTemplateEngine() {{
            init(null);
        }};

        Tries.tryThis(() -> engine.writer(paramMap, YML_TEMPLATE_PATH, ymlFile.toString()));
    }
}
