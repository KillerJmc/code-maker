package com.jmc.codemaker.core;

import com.jmc.codemaker.common.Const;
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
    /**
     * 模块根目录保留的文件名中包含内容（只兼容Idea）
     */
    private static final String[] RETAINED_FILES = { "src", "pom.xml", ".iml", "target", ".idea" };

    /**
     * 清理无用文件
     * @param modulePath 模块路径
     */
    public static void clean(String modulePath) {
        File[] fs = new File(modulePath).listFiles();
        Assert.notNull(fs, "fs can't be null");

        for (var f : fs) {
            if (!Strs.orContains(f.getName(), RETAINED_FILES)) {
                Files.delete(f);
            }
        }

        System.out.printf(Const.BLUE_MSG, "CodeMaker: 无用文件清理完毕！\n");
    }
}
