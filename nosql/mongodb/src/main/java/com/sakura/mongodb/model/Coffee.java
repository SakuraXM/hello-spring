package com.sakura.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡实体类
 * @date 2022/06/20
 */
@Data
@Builder
@Document(collection = "Coffee")
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    @Id
    private String id;
    @Field(value = "name")
    private String name;
    @Field(value = "price")
    private Money price;
    @Field(value = "create_time")
    private Date createTime;
    @Field(value = "update_time")
    private Date updateTime;
}
