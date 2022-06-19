package com.sakura.druidmonitor.dao;

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
    public void insertData() {
        jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'AAA')");
    }

    /**
     * @return long
     * @description 获取Foo表数据条数
     */
    public long getCount() {
        return (long) jdbcTemplate.queryForList("SELECT COUNT(*) AS CNT FROM FOO").get(0).get("CNT");
    }

    /**
     * @description 查询Foo表(行锁)
     */
    public void selectForUpdate() {
        jdbcTemplate.queryForObject("SELECT ID FROM FOO WHERE ID = 1 FOR UPDATE ", Long.class);
    }

}
