package com.sakura.jedis.initial;

import com.sakura.jedis.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动初始化
 * @date 2022/06/20
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private ICoffeeService coffeeService;
    @Resource
    private JedisPoolConfig jedisPoolConfig;
    @Resource
    private JedisPool jedisPool;

    @Override
    public void run(String... args) throws Exception {
        log.info("=============================================");
        log.info("jedisPoolConfig: {}", jedisPoolConfig);
        try (Jedis jedis = jedisPool.getResource()) {
            coffeeService.findAll().forEach(item -> {
                jedis.hset("springbucks-menu",
                        item.getName(),
                        String.valueOf(item.getPrice().getAmountMajorLong()));
            });

            Map<String, String> menu = jedis.hgetAll("springbucks-menu");
            log.info("All menu: {}", menu);

            String price = jedis.hget("springbucks-menu", "espresso");
            log.info("espresso - {}", Money.ofMinor(CurrencyUnit.of("CNY"), Long.parseLong(price)));
            log.info("=============================================");
        }
    }
}
