package com.sakura.mybatis.service.impl;

import com.sakura.mybatis.mapper.CoffeeMapper;
import com.sakura.mybatis.mapper.ICoffeeMapper;
import com.sakura.mybatis.model.Coffee;
import com.sakura.mybatis.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡服务接口实现
 * @date 2022/06/22
 */
@Slf4j
@Service
public class CoffeeServiceImpl implements ICoffeeService {
    @Resource
    private CoffeeMapper coffeeMapper;
    @Resource
    private ICoffeeMapper mapper;

    @Override
    public int save(String name, double price) {
        Coffee c = Coffee.builder()
                .name(name)
                .price(Money.of(CurrencyUnit.of("CNY"), price))
                .build();
        int i = coffeeMapper.save(c);
//        int i = mapper.save(c);
        log.info("========================生产的咖啡信息===================");
        log.info("Product Coffee info: {}", c);
        return i;
    }

    @Override
    public Coffee findById(Long id) {
        return coffeeMapper.findById(id);
//        return mapper.findById(id);
    }
}
