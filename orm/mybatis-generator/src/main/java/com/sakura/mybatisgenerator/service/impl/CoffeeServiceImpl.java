package com.sakura.mybatisgenerator.service.impl;

import com.sakura.mybatisgenerator.mapper.manual.ICoffeeMapper;
import com.sakura.mybatisgenerator.model.auto.Coffee;
import com.sakura.mybatisgenerator.model.auto.CoffeeExample;
import com.sakura.mybatisgenerator.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡业务接口实现类
 * @date 2022/06/26
 */
@Slf4j
@Service
public class CoffeeServiceImpl implements ICoffeeService {
    @Resource
    private ICoffeeMapper coffeeMapper;

    @Override
    public int insertCoffee(Coffee coffee) {
        return coffeeMapper.insert(coffee);
    }

    @Override
    public Coffee selectById(Long id) {
        return coffeeMapper.selectById(id);
    }

    @Override
    public List<Coffee> selectByCoffeeExample(CoffeeExample example) {
        return coffeeMapper.selectByExample(example);
    }
}
