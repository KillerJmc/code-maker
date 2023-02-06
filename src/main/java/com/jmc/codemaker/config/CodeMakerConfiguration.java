package com.jmc.codemaker.config;

import com.jmc.codemaker.anno.CodeMaker;
import com.jmc.codemaker.common.Const;
import com.jmc.codemaker.core.clean.DependencyCleanCore;
import com.jmc.codemaker.core.clean.FileCleanCore;
import com.jmc.codemaker.core.inject.CodeTemplateInjectCore;
import com.jmc.codemaker.core.inject.GradleTemplateInjectCore;
import com.jmc.codemaker.core.inject.PomTemplateInjectCore;
import com.jmc.codemaker.core.inject.YmlTemplateInjectCore;
import com.jmc.io.Files;
import com.jmc.lang.Outs;
import com.jmc.lang.Strs;
import com.jmc.lang.Tries;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Objects;

/**
 * 激活自动生成代码的配置类
 * @since 1.0
 * @author Jmc
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
@RequiredArgsConstructor
public class CodeMakerConfiguration implements InitializingBean {
    private final DataSourceProperties dataSourceProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 模块路径
        var modulePath = getModulePath();

        // 启动类的Class对象
        var appClass = getAppClass(modulePath);

        // 启动类的包名
        var appPackageName = appClass.getPackageName();

        // 启动类java文件路径
        var appJavaPath = Files.findAny(modulePath, ".java").toPath();

        // 以包名的二级域名作为作者名称并将首字母大写
        var authorName = Strs.capitalize(appPackageName.split("\\.")[1]);

        // 获取CodeMaker注解内容
        var anno = appClass.getAnnotation(CodeMaker.class);
        var buildType = anno.buildType();
        var injectYml = anno.injectYml();

        Outs.newLine(() -> {
            // 开始自动生成代码
            CodeTemplateInjectCore.make(dataSourceProperties, modulePath, appPackageName, authorName, anno);

            // 清除项目中无用的文件
            FileCleanCore.clean(modulePath);

            // 注入Maven或者Gradle模板
            switch (buildType) {
                case MAVEN -> PomTemplateInjectCore.inject(modulePath, anno);
                case GRADLE -> GradleTemplateInjectCore.inject(modulePath, anno);
            }

            // 如果用户确定注入yml文件模板
            if (injectYml) {
                // 进行yml文件模板注入
                YmlTemplateInjectCore.inject(dataSourceProperties, modulePath);
            }

            // 清除CodeMaker相关依赖
            DependencyCleanCore.clean(appJavaPath);

            System.out.printf(Const.BLUE_MSG, "\nCodeMaker: 项目构建成功！\n");
        });

        // 强制停止运行
        Runtime.getRuntime().halt(0);
    }

    /**
     * 获取模块路径
     * @return 模块路径字符串
     */
    private String getModulePath() throws URISyntaxException {
        // 类加载路径URI
        var classPathUri = Objects.requireNonNull(
                Thread.currentThread().getContextClassLoader().getResource("")
        ).toURI();

        // 真实的类加载路径（截取到classes文件夹）
        var realClassPath = Strs.subInclusive(
                Path.of(classPathUri).toFile().getAbsolutePath(), "", "classes"
        );

        // 通过访问类加载的上两级目录获取模块根路径
        return Path.of(realClassPath)
                .getParent()
                .getParent()
                .toString();
    }

    /**
     * 获取启动类的Class对象
     * @param modulePath 模块路径
     * @return 启动类的Class对象
     */
    private Class<?> getAppClass(String modulePath) {
        var srcPath = Path.of(modulePath, "/src/main/java").toAbsolutePath().toString();

        // 模块中所有java源文件
        var javaFiles = Files.findFiles(srcPath, ".java");

        if (javaFiles.size() == 0) {
            throw new RuntimeException("找不到启动类！");
        }

        if (javaFiles.size() > 1) {
            throw new RuntimeException("模块中类过多！模块中只允许有一个类，且这个类必须是启动类。");
        }

        // 通过java文件路径推算出完整类名
        var appClassName = Strs
                // 截取包名和类名
                .subExclusive(javaFiles.get(0).getAbsolutePath(), srcPath + File.separator, ".java")
                // 把文件分隔符全部替换成点
                .replace(File.separatorChar, '.');

        return Tries.tryReturnsT(() -> Class.forName(appClassName));
    }
}
