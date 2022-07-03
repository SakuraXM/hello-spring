package com.sakura.mongodb.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.sakura.mongodb.model.Coffee;
import com.sakura.mongodb.repository.CoffeeRepository;
import com.sakura.mongodb.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡信息服务接口实现类
 * @date 2022/07/02
 */
@Slf4j
@Service
public class CoffeeServiceImpl implements ICoffeeService {
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private CoffeeRepository coffeeRepository;

    @Override
    public Coffee saveCoffeeToMongo(String name, double amount) {
        Coffee espresso = Coffee.builder()
                .name(name)
                .price(Money.of(CurrencyUnit.of("CNY"), amount))
                .createTime(new Date())
                .updateTime(new Date()).build();
        Coffee coffee = mongoTemplate.save(espresso);
        log.info("Coffee save to mongodb: {}", coffee);
        return coffee;
    }

    @Override
    public List<Coffee> findByName(String name) {
        List<Coffee> list = mongoTemplate.find(Query.query(Criteria.where("name").is(name)), Coffee.class);
        log.info("Find {} coffee", list.size());
        list.forEach(item -> log.info("Find coffee: {}", item));
        return list;
    }

    @Override
    public long modifyByFields(String name, long amount) {
        UpdateResult result = mongoTemplate.updateFirst(Query.query(Criteria.where("name").is(name)),
                new Update().set("price", Money.ofMajor(CurrencyUnit.of("CNY"), 30))
                        .currentDate("updateTime"), Coffee.class);
        log.info("Update result count: {}", result.getModifiedCount());
        return result.getModifiedCount();
    }

    @Override
    public Coffee findById(String id) {
        Coffee updateData = mongoTemplate.findById(id, Coffee.class);
        log.info("Update result: {}", updateData);
        return updateData;
    }

    @Override
    public long removeData(Object obj) {
        DeleteResult result = mongoTemplate.remove(obj);
        long count = result.getDeletedCount();
        log.info("Coffee remove count: {}", count);
        return count;
    }

    @Override
    public void insert(List<Coffee> list) {
        coffeeRepository.insert(list);
    }

    @Override
    public List<Coffee> findAllByName() {
        List<Coffee> list = coffeeRepository.findAll(Sort.by("name"));
        list.forEach(item -> log.info("Insert coffee data: {}", item));
        return list;
    }

    @Override
    public void saveCoffee(Coffee coffee) {
        coffeeRepository.save(coffee);
    }

    @Override
    public List<Coffee> cFindByName(String name) {
        List<Coffee> list = coffeeRepository.findByName(name);
        list.forEach(item -> log.info("Modified coffee info: {}", item));
        return list;
    }

    @Override
    public void deleteAll() {
        coffeeRepository.deleteAll();
    }

}
