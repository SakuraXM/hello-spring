package com.sakura.datasource.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author DM
 * @version 1.0
 * @description 利用CommandLineRunner预先加载数据
 * @date 2022/05/28
 */
@Component
@Order(value = 2)
public class MyStartupRunnerTwo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 MyStartupRunnerOne Order 2<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
