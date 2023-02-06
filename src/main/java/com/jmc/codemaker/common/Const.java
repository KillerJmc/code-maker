package com.jmc.codemaker.common;

/**
 * 常量类
 * @since 1.0
 * @author Jmc
 */
public interface Const {
    /**
     * 控制台蓝色字符串
     */
    String BLUE_MSG = "\033[34;24m%s\033[0m";

    /**
     * 持久化类型JPA的模块路径
     */
    String JPA_TEMPLATE_PATH = "/templates/jpa/";

    /**
     * 持久化类型MyBatis Plus的模块路径
     */
    String MYBATIS_PLUS_TEMPLATE_PATH = "/templates/mybatis-plus/";
}
