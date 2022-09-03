package com.sakura.reactiveredis.initial;

import com.sakura.reactiveredis.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.annotation.Resource;
import java.time.Duration;
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
    /**
     * 缓存名称
     */
    private static final String CACHE_KEY = "COFFEE_MENU";
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private ReactiveStringRedisTemplate reactiveStringRedisTemplate;

    @Override
    public void run(String... args) throws Exception {
        ReactiveHashOperations<String, String, String> hashOps = reactiveStringRedisTemplate.opsForHash();
        CountDownLatch cdl = new CountDownLatch(1);

        List<Coffee> list = jdbcTemplate.query(
                "select * from t_coffee", (rs, i) ->
                        Coffee.builder().id(rs.getLong("id"))
                                .name(rs.getString("name"))
                                .price(rs.getLong("price"))
                                .build()
        );

        Flux.fromIterable(list)
                .publishOn(Schedulers.single())
                .doOnComplete(() -> log.info("================list ok================="))
                .flatMap(c -> {
                    log.info("===================try to put {}, {}", c.getName(), c.getPrice());
                    return hashOps.put(CACHE_KEY, c.getName(), String.valueOf(c.getPrice()));
                })
                .doOnComplete(() -> log.info("==============set ok==============="))
                .concatWith(reactiveStringRedisTemplate.expire(CACHE_KEY, Duration.ofMinutes(1)))
                .doOnComplete(() -> log.info("================expire ok================="))
                .onErrorResume(e -> {
                    log.error("=================exception {}", e.getMessage());
                    return Mono.just(false);
                })
                .subscribe(b -> log.info("================Boolean: {}==============", b),
                        e -> log.error("=====================Exception {}====================", e.getMessage()),
                        () -> cdl.countDown());
        log.info("========================Waiting========================");
        cdl.await();
    }
}
