package com.sakura.redisrepository.repository;

import com.sakura.redisrepository.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡数据库操作接口
 * @date 2022/08/29
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
