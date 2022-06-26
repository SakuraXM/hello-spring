package com.sakura.mybatisgenerator.service;

import com.sakura.mybatisgenerator.model.auto.Coffee;
import com.sakura.mybatisgenerator.model.auto.CoffeeExample;

import java.util.List;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡业务接口
 * @date 2022/06/26
 */
public interface ICoffeeService {

    /**
     * @param coffee 咖啡信息
     * @return int
     * @description 保存咖啡信息
     */
    int insertCoffee(Coffee coffee);

    /**
     * @param id 主键
     * @return Coffee
     * @description 根据ID查询信息
     */
    Coffee selectById(Long id);

    /**
     * @param example 咖啡信息模板
     * @return List<Coffee>
     * @description 根据模板查询咖啡信息
     */
    List<Coffee> selectByCoffeeExample(CoffeeExample example);

}
