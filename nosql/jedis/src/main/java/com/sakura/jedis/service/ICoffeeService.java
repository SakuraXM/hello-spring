package com.sakura.jedis.service;

import com.sakura.jedis.model.Coffee;

import java.util.List;
import java.util.Optional;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡服务接口
 * @date 2022/06/20
 */
public interface ICoffeeService {
    /**
     * @param name 咖啡名称
     * @return Optional<Coffee>
     * @description 更加名称查询咖啡信息
     */
    Optional<Coffee> findOneCoffee(String name);

    /**
     * @return List<Coffee>
     * @description 查询所有咖啡
     */
    List<Coffee> findAll();

}
