package com.hanei.financehub.api.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    void deveSomarDoisValoresCorretamente() {
        Money a = Money.of("100.50");
        Money b = Money.of("50.25");
        assertThat(a.add(b)).isEqualTo(Money.of("150.75"));
    }

    @Test
    void deveSubtrairDoisValoresCorretamente() {
        Money a = Money.of("100.00");
        Money b = Money.of("30.00");
        assertThat(a.subtract(b)).isEqualTo(Money.of("70.00"));
    }

    @Test
    void deveMultiplicarPorUmFator() {
        Money a = Money.of("10.00");
        assertThat(a.multiply(new BigDecimal("3"))).isEqualTo(Money.of("30.00"));
    }

    @Test
    void deveArredondarComHalfEven() {
        Money valor = Money.of("2.005");
        assertThat(valor.asBigDecimal().toPlainString()).isEqualTo("2.00");
    }

    @Test
    void deveIdentificarValorNegativoEPositivo() {
        assertThat(Money.of("-10.00").isNegative()).isTrue();
        assertThat(Money.of("10.00").isPositive()).isTrue();
        assertThat(Money.ZERO.isNegative()).isFalse();
        assertThat(Money.ZERO.isPositive()).isFalse();
    }

    @Test
    void deveCompararValores() {
        Money maior = Money.of("100");
        Money menor = Money.of("50");

        assertThat(maior.isGreaterThanOrEqual(menor)).isTrue();
        assertThat(menor.isLessThan(maior)).isTrue();
        assertThat(maior.compareTo(menor)).isGreaterThan(0);
    }

    @Test
    void deveAceitarConstrutorPorLong() {
        assertThat(Money.of(100L)).isEqualTo(Money.of("100.00"));
    }

    @Test
    void deveCompararValoresIgnorandoEscalaExcedente() {
        assertThat(Money.of("10")).isEqualTo(Money.of("10.00"));
        assertThat(Money.of("10")).hasSameHashCodeAs(Money.of("10.00"));
    }

    @Test
    void deveGerarToStringLegivel() {
        assertThat(Money.of("42.50").toString()).isEqualTo("42.50");
    }
}