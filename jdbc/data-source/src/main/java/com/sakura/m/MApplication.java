package com.sakura.m;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@SpringBootApplication
public class MApplication implements CommandLineRunner {
    @Resource
    DataSource dataSource;
    @Resource
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        showConnection();
        showData();
    }

    private void showConnection() throws SQLException {
        log.info("=====================dataSource信息======================");
        log.info(dataSource.toString());
        log.info("=====================dataSource信息======================");
        Connection connection = dataSource.getConnection();
        log.info("=====================dataSource连接信息======================");
        log.info(connection.toString());
        log.info("=====================dataSource连接信息======================");
        connection.close();
    }

    private void showData() {
        log.info("=====================数据查询开始======================");
        jdbcTemplate.queryForList("SELECT * FROM FOO").forEach(row -> System.out.println(row.toString()));
        log.info("=====================数据查询结束======================");
    }

}
