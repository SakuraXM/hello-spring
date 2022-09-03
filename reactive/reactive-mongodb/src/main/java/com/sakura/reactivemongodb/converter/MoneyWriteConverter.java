package com.sakura.reactivemongodb.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * Money to Long write 类型转换
 *
 * @author DM
 * @version 1.0
 * @date 2022/09/03
 */
public class MoneyWriteConverter implements Converter<Money, Long> {
    @Override
    public Long convert(Money source) {
        return source.getAmountMinorLong();
    }
}
