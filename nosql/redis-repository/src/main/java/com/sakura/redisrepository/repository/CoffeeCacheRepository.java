package com.sakura.redisrepository.repository;

import com.sakura.redisrepository.model.CoffeeCache;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡信息缓存操作接口
 * @date 2022/08/30
 */
public interface CoffeeCacheRepository extends CrudRepository<CoffeeCache, Long> {
    /**
     * 根据咖啡名称查询咖啡信息
     *
     * @param name
     * @return
     */
    Optional<CoffeeCache> findOneByName(String name);
}
