package com.sakura.simplereactor.initial;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * 服务启动初始化
 *
 * @author DM
 * @version 1.0
 * @date 2022/09/01
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Flux.range(1, 6)
                .doOnRequest(n -> log.info("=============Request {} number=============", n))
                // 需注意顺序造成的区别
                .publishOn(Schedulers.boundedElastic())
                .doOnComplete(() -> log.info("=============Publisher COMPLETE 1============="))
                .map(i -> {
                    log.info("=============Publisher {}, {}", Thread.currentThread(), i);
//                    return 10 / (i - 3);
                    return i;
                })
                .doOnComplete(()->log.info("=============Publisher COMPLETE 2============="))
                .subscribeOn(Schedulers.single())
                .onErrorResume(e -> {
                    log.error("=============Exception {}=============", e.toString());
                    return Mono.just(-1);
                })
                .onErrorReturn(-1)
                .subscribe(i -> log.info("=============Subscribe {}: {}", Thread.currentThread(), i),
                        e -> log.error("=============error {}=============", e.toString()),
                        () -> log.info("=============Subscriber COMPLETE============="),
                        s -> s.request(4));
        Thread.sleep(2000);
    }
}
