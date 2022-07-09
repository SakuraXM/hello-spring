package com.sakura.cachewithredis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡实体类
 * @date 2022/07/09
 */
@Data
@Entity
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COFFEE")
public class Coffee extends BaseEntity implements Serializable {

    private String name;

    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money price;
}
