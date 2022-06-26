package com.sakura.mybatispagehelper.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author DM
 * @version 1.0
 * @description Long与Money之间转换的TypeHandler
 * @date 2022/06/22
 */
public class MoneyTypeHandler extends BaseTypeHandler<Money> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Money money, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i, money.getAmountMinorLong());
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return parseMoney(resultSet.getLong(s));
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return parseMoney(resultSet.getLong(i));
    }

    @Override
    public Money getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return parseMoney(callableStatement.getLong(i));
    }

    /**
     * @param value 咖啡价格
     * @return Money
     * @description 将Long类型数据转换为人民币Money类型
     */
    private Money parseMoney(Long value) {
//        return Money.of(CurrencyUnit.of("CNY"), value / 100.0);
        return Money.ofMinor(CurrencyUnit.of("CNY"), value);
    }
}
