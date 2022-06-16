package com.sakura.predatasource.sakura.startup;

import com.sakura.predatasource.sakura.service.IPreDataSourceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动调用类
 * @date 2022/06/16
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private IPreDataSourceService preDataSourceService;

    @Override
    public void run(String... args) throws Exception {
        preDataSourceService.showConnection();
        preDataSourceService.showData();
    }
}
