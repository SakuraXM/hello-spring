package com.sakura.jpacomplex.repository;

import com.sakura.jpacomplex.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡订单数据库操作接口
 * @date 2022/06/20
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
