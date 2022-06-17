package com.sakura.declarativetransaction.init;

import com.sakura.declarativetransaction.service.IFooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动初始化信息
 * @date 2022/06/17
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private IFooService fooService;

    @Override
    public void run(String... args) throws Exception {
        log.info("==============================================");
        fooService.insertRecord();
        log.info("AAA {} ", fooService.getCount("AAA"));
        try {
            fooService.insertThenRollback();
        } catch (Exception e) {
            log.info("BBB {}", fooService.getCount("BBB"));
        }

        try {
            fooService.invokeInsertThenRollback();
            // 本身调用
//            fooService.invokeInsertThenRollbackBySelfService();
            // 当前代理调用
//            fooService.invokeInsertThenRollbackByAopContext();
            // 加上事务管理
//            fooService.invokeInsertThenRollbackAddTransaction();
        } catch (Exception e) {
            log.info("BBB {}", fooService.getCount("BBB"));
            log.info("==============================================");
        }
    }

}
