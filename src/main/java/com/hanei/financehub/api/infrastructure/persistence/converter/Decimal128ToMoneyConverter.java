package com.hanei.financehub.api.infrastructure.persistence.converter;

import com.hanei.financehub.api.domain.model.Money;
import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class Decimal128ToMoneyConverter implements Converter<Decimal128, Money> {
    @Override
    public Money convert(Decimal128 source) {
        return Money.of(source.bigDecimalValue());
    }
}