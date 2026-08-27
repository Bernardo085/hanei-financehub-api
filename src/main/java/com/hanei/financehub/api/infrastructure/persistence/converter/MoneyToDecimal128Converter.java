package com.hanei.financehub.api.infrastructure.persistence.converter;

import com.hanei.financehub.api.domain.model.Money;
import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class MoneyToDecimal128Converter implements Converter<Money, Decimal128> {
    @Override
    public Decimal128 convert(Money source) {
        return new Decimal128(source.asBigDecimal());
    }
}