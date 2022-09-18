package com.sakura.simpler2dbc.initial;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * 服务启动初始化
 *
 * @author DM
 * @version 1.0
 * @date 2022/09/17
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private R2dbcEntityTemplate r2dbcEntityTemplate;
    /**
     * 当使用 Schedulers.boundedElastic() 后需要使用cdl进行控制
     * 主线程结束不意味着当前程序该退出了，所有线程都执行完成了JVM才会退出
     * 避免其它线程还没有启动，main线程就结束，导致其它线程都没机会开启
     */
    private CountDownLatch cdl = new CountDownLatch(1);

    @Override
    public void run(String... args) throws Exception {
        r2dbcEntityTemplate.getDatabaseClient().sql("select * from t_coffee")
                .fetch()
                .first()
                .doFinally(s -> cdl.countDown())
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(c -> log.info("=============Fetch sql：{}", c));

        cdl.await();
    }
}
