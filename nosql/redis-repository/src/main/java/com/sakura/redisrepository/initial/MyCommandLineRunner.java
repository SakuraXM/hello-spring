package com.sakura.redisrepository.initial;

import com.sakura.redisrepository.model.Coffee;
import com.sakura.redisrepository.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动初始化
 * @date 2022/08/30
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private ICoffeeService coffeeService;

    @Override
    public void run(String... args) throws Exception {
        Optional<Coffee> coffee = coffeeService.findSimpleCoffeeFromCache("mocha");
        log.info("=============Coffee {}==============", coffee);
        for (int i = 0; i < 5; i++) {
            coffee = coffeeService.findSimpleCoffeeFromCache("mocha");
        }
        log.info("====================Value from Redis: {}", coffee);
    }
}
