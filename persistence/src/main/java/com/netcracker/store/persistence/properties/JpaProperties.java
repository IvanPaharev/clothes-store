package com.netcracker.store.persistence.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by A-one on 01.06.2017.
 */
@Configuration
@Getter @Setter
@PropertySource("classpath:persistence.properties")
@ConfigurationProperties(prefix = "spring.jpa")
public class JpaProperties {
    private String showSql;
    private String databasePlatform;
}
