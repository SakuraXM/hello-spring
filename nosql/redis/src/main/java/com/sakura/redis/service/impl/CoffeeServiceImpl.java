package com.sakura.redis.service.impl;

import com.sakura.redis.model.Coffee;
import com.sakura.redis.repository.CoffeeRepository;
import com.sakura.redis.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡服务接口实现类
 * @date 2022/08/29
 */
@Slf4j
@Service
public class CoffeeServiceImpl implements ICoffeeService {
    /**
     * 缓存名称
     */
    private static final String CACHE = "springbucks-coffee";

    @Resource
    private CoffeeRepository coffeeRepository;
    @Resource
    private RedisTemplate<String, Coffee> redisTemplate;

    @Override
    public Optional<Coffee> findOneCoffee(String name) {
        HashOperations<String, String, Coffee> hashOperations = redisTemplate.opsForHash();
        if (redisTemplate.hasKey(CACHE) && hashOperations.hasKey(CACHE, name)) {
            log.info("===============Get coffee {} from Redis==================", name);
            return Optional.of(hashOperations.get(CACHE, name));
        }
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("===============根据名称查询咖啡==================");
        log.info("Coffee Found: {}", coffee);
        log.info("===============根据名称查询咖啡==================");
        if (coffee.isPresent()){
            log.info("===============Put coffee {} to Redis==================", name);
            hashOperations.put(CACHE, name, coffee.get());
            redisTemplate.expire(CACHE, 1, TimeUnit.MINUTES);
        }
        return coffee;
    }

    @Override
    public List<Coffee> findAll() {
        List<Coffee> list = coffeeRepository.findAll();
        log.info("===============当前支持的所有咖啡==================");
        log.info("All Coffee: {}", list);
        log.info("===============当前支持的所有咖啡==================");
        return list;
    }
}
