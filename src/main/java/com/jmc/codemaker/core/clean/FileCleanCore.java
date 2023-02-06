package com.jmc.codemaker.core.clean;

import com.jmc.codemaker.common.Const;
import com.jmc.io.Files;
import com.jmc.lang.Strs;
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
    private static final String[] ROOT_PATH_RETAINED_FILES = {
            "src",
            "pom.xml", "build.gradle", "settings.gradle",
            "target", "build", ".iml", ".idea"
    };

    /**
     * 需要被额外删除的文件夹（存放相对于模块根目录的路径）
     */
    private static final String[] EXTRA_DELETED_FILES = { "src/test" };

    /**
     * 清理无用文件
     * @param modulePath 模块路径
     */
    public static void clean(String modulePath) {
        File[] fs = new File(modulePath).listFiles();
        Assert.notNull(fs, "fs can't be null");

        for (var f : fs) {
            if (!Strs.orContains(f.getName(), ROOT_PATH_RETAINED_FILES)) {
                Files.delete(f);
            }
        }

        for (var relative : EXTRA_DELETED_FILES) {
            Files.delete(modulePath + "/" + relative);
        }

        System.out.printf(Const.BLUE_MSG, "CodeMaker: 无用文件清理完毕！\n");
    }
}
