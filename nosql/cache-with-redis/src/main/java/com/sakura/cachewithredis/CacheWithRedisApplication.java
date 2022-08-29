package com.sakura.cachewithredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
//@EnableTransactionManagement
//@EnableJpaRepositories
@SpringBootApplication
public class CacheWithRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheWithRedisApplication.class, args);
	}

}
