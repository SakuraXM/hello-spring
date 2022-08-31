package com.sakura.redisrepository;

import com.sakura.redisrepository.converter.BytesToMoneyConverter;
import com.sakura.redisrepository.converter.MoneyToBytesConverter;
import io.lettuce.core.ReadFrom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.convert.RedisCustomConversions;

import java.util.Arrays;

@SpringBootApplication
public class RedisRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisRepositoryApplication.class, args);
    }

    /**
     * 优先从master库读取数据
     *
     * @return
     */
    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }

    /**
     * 添加自定义类型转换
     *
     * @return
     */
    @Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(Arrays.asList(new MoneyToBytesConverter(), new BytesToMoneyConverter()));
    }

}
