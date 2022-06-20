package com.sakura.springbucks.repository;

import com.sakura.springbucks.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡订单数据库操作接口
 * @date 2022/06/20
 */
public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
}
