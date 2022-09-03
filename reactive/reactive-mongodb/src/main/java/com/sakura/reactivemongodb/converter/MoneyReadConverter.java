package com.sakura.reactivemongodb.converter;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * Long to Long Money 类型转换
 *
 * @author DM
 * @version 1.0
 * @date 2022/09/03
 */
public class MoneyReadConverter implements Converter<Long, Money> {
    @Override
    public Money convert(Long source) {
        return Money.ofMinor(CurrencyUnit.of("CNY"), source);
    }
}
