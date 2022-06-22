package com.sakura.mybatis.service;

import com.sakura.mybatis.model.Coffee;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡服务接口
 * @date 2022/06/22
 */
public interface ICoffeeService {

    /**
     * @param name  咖啡名称
     * @param price 咖啡价格
     * @return int
     * @description 保存咖啡信息
     */
    int save(String name, double price);

    /**
     * @param id 主键
     * @return Coffee
     * @description 根据ID查询咖啡信息
     */
    Coffee findById(Long id);

}
