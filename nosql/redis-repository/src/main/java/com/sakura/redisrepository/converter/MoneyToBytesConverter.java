package com.sakura.redisrepository.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.nio.charset.StandardCharsets;

/**
 * Money转换为byte数组
 *
 * @author DM
 * @version 1.0
 * @date 2022/08/30
 */
@WritingConverter
public class MoneyToBytesConverter implements Converter<Money, byte[]> {
    @Override
    public byte[] convert(Money source) {
        String value = Long.toString(source.getAmountMajorLong());
        return value.getBytes(StandardCharsets.UTF_8);
    }
}
