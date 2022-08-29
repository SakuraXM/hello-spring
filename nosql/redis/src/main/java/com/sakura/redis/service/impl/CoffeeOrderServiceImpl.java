package com.sakura.redis.service.impl;

import com.sakura.redis.enums.OrderStateEnum;
import com.sakura.redis.model.Coffee;
import com.sakura.redis.model.CoffeeOrder;
import com.sakura.redis.repository.CoffeeOrderRepository;
import com.sakura.redis.service.ICoffeeOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡订单服务接口实现类
 * @date 2022/08/29
 */
@Slf4j
@Service
public class CoffeeOrderServiceImpl implements ICoffeeOrderService {
    @Resource
    private CoffeeOrderRepository coffeeOrderRepository;

    @Override
    public CoffeeOrder createOrder(String customer, Coffee... coffees) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(Arrays.asList(coffees))
                .state(OrderStateEnum.INIT)
                .build();
        CoffeeOrder coffeeOrder = coffeeOrderRepository.save(order);
        log.info("===============创建咖啡订单==================");
        log.info("New Order: {}", coffeeOrder);
        log.info("===============创建咖啡订单==================");
        return coffeeOrder;
    }

    @Override
    public boolean updateState(CoffeeOrder order, OrderStateEnum stateEnum) {
        if (stateEnum.compareTo(order.getState()) <= 0) {
            log.warn("Wrong StateEnum order: {}, {}", stateEnum, order.getState());
            return false;
        } else {
            order.setState(stateEnum);
            coffeeOrderRepository.save(order);
            log.info("===============咖啡订单状态更新==================");
            log.info("Updated Order: {}", order);
            log.info("===============咖啡订单状态更新==================");
        }
        return true;
    }
}
