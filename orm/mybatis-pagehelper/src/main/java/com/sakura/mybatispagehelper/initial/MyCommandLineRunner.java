package com.sakura.mybatispagehelper.initial;

import com.github.pagehelper.PageInfo;
import com.sakura.mybatispagehelper.model.Coffee;
import com.sakura.mybatispagehelper.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动初始化
 * @date 2022/06/26
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private ICoffeeService coffeeService;

    @Override
    public void run(String... args) throws Exception {
        log.info("=======================================================");
        coffeeService.findAllWithRowBounds(new RowBounds(1, 3)).forEach(item -> log.info("Page(1) Coffee {}", item));
        coffeeService.findAllWithRowBounds(new RowBounds(2, 3)).forEach(item -> log.info("Page(2) Coffee {}", item));
        log.info("=======================================================");
        coffeeService.findAllWithRowBounds(new RowBounds(1, 0)).forEach(item -> log.info("Page(1) Coffee {}", item));
        log.info("=======================================================");
        coffeeService.findAllWithParam(1, 3).forEach(item -> log.info("Page(1) Coffee {}", item));
        List<Coffee> list = coffeeService.findAllWithParam(2, 3);
        PageInfo<Coffee> pageInfo = new PageInfo<>(list);
        log.info("PageInfo: {}", pageInfo);
    }

}
