package com.jmc.codemaker.anno;

/**
 * 构建类型枚举
 * @since 2.0
 * @author Jmc
 */
public enum BuildType {
    /**
     * 设置构建类型为Gradle并简化gradle文件
     */
    GRADLE,

    /**
     * 设置构建类型为Maven并简化pom文件
     */
    MAVEN,

    /**
     * 设置为该值将不会改变现有的构建文件
     */
    NONE
}
