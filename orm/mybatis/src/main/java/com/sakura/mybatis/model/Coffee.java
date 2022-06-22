package com.sakura.mybatis.model;

import lombok.Builder;
import lombok.Data;
import org.joda.money.Money;

import java.util.Date;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡信息实体类
 * @date 2022/06/22
 */
@Data
@Builder
public class Coffee {
    /**
     * 主键
     */
    private Long id;
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
     * 修改时间
     */
    private Date updateTime;
}
