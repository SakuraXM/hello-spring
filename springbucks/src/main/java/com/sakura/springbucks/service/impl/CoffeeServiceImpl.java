package com.sakura.springbucks.service.impl;

import com.sakura.springbucks.model.Coffee;
import com.sakura.springbucks.repository.CoffeeRepository;
import com.sakura.springbucks.service.ICoffeeService;
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
 * @date 2022/06/20
 */
@Slf4j
@Service
public class CoffeeServiceImpl implements ICoffeeService {
    @Resource
    private CoffeeRepository coffeeRepository;

    @Override
    public Optional<Coffee> findOneCoffee(String name) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("=================================");
        log.info("Coffee Found: {}", coffee);
        log.info("=================================");
        return coffee;
    }

    @Override
    public List<Coffee> findAll() {
        List<Coffee> list = coffeeRepository.findAll();
        log.info("All Coffee: {}", list);
        return list;
    }
}
