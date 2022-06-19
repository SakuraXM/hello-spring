package com.sakura.druidmonitor.init;

import com.sakura.druidmonitor.service.IDruidMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动初始化信息
 * @date 2022/06/18
 */
@Slf4j
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private DataSource dataSource;
    @Resource
    private IDruidMonitorService druidMonitorService;

    @Override
    public void run(String... args) throws Exception {
        log.info("dataSource info: {}", dataSource.toString());
        log.info("========================开启线程查询=========================");
        new Thread(() -> druidMonitorService.selectForUpdate()).start();
        new Thread(() -> druidMonitorService.selectForUpdate()).start();
        log.info("========================线程查询结束=========================");
    }

}
