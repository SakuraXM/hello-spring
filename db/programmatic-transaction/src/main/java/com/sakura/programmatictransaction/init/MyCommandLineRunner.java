package com.sakura.programmatictransaction.init;

import com.sakura.programmatictransaction.service.IProgrammaticTraService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动初始化信息
 * @date 2022/06/17
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private IProgrammaticTraService programmaticTraService;

    @Override
    public void run(String... args) throws Exception {
        programmaticTraService.TransactionTest();
    }

}
