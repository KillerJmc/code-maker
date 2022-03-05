package com.jmc.codemaker.core;

import com.jmc.codemaker.common.Const;
import com.jmc.io.Files;
import com.jmc.lang.Strs;

import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * 清除本应用依赖
 * @since 1.0
 * @author Jmc
 */
public class ApplicationCleanCore {
    /**
     * 源码中被清除的行标志（包括导入包和注解）
     */
    private static final String SRC_SYMBOL = "CodeMaker";

    /**
     * Pom中被清除依赖的标志
     */
    private static final String DEPENDENCY_SYMBOL = "code-maker";

    /**
     * 清理本应用依赖
     * @param appJavaPath 启动类java文件路径
     * @param modulePath 模块路径
     */
    public static void clean(Path appJavaPath, String modulePath) {
        var pomPath = modulePath + "/pom.xml";
        var pomContent = Files.read(pomPath);

        // 从pom文件中查找code-maker的依赖
        var targetDependency = Strs
                .collectAll(pomContent, "<dependency>", "</dependency>", true)
                .stream()
                .filter(s -> s.contains(DEPENDENCY_SYMBOL))
                .findAny()
                .orElse(null);

        // 如果查到就去除pom文件中的依赖
        if (targetDependency != null) {
            var resPomContent = pomContent.replace(targetDependency, "");
            Files.out(resPomContent, pomPath);
        }

        // 去除项目启动类中的注解
        Files.out(
            Files.lines(appJavaPath.toFile())
                 .filter(t -> !t.contains(SRC_SYMBOL))
                 .collect(Collectors.joining("\n")),
            appJavaPath.toFile()
        );

        System.out.printf(Const.BLUE_MSG, "CodeMaker: 清除CodeMaker依赖完毕！\n");
    }
}
