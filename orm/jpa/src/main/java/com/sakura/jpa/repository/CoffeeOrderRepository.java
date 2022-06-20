package com.sakura.jpa.repository;

import com.sakura.jpa.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡订单数据库操作类
 * @date 2022/06/19
 */
public class CoffeeOrderRepository implements CrudRepository<CoffeeOrder, Long> {
    @Override
    public <S extends CoffeeOrder> S save(S entity) {
        return null;
    }

    @Override
    public <S extends CoffeeOrder> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CoffeeOrder> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<CoffeeOrder> findAll() {
        return null;
    }

    @Override
    public Iterable<CoffeeOrder> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(CoffeeOrder entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends CoffeeOrder> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
