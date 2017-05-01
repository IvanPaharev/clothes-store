package com.netcracker.store.logic.config;

import com.netcracker.store.persistence.config.DataConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by A-one on 17.04.2017.
 */
@Configuration
@ComponentScan(basePackages = "com.netcracker.store.logic")
@Import(DataConfig.class)
public class ServiceConfig {
}
