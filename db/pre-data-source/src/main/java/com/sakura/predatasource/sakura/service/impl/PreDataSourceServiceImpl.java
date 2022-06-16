package com.sakura.predatasource.sakura.service.impl;

import com.sakura.predatasource.sakura.service.IPreDataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author DM
 * @version 1.0
 * @description 服务接口实现
 * @date 2022/06/16
 */
@Slf4j
@Service
public class PreDataSourceServiceImpl implements IPreDataSourceService {
    @Resource
    private DataSource dataSource;
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void showConnection() throws SQLException {
        log.info("=====================dataSource信息======================");
        log.info(dataSource.toString());
        log.info("=====================dataSource信息======================");
        Connection connection = dataSource.getConnection();
        log.info("=====================dataSource连接信息======================");
        log.info(connection.toString());
        log.info("=====================dataSource连接信息======================");
        connection.close();
    }

    @Override
    public void showData() {
        log.info("=====================数据查询开始======================");
        jdbcTemplate.queryForList("SELECT * FROM FOO").forEach(row -> System.out.println(row.toString()));
        log.info("=====================数据查询结束======================");
    }
}
