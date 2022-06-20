package com.sakura.jpacomplex.repository;

import com.sakura.jpacomplex.model.Coffee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡数据库操作类
 * @date 2022/06/20
 */
public class CoffeeRepository implements CrudRepository<Coffee, Long> {
    @Override
    public <S extends Coffee> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Coffee> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Coffee> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Coffee> findAll() {
        return null;
    }

    @Override
    public Iterable<Coffee> findAllById(Iterable<Long> longs) {
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
    public void delete(Coffee entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Coffee> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
