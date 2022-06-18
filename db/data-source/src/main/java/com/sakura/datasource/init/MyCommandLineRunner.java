package com.sakura.datasource.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动初始化信息
 * @date 2022/06/17
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private DataSource dataSource;

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
