package com.sakura.redis;

import com.sakura.redis.model.Coffee;
import io.lettuce.core.ReadFrom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    /**
     * 自定义Redis存储对象
     *
     * @param redisConnectionFactory
     * @return
     */
//    @Bean
//    public RedisTemplate<String, Coffee> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Coffee> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }

    /**
     * 优先从master库读取数据
     *
     * @return
     */
    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }

}
