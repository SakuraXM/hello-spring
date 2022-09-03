package com.sakura.reactivemongodb.initial;

import com.sakura.reactivemongodb.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 服务启动初始化
 *
 * @author DM
 * @version 1.0
 * @date 2022/09/03
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private ReactiveMongoTemplate reactiveMongoTemplate;

    CountDownLatch cdl = new CountDownLatch(2);

    @Override
    public void run(String... args) throws Exception {

//        startFromInsertion(() -> log.info("===================Runnable==================="));
        // 注：多线程环境下执行顺序
        startFromInsertion(() ->{
            log.info("===================Runnable===================");
            decreaseHighPrice();
        });

        log.info("===================after starting===================");
//        decreaseHighPrice();

        cdl.await();
    }

    /**
     * 将咖啡信息保存到MongoDB中
     *
     * @param runnable
     */
    private void startFromInsertion(Runnable runnable) {
        reactiveMongoTemplate.insertAll(initCoffee())
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(c -> log.info("===============Next: {}", c))
                .doOnComplete(runnable)
                .doFinally(s -> {
                    cdl.countDown();
                    log.info("=================Finally 1, {}", s);
                })
                .count()
                .subscribe(c -> log.info("==================Insert {} records", c));
    }

    /**
     * 咖啡价格降价处理
     */
    private void decreaseHighPrice() {
        reactiveMongoTemplate.updateMulti(Query.query(Criteria.where("price").gte(3000L)),
                        new Update().inc("price", -500L)
                                .currentDate("updateTime"), Coffee.class)
                .doFinally(s -> {
                    cdl.countDown();
                    log.info("================Finally 2, {}", s);
                })
                .subscribe(r -> log.info("===================Result is {}", r));
    }

    /**
     * 初始化咖啡信息
     *
     * @return List<Coffee>
     */
    private List<Coffee> initCoffee() {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        return Arrays.asList(espresso, latte);
    }
}
