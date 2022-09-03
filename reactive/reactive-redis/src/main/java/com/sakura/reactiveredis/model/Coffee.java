package com.sakura.reactiveredis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Coffee实体信息类
 *
 * @author DM
 * @version 1.0
 * @date 2022/09/03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    private Long id;
    private String name;
    private Long price;
}
