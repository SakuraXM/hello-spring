package com.sakura.redisrepository.service.impl;

import com.sakura.redisrepository.model.Coffee;
import com.sakura.redisrepository.model.CoffeeCache;
import com.sakura.redisrepository.repository.CoffeeCacheRepository;
import com.sakura.redisrepository.repository.CoffeeRepository;
import com.sakura.redisrepository.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡服务接口实现类
 * @date 2022/08/30
 */
@Slf4j
@Service
public class CoffeeServiceImpl implements ICoffeeService {
    @Resource
    private CoffeeRepository coffeeRepository;
    @Resource
    private CoffeeCacheRepository cacheRepository;

    @Override
    public List<Coffee> findAll() {
        List<Coffee> list = coffeeRepository.findAll();
        log.info("===============当前支持的所有咖啡==================");
        log.info("All Coffee: {}", list);
        log.info("===============当前支持的所有咖啡==================");
        return list;
    }

    @Override
    public Optional<Coffee> findSimpleCoffeeFromCache(String name) {
        Optional<CoffeeCache> cache = cacheRepository.findOneByName(name);
        if (cache.isPresent()) {
            CoffeeCache coffeeCache = cache.get();
            Coffee coffee = Coffee.builder()
                    .name(coffeeCache.getName())
                    .price(coffeeCache.getPrice())
                    .build();
            log.info("==============Coffee {} found in cache===============", coffeeCache);
            return Optional.of(coffee);
        } else {
            Optional<Coffee> raw = findOneCoffee(name);
            raw.ifPresent(c -> {
                CoffeeCache coffeeCache = CoffeeCache.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .price(c.getPrice())
                        .build();
                log.info("================Save Coffee {} to cache==================", coffeeCache);
                cacheRepository.save(coffeeCache);
            });
            return raw;
        }
    }

    @Override
    public Optional<Coffee> findOneCoffee(String name) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("================Coffee Found: {}==============", coffee);
        return coffee;
    }
}
