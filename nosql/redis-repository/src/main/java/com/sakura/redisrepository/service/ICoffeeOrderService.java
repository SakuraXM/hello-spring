package com.sakura.redisrepository.service;

import com.sakura.redisrepository.enums.OrderStateEnum;
import com.sakura.redisrepository.model.Coffee;
import com.sakura.redisrepository.model.CoffeeOrder;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡订单服务接口
 * @date 2022/08/29
 */
public interface ICoffeeOrderService {
    /**
     * @param customer 客户
     * @param coffees  咖啡
     * @return CoffeeOrder
     * @description 创建订单接口
     */
    CoffeeOrder createOrder(String customer, Coffee... coffees);

    /**
     * @param order     订单信息
     * @param stateEnum 订单状态
     * @return boolean
     * @description 状态更新接口
     */
    boolean updateState(CoffeeOrder order, OrderStateEnum stateEnum);

}
