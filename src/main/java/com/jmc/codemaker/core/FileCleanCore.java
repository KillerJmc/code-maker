package com.jmc.codemaker.core;

import com.jmc.io.Files;
import com.jmc.lang.extend.Strs;
import org.springframework.util.Assert;

import java.io.File;

/**
 * 项目无用文件清除类
 * @since 1.0
 * @author Jmc
 */
public class FileCleanCore {
    private static final String[] RETAINED_FILES = {"src", "pom.xml", ".iml", "target", ".idea"};

    public static void clean(String modulePath) {
        File[] fs = new File(modulePath).listFiles();
        Assert.notNull(fs, "fs can't be null");

        for (var f : fs) {
            if (!Strs.orContains(f.getName(), RETAINED_FILES)) {
                Files.delete(f);
            }
        }
    }
}
