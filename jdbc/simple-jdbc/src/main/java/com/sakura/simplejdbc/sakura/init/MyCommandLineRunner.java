package com.sakura.simplejdbc.sakura.init;

import com.sakura.simplejdbc.sakura.service.IFooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author DM
 * @version 1.0
 * @description 接口初始化调用
 * @date 2022/06/16
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private IFooService fooService;

    @Override
    public void run(String... args) throws Exception {
//        fooService.insertData();
        fooService.batchInsert();
        fooService.listData();
    }

}
