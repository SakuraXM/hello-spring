package com.sakura.simpler2dbc;

import com.sakura.simpler2dbc.converter.MoneyReadConverter;
import com.sakura.simpler2dbc.converter.MoneyWriteConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;

import java.util.Arrays;

@SpringBootApplication
public class SimpleR2dbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleR2dbcApplication.class, args);
    }

    /**
     * 追加自定义转换类
     *
     * @return
     */
    @Bean
    public R2dbcCustomConversions r2dbcCustomConversions() {
        return new R2dbcCustomConversions(R2dbcCustomConversions.STORE_CONVERSIONS,
                Arrays.asList(new MoneyReadConverter(), new MoneyWriteConverter()));
    }

}
