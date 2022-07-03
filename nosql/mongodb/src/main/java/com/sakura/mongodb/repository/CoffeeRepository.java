package com.sakura.mongodb.repository;

import com.sakura.mongodb.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author DM
 * @version 1.0
 * @description Coffee数据库操作接口
 * @date 2022/07/02
 */
public interface CoffeeRepository extends MongoRepository<Coffee, String> {

    /**
     * @param name
     * @return List<Coffee>
     * @description 根据咖啡名称查找咖啡
     */
    List<Coffee> findByName(String name);

}
