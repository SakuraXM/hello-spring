package com.sakura.redisrepository.enums;

/**
 * @author DM
 * @version 1.0
 * @description 订单状态枚举常量类
 * @date 2022/08/29
 */
public enum OrderStateEnum {
    /**
     * 初始状态
     */
    INIT,
    /**
     * 支付状态
     */
    PAID,
    /**
     * 等待状态
     */
    BREWING,
    /**
     * 初始状态
     */
    BREWED,
    /**
     * 初始状态
     */
    TAKEN,
    /**
     * 取消状态
     */
    CANCELLED
}
