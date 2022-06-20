package com.sakura.springbucks.initial;

import com.sakura.springbucks.enums.OrderStateEnum;
import com.sakura.springbucks.model.Coffee;
import com.sakura.springbucks.model.CoffeeOrder;
import com.sakura.springbucks.service.ICoffeeOrderService;
import com.sakura.springbucks.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动初始化
 * @date 2022/06/20
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private ICoffeeService coffeeService;
    @Resource
    private ICoffeeOrderService coffeeOrderService;

    @Override
    public void run(String... args) throws Exception {
        coffeeService.findAll();
        Optional<Coffee> coffee = coffeeService.findOneCoffee("Latte");
        if (coffee.isPresent()) {
            CoffeeOrder order = coffeeOrderService.createOrder("Li Lei", coffee.get());
            log.info("Update INIT to PAID: {}", coffeeOrderService.updateState(order, OrderStateEnum.PAID));
            log.info("Update PAID to INIT: {}", coffeeOrderService.updateState(order, OrderStateEnum.INIT));
        }
    }
}
