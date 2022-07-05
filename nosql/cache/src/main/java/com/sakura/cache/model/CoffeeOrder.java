package com.sakura.cache.model;

import com.sakura.cache.enums.OrderStateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡订单实体类
 * @date 2022/07/05
 */
@Data
@Entity
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ORDER")
public class CoffeeOrder extends BaseEntity implements Serializable {

    private String customer;

    @ManyToMany
    @OrderBy(value = "id")
    @JoinTable(name = "T_ORDER_COFFEE")
    private List<Coffee> items;

    @Enumerated
    @Column(nullable = false)
    private OrderStateEnum state;
}
