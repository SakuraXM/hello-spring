package com.sakura.simplejdbc.sakura.dao;

import com.sakura.simplejdbc.sakura.model.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DM
 * @version 1.0
 * @description Foo数据库交互接口
 * @date 2022/06/16
 */
@Slf4j
@Repository
public class FooDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private SimpleJdbcInsert simpleJdbcInsert;

    /**
     * @description 插入数据
     */
    public void insertData() {
        Arrays.asList("b", "c").forEach(item ->
                jdbcTemplate.update("INSERT INTO FOO (BAR) VALUES (?)", item)
        );
        Map<String, String> row = new HashMap<>();
        row.put("BAR", "d");
        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("ID of d: {}", id.longValue());
    }

    /**
     * @description 获取列表数据
     */
    public void listData() {
        log.info("Count: {}",
                jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO", Long.class));
        List<String> list = jdbcTemplate.queryForList("SELECT BAR FROM FOO", String.class);
        list.forEach(item -> log.info("Bar: {}", item));

        List<Foo> fooList = jdbcTemplate.query("SELECT * FROM FOO", new RowMapper<Foo>() {
            @Override
            public Foo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Foo.builder()
                        .id(rs.getLong(1))
                        .bar(rs.getString(2))
                        .build();
            }
        });
        fooList.forEach(item -> log.info("Foo: {}", item));
    }

}
