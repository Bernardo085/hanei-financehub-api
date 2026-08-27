package com.hanei.financehub.api.domain.model;

import com.hanei.financehub.api.domain.exception.BusinessRuleException;
import com.hanei.financehub.api.domain.exception.InsufficientBalanceException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AccountTest {

    private Account openAccount(String saldoInicial) {
        return Account.open("user-1", "Conta Corrente", AccountType.CHECKING, "#0F8B8D",
                Money.of(saldoInicial), Money.of("0"));
    }

    @Test
    void deveAbrirContaComSaldoInicial() {
        Account conta = openAccount("100.00");
        assertThat(conta.getCurrentBalance()).isEqualTo(Money.of("100.00"));
        assertThat(conta.isArchived()).isFalse();
    }

    @Test
    void deveCreditarValorNaConta() {
        Account conta = openAccount("100.00");
        conta.credit(Money.of("50.00"));
        assertThat(conta.getCurrentBalance()).isEqualTo(Money.of("150.00"));
    }

    @Test
    void deveDebitarValorDaConta() {
        Account conta = openAccount("100.00");
        conta.debit(Money.of("30.00"));
        assertThat(conta.getCurrentBalance()).isEqualTo(Money.of("70.00"));
    }

    @Test
    void deveRecusarDebitoComSaldoInsuficiente() {
        Account conta = openAccount("50.00");
        assertThatThrownBy(() -> conta.debit(Money.of("100.00")))
                .isInstanceOf(InsufficientBalanceException.class);
    }

    @Test
    void deveRecusarCreditoOuDebitoComValorNaoPositivo() {
        Account conta = openAccount("100.00");
        assertThatThrownBy(() -> conta.credit(Money.of("0")))
                .isInstanceOf(BusinessRuleException.class);
        assertThatThrownBy(() -> conta.debit(Money.of("-10.00")))
                .isInstanceOf(BusinessRuleException.class);
    }

    @Test
    void deveRecusarMovimentacaoEmContaArquivada() {
        Account conta = openAccount("100.00");
        conta.archive();

        assertThatThrownBy(() -> conta.credit(Money.of("10.00")))
                .isInstanceOf(BusinessRuleException.class)
                .hasMessageContaining("arquivada");
    }

    @Test
    void deveReativarContaArquivada() {
        Account conta = openAccount("100.00");
        conta.archive();
        conta.unarchive();

        conta.credit(Money.of("10.00"));
        assertThat(conta.getCurrentBalance()).isEqualTo(Money.of("110.00"));
    }

    @Test
    void deveRenomearConta() {
        Account conta = openAccount("100.00");
        conta.rename("Reserva de Emergencia");
        assertThat(conta.getName()).isEqualTo("Reserva de Emergencia");
    }
}