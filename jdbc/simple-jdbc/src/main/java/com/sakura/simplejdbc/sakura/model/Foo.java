package com.sakura.simplejdbc.sakura.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author DM
 * @version 1.0
 * @description Foo对象模型
 * @date 2022/06/16
 */
@Data
@Builder
public class Foo {
    /**
     * ID字段
     */
    private Long id;
    /**
     * bar字段
     */
    private String bar;
}

