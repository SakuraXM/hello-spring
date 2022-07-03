package com.sakura.mongodb.converter;

import org.bson.Document;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * @author DM
 * @version 1.0
 * @description Money类型转换(Document对象=========>>Money对象)
 * @date 2022/07/02
 */
public class MoneyReadConverter implements Converter<Document, Money> {

    @Override
    public Money convert(Document source) {
        Document money = (Document) source.get("money");
        double amount = Double.parseDouble(money.getString("amount"));
        String currency = ((Document) money.get("currency")).getString("code");
        return Money.of(CurrencyUnit.of(currency), amount);
    }

}
