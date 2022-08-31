package com.sakura.redisrepository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @author DM
 * @version 1.0
 * @description Redis咖啡缓存类
 * @date 2022/08/30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "springbucks-coffee", timeToLive = 60)
public class CoffeeCache {

    @Id
    private Long id;

    @Indexed
    private String name;

    private Money price;

}
