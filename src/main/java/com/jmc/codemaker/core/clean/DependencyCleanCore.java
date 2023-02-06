package com.jmc.codemaker.core.clean;

import com.jmc.codemaker.common.Const;
import com.jmc.io.Files;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 清除本应用依赖
 * @since 1.0
 * @author Jmc
 */
public class DependencyCleanCore {
    /**
     * 启动类Java中被清除的行标志（包括导入包和注解）
     */
    private static final List<String> APPLICATION_JAVA_CLEAN_SYMBOLS = List.of(
            "com.jmc.codemaker", "CodeMaker"
    );

    /**
     * 清理本应用依赖
     *
     * @param appJavaPath 启动类java文件路径
     */
    public static void clean(Path appJavaPath) {
        // 去除项目启动类中的导入包和注解
        Files.out(
            Files.lines(appJavaPath.toFile())
                 .filter(line -> APPLICATION_JAVA_CLEAN_SYMBOLS.stream().noneMatch(line::contains))
                 .collect(Collectors.joining("\n")),
            appJavaPath.toFile()
        );

        // 获取@SpringBootApplication之前内容
        var pre = Files.lines(appJavaPath.toFile())
                .takeWhile(line -> !line.equals("@SpringBootApplication"))
                .collect(Collectors.joining("\n"));

        // 获取@SpringBootApplication之后内容并去除空行
        var sub = Files.lines(appJavaPath.toFile())
                .dropWhile(line -> !line.equals("@SpringBootApplication"))
                .filter(line -> !line.isBlank())
                .collect(Collectors.joining("\n"));

        // 去除项目启动类中的空行
        Files.out(
                pre + "\n" + sub,
                appJavaPath.toFile()
        );

        System.out.printf(Const.BLUE_MSG, "CodeMaker: 清除CodeMaker依赖完毕！\n");
    }
}
