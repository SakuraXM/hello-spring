package com.sakura.mybatisgenerator.initial;

import com.sakura.mybatisgenerator.model.auto.Coffee;
import com.sakura.mybatisgenerator.model.auto.CoffeeExample;
import com.sakura.mybatisgenerator.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DM
 * @version 1.0
 * @description 服务启动初始化
 * @date 2022/06/25
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private ICoffeeService coffeeService;

    @Override
    public void run(String... args) throws Exception {
//        generateArtifacts();
        operation();
    }

    /**
     * @throws Exception
     * @description MyBatis相关文件生成
     */
    private void generateArtifacts() throws Exception {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(
                this.getClass().getResourceAsStream("/generatorConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    private void operation() {
        Coffee espresso = new Coffee()
                .withName("espresso")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 20.0));
        int i = coffeeService.insertCoffee(espresso);
        log.info("Insert data: {}", i);

        Coffee latte = new Coffee()
                .withName("latte")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 30.0));
        int k = coffeeService.insertCoffee(latte);
        log.info("Insert data: {}", k);
        log.info("===========================================");
        Coffee coffee = coffeeService.selectById(Long.valueOf(k));
        log.info("Coffee info: {}", coffee);

        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andNameEqualTo("latte");
        List<Coffee> list = coffeeService.selectByCoffeeExample(example);
        list.forEach(item -> log.info("selectByCoffeeExample: {}", item));
    }

}
