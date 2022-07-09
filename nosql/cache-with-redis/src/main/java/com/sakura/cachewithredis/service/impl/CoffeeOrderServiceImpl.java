package com.sakura.cachewithredis.service.impl;

import com.sakura.cachewithredis.enums.OrderStateEnum;
import com.sakura.cachewithredis.model.Coffee;
import com.sakura.cachewithredis.model.CoffeeOrder;
import com.sakura.cachewithredis.repository.CoffeeOrderRepository;
import com.sakura.cachewithredis.service.ICoffeeOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡订单服务接口实现类
 * @date 2022/07/09
 */
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
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
