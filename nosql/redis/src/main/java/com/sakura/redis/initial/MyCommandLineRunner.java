package com.sakura.redis.initial;

import com.sakura.redis.model.Coffee;
import com.sakura.redis.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动初始化
 * @date 2022/08/29
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private ICoffeeService coffeeService;

    @Override
    public void run(String... args) throws Exception {
        Optional<Coffee> coffee = coffeeService.findOneCoffee("mocha");
        log.info("=============Coffee {}==============", coffee);
        for (int i = 0; i < 5; i++) {
            coffee = coffeeService.findOneCoffee("mocha");
        }
        log.info("====================Value from Redis: {}", coffee);
    }
}
