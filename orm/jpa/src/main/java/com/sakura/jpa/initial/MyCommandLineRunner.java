package com.sakura.jpa.initial;

import com.sakura.jpa.model.Coffee;
import com.sakura.jpa.model.CoffeeOrder;
import com.sakura.jpa.repository.CoffeeOrderRepository;
import com.sakura.jpa.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;

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
    private CoffeeRepository coffeeRepository;
    @Resource
    private CoffeeOrderRepository coffeeOrderRepository;

    @Override
    public void run(String... args) throws Exception {
        initCoffeeOrder();
    }

    private void initCoffeeOrder() {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        log.info("====================创建espresso咖啡===================");
        coffeeRepository.save(espresso);
        log.info("Coffee: {}", espresso);
        log.info("====================创建espresso咖啡===================");

        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();
        log.info("====================创建latte咖啡===================");
        coffeeRepository.save(latte);
        log.info("Coffee: {}", latte);
        log.info("====================创建latte咖啡===================");


        CoffeeOrder order = new CoffeeOrder()
                .setCustomer("Li Lei")
                .setItems(Collections.singletonList(espresso))
                .setState(0);
        log.info("====================创建espresso咖啡订单===================");
        coffeeOrderRepository.save(order);
        log.info("Order: {}", order);
        log.info("====================创建espresso咖啡订单===================");

        order = new CoffeeOrder()
                .setCustomer("Li Lei")
                .setItems(Arrays.asList(espresso, latte))
                .setState(0);
        log.info("====================创建espresso和latte咖啡订单===================");
        coffeeOrderRepository.save(order);
        log.info("Order: {}", order);
        log.info("====================创建espresso和latte咖啡订单===================");;
    }

}
