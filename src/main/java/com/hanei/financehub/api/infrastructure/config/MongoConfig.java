package com.hanei.financehub.api.infrastructure.config;

import com.hanei.financehub.api.infrastructure.persistence.converter.Decimal128ToMoneyConverter;
import com.hanei.financehub.api.infrastructure.persistence.converter.MoneyToDecimal128Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.List;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(List.of(
                new MoneyToDecimal128Converter(),
                new Decimal128ToMoneyConverter()
        ));
    }
}