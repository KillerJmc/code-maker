package com.jmc.codemaker.config;

import com.jmc.codemaker.anno.CodeMaker;
import com.jmc.codemaker.core.*;
import com.jmc.io.Files;
import com.jmc.lang.extend.Outs;
import com.jmc.lang.extend.Strs;
import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

/**
 * 激活自动生成代码的配置类
 * @since 1.0
 * @author Jmc
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class CodeMakerConfiguration implements InitializingBean {
    private DataSourceProperties dataSourceProperties;

    @Autowired
    public void setDataSourceProperties(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        var classPathUri = Resources.getResourceURL("").toURI();

        // 通过访问classPath的上级目录获取模块根路径
        String modulePath = Path.of(classPathUri).getParent().getParent().toString();

        // 启动类名称要求以Application结尾
        var appFile = Files.findAny(Path.of(classPathUri).toString(), "Application.class");
        if (appFile == null) {
            throw new RuntimeException("找不到启动类，请检查启动类名称是否以Application结尾");
        }

        var appClassName = Strs.subExclusive(appFile.getAbsolutePath(),
                "classes\\", ".class").replace('\\', '.');
        var appJavaPath = Path.of(modulePath,
                "src/main/java", appClassName.replace('.', '/') + ".java");

        // 获取CodeMaker注解
        var anno = Class.forName(appClassName).getAnnotation(CodeMaker.class);
        String[] tableNames = anno.tableNames();
        boolean autowired = anno.autowired();
        String packageName = appClassName.substring(0, appClassName.lastIndexOf('.'));

        // 以包名的二级域名作为作者名称
        var tmp = packageName.split("\\.")[1];
        // 首字母大写
        String authorName = Character.toUpperCase(tmp.charAt(0)) + tmp.substring(1);


        var blueMsg = "\033[34;24m%s\033[0m";
        Outs.newLine(() -> {
            // 开始自动生成代码
            CodeMakerCore.make(dataSourceProperties, modulePath, packageName, authorName, tableNames, autowired);
            System.out.printf(blueMsg, "CodeMaker: 代码自动生成完毕！\n");

            // 清除项目中无用的文件
            FileCleanCore.clean(modulePath);
            System.out.printf(blueMsg, "CodeMaker: 无用文件清理完毕！\n");

            // yml模板注入
            YmlTemplateInjectCore.inject(dataSourceProperties, modulePath);
            System.out.printf(blueMsg, "CodeMaker: yml文件生成完毕！\n");

            // pom文件模板注入
            PomTemplateInjectCore.inject(modulePath);
            System.out.printf(blueMsg, "CodeMaker: pom文件模板注入完毕！\n");

            // 清除启动类中CodeMaker的相关代码
            ApplicationCleanCore.clean(appJavaPath);
            System.out.printf(blueMsg, "CodeMaker: 清除CodeMaker依赖完毕！\n");

            System.out.printf(blueMsg, "\nCodeMaker: 项目构建成功！\n");
        });

        // 强制停止运行
        Runtime.getRuntime().halt(0);
    }
}
