package com.jmc.codemaker.config;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 将用户yml中的datasource信息注入配置类以供使用
 * @since 1.0
 * @author Jmc
 */
@ConfigurationProperties("spring.datasource")
public class DataSourceProperties extends DataSourceConfig {
    public DataSourceProperties() {
        this.setDriverName("com.mysql.cj.jdbc.Driver");
        this.setUrl("jdbc:mysql:///jmc");
        this.setUsername("root");
        this.setPassword("root");
    }
}
