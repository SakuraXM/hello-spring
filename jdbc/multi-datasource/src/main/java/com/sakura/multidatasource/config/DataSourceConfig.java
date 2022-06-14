package com.sakura.multidatasource.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author DM
 * @version 1.0
 * @description 多数据源配置信息
 * @date 2022/05/28
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDataSourceProperties() {
        log.info("======================获取foo datasource配置信息======================");
        return new DataSourceProperties();
    }

    @Bean
    public DataSource fooDataSource() {
        log.info("======================创建foo datasource信息======================");
        DataSourceProperties dataSourceProperties = fooDataSourceProperties();
        log.info("foo datasource:{}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
        log.info("======================创建fooTxManager信息======================");
        return new DataSourceTransactionManager(fooDataSource);
    }

    @Bean
    @ConfigurationProperties("bar.datasource")
    public DataSourceProperties barDataSourceProperties() {
        log.info("======================获取bar datasource配置信息======================");
        return new DataSourceProperties();
    }

    @Bean
    public DataSource barDataSource() {
        log.info("======================创建bar datasource信息======================");
        DataSourceProperties dataSourceProperties = barDataSourceProperties();
        log.info("bar datasource:{}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager barTxManager(DataSource barDataSource) {
        log.info("======================创建barTxManager信息======================");
        return new DataSourceTransactionManager(barDataSource);
    }

}
