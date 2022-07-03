package com.sakura.mongodb.initial;

import com.sakura.mongodb.model.Coffee;
import com.sakura.mongodb.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动初始
 * @date 2022/07/02
 */
@Slf4j
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Resource
    private ICoffeeService coffeeService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        extracted();
        mongodbRepositoryTest();
    }

    /**
     * @throws InterruptedException
     * @description mongodb测试
     */
    private void extracted() throws InterruptedException {
        log.info("======================create coffee==========================");
        Coffee coffee = coffeeService.saveCoffeeToMongo("espresso", 20.0);
        log.info("======================find coffee by name==========================");
        coffeeService.findByName("espresso");
        Thread.sleep(2000);
        log.info("======================modify coffee by name==========================");
        coffeeService.modifyByFields("espresso", 30);
        log.info("======================find coffee by id==========================");
        coffeeService.findById(coffee.getId());
        log.info("======================remove coffee data==========================");
        coffeeService.removeData(coffee);
    }

    private void mongodbRepositoryTest() throws InterruptedException {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .createTime(new Date())
                .updateTime(new Date()).build();

        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .createTime(new Date())
                .updateTime(new Date()).build();
        log.info("======================batch insert coffee==========================");
        coffeeService.insert(Arrays.asList(espresso, latte));
        log.info("======================find all coffee by name sort==========================");
        coffeeService.findAllByName();

        Thread.sleep(2000);
        latte.setPrice(Money.of(CurrencyUnit.of("CNY"), 35.0));
        latte.setUpdateTime(new Date());
        coffeeService.saveCoffee(latte);
        log.info("======================find by name modified coffee==========================");
        coffeeService.cFindByName("latte");
        log.info("======================delete all coffee==========================");
        coffeeService.deleteAll();
    }

}
