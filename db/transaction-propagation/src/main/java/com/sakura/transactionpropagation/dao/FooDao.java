package com.sakura.transactionpropagation.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author DM
 * @version 1.0
 * @description Foo数据库交互接口
 * @date 2022/06/18
 */
@Slf4j
@Repository
public class FooDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * @description 插入数据FOO
     */
    public void insertData(String parameter) {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('" + parameter + "')");
    }

    /**
     * @description 获取数据条数
     */
    public long getCount(String parameter) {
        return (long) jdbcTemplate.queryForList("SELECT COUNT(*) AS CNT FROM FOO WHERE BAR=?", parameter).get(0).get("CNT");
    }

}
