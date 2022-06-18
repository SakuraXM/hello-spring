package com.sakura.transactionpropagation.init;

import com.sakura.transactionpropagation.service.IFooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动初始化信息
 * @date 2022/06/18
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private IFooService fooService;

    @Override
    public void run(String... args) throws Exception {
        log.info("==============================================");
        try {
            fooService.invokeInsertThenRollback();
        } catch (Exception e) {
        }
        log.info("AAA {}", fooService.getCount("AAA"));
        log.info("BBB {}", fooService.getCount("BBB"));
        log.info("==============================================");

    }

}
