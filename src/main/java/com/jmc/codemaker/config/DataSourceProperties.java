package com.jmc.codemaker.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 将用户yml中的datasource信息注入配置类以供使用
 * @since 1.0
 * @author Jmc
 */
@Data
@ConfigurationProperties("spring.datasource")
public class DataSourceProperties {
    private String url = "jdbc:mysql:///jmc";
    private String username = "root";
    private String password = "root";
}
