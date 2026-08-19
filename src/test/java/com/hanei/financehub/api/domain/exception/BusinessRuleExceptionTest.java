package com.hanei.financehub.api.domain.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BusinessRuleExceptionTest {

    @Test
    void deveCriarExcecaoComMensagem() {
        BusinessRuleException ex = new BusinessRuleException("regra violada");
        assertThat(ex.getMessage()).isEqualTo("regra violada");
    }

    @Test
    void deveCriarExcecaoComMensagemECausa() {
        Throwable causa = new RuntimeException("causa raiz");
        BusinessRuleException ex = new BusinessRuleException("regra violada", causa);

        assertThat(ex.getMessage()).isEqualTo("regra violada");
        assertThat(ex.getCause()).isEqualTo(causa);
    }

    @Test
    void deveMontarMensagemDeSaldoInsuficiente() {
        InsufficientBalanceException ex = new InsufficientBalanceException("acc-123");
        assertThat(ex.getMessage()).contains("acc-123");
    }
}