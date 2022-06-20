package com.sakura.jpa.repository;

import com.sakura.jpa.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡订单数据库操作接口
 * @date 2022/06/19
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
