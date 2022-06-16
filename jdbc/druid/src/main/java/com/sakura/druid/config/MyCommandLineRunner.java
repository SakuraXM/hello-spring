package com.sakura.druid.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        log.info("=================dataSource信息====================");
        log.info(dataSource.toString());
    }

}
