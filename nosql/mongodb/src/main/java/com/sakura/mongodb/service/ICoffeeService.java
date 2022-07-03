package com.sakura.mongodb.service;

import com.sakura.mongodb.model.Coffee;

import java.util.List;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡信息服务接口
 * @date 2022/07/02
 */
public interface ICoffeeService {

    /**
     * @param name   咖啡名称
     * @param amount 咖啡价格
     * @return Coffee
     * @description 保存咖啡信息至mongodb
     */
    Coffee saveCoffeeToMongo(String name, double amount);

    /**
     * @param name 咖啡名称
     * @return List<Coffee>
     * @description 查询咖啡信息列表
     */
    List<Coffee> findByName(String name);

    /**
     * @param name   咖啡名称
     * @param amount 咖啡价格
     * @return long
     * @description 修改影响的条数
     */
    long modifyByFields(String name, long amount);

    /**
     * @param id 主键
     * @return Coffee
     * @description 根据主键查询咖啡信息
     */
    Coffee findById(String id);

    /**
     * @param obj 移除参数
     * @return long
     * @description 根据主键删除
     */
    long removeData(Object obj);

    /**
     * @param list 咖啡信息
     * @description 保存接口
     */
    void insert(List<Coffee> list);

    /**
     * @return List<Coffee>
     * @description 更加咖啡名称查找所有咖啡信息
     */
    List<Coffee> findAllByName();

    /**
     * @param coffee 咖啡信息
     * @description 保存咖啡信息
     */
    void saveCoffee(Coffee coffee);

    /**
     * @param name 咖啡名称
     * @return List<Coffee>
     * @description 更加咖啡名称查找咖啡信息
     */
    List<Coffee> cFindByName(String name);

    /**
     * @description 删除所有数据
     */
    void deleteAll();

}
