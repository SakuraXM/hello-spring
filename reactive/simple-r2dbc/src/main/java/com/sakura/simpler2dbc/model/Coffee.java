package com.sakura.simpler2dbc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;

/**
 * Coffee实体信息类
 *
 * @author DM
 * @version 1.0
 * @date 2022/09/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    /**
     * 主键
     */
    private String id;
    /**
     * 咖啡名称
     */
    private String name;
    /**
     * 咖啡价格
     */
    private Money price;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
