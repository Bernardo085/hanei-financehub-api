package com.hanei.financehub.api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuracao central do MongoDB.
 *
 * Aqui entrarao, na etapa de modelagem de persistencia (Sprint 2+):
 *  - Converters customizados BigDecimal <-> Decimal128 (RNF01)
 *  - Registro de indices compostos (userId + competenceDate + accountId)
 */
@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        List<Object> converters = new ArrayList<>();
        // TODO (Sprint 2): registrar MoneyToDecimal128Converter e Decimal128ToMoneyConverter
        return new MongoCustomConversions(converters);
    }
}
