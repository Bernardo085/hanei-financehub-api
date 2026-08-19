package com.hanei.financehub.api.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    void deveSomarDoisValoresCorretamente() {
        Money a = Money.of("100.50");
        Money b = Money.of("50.25");

        assertThat(a.add(b)).isEqualTo(Money.of("150.75"));
    }

    @Test
    void deveArredondarComHalfEven() {
        Money valor = Money.of("2.005");
        assertThat(valor.asBigDecimal().toPlainString()).isEqualTo("2.00");
    }

    @Test
    void deveIdentificarValorNegativo() {
        Money valor = Money.of("-10.00");
        assertThat(valor.isNegative()).isTrue();
    }

    @Test
    void deveCompararValoresIgnorandoEscalaExcedente() {
        assertThat(Money.of("10")).isEqualTo(Money.of("10.00"));
    }
}
