package com.sakura.reactivemongodb;

import com.sakura.reactivemongodb.converter.MoneyReadConverter;
import com.sakura.reactivemongodb.converter.MoneyWriteConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@SpringBootApplication
public class ReactiveMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongodbApplication.class, args);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter(), new MoneyWriteConverter()));
    }

}
