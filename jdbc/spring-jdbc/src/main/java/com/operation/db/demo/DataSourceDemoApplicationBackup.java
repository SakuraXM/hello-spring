package com.operation.db.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author DM
 * @version 1.0
 * @description Spring DataSource demo示例
 * @date 2022/04/12
 */
@Slf4j
@SpringBootApplication
public class DataSourceDemoApplicationBackup implements CommandLineRunner {
    private final DataSource dataSource;

    public DataSourceDemoApplicationBackup(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) {
        SpringApplication.run(DataSourceDemoApplicationBackup.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        showConnection();
    }

    /**
     * @throws SQLException
     * @description 连接信息展示
     */
    private void showConnection() throws SQLException {
        log.info("数据源信息:" + dataSource.toString());
        try (Connection connection = dataSource.getConnection()) {
            log.info("连接信息:" + connection.toString());
        }
    }

}
