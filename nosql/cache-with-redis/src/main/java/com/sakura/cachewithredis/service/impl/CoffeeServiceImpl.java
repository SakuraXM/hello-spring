package com.sakura.cachewithredis.service.impl;

import com.sakura.cachewithredis.model.Coffee;
import com.sakura.cachewithredis.repository.CoffeeRepository;
import com.sakura.cachewithredis.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
 * @date 2022/07/09
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "coffee")
public class CoffeeServiceImpl implements ICoffeeService {
    @Resource
    private CoffeeRepository coffeeRepository;

    @Override
    public Optional<Coffee> findOneCoffee(String name) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("===============根据名称查询咖啡==================");
        log.info("Coffee Found: {}", coffee);
        log.info("===============根据名称查询咖啡==================");
        return coffee;
    }

    @Override
    @Cacheable
    public List<Coffee> findAllCoffee() {
        List<Coffee> list = coffeeRepository.findAll();
        log.info("===============当前支持的所有咖啡==================");
        log.info("All Coffee: {}", list);
        log.info("===============当前支持的所有咖啡==================");
        return list;
    }

    @Override
    @CacheEvict
    public void reloadCoffee() {
        log.info("===========================CacheEvict========================");
    }
}
