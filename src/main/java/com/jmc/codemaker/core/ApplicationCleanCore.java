package com.jmc.codemaker.core;

import com.jmc.lang.extend.Tries;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * 清除本应用依赖
 * @since 1.0
 * @author Jmc
 */
public class ApplicationCleanCore {
    private static final String SYMBOL = "CodeMaker";

    public static void clean(Path appJavaPath) {
        Tries.tryThis(() ->
            Files.writeString(appJavaPath,
                Files.readAllLines(appJavaPath)
                     .stream()
                     .filter(t -> !t.contains(SYMBOL))
                     .collect(Collectors.joining("\n"))
            )
        );
    }
}
