package com.jmc.codemaker.core;

import com.jmc.codemaker.common.Const;
import com.jmc.lang.Tries;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * 清除本应用依赖
 * @since 1.0
 * @author Jmc
 */
public class ApplicationCleanCore {
    /**
     * 被清除行的标志
     */
    private static final String SYMBOL = "CodeMaker";

    /**
     * 清理本应用依赖
     * @param appJavaPath 启动类java文件路径
     */
    public static void clean(Path appJavaPath) {
        Tries.tryThis(() ->
            Files.writeString(appJavaPath,
                Files.readAllLines(appJavaPath)
                     .stream()
                     .filter(t -> !t.contains(SYMBOL))
                     .collect(Collectors.joining("\n"))
            )
        );

        System.out.printf(Const.BLUE_MSG, "CodeMaker: 清除CodeMaker依赖完毕！\n");
    }
}
