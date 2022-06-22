package com.sakura.mybatis.initial;

import com.sakura.mybatis.model.Coffee;
import com.sakura.mybatis.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动初始化
 * @date 2022/06/22
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private ICoffeeService coffeeService;

    @Override
    public void run(String... args) throws Exception {
        int i = coffeeService.save("espresso", 20.0);
        int k = coffeeService.save("latte", 25.0);

        log.info("=========================查询咖啡信息==========================");
        Coffee coffee = coffeeService.findById(Long.valueOf(i));
        log.info("Query Coffee: {}", coffee);
        log.info("=========================查询咖啡信息==========================");
    }

}
