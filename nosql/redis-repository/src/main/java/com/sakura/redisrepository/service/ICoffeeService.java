package com.sakura.redisrepository.service;

import com.sakura.redisrepository.model.Coffee;

import java.util.List;
import java.util.Optional;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡服务接口
 * @date 2022/08/29
 */
public interface ICoffeeService {
    /**
     * @return List<Coffee>
     * @description 查询所有咖啡信息
     */
    List<Coffee> findAll();

    /**
     * @param name 咖啡名称
     * @return Optional<Coffee>
     * @description 根据咖啡名称从缓存获取单个咖啡信息
     */
    Optional<Coffee> findSimpleCoffeeFromCache(String name);

    /**
     * @param name 咖啡名称
     * @return Optional<Coffee>
     * @description 根据咖啡名称查询咖啡信息
     */
    Optional<Coffee> findOneCoffee(String name);

}
