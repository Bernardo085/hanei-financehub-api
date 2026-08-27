package com.hanei.financehub.api.domain.model;

import com.hanei.financehub.api.domain.exception.BusinessRuleException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TransactionTest {

    @Test
    void deveCriarLancamentoDeDespesa() {
        Transaction tx = Transaction.expense("user-1", "acc-1", "cat-1", Money.of("150.75"),
                LocalDate.of(2026, 8, 17), "Supermercado", false);

        assertThat(tx.getType()).isEqualTo(TransactionType.EXPENSE);
        assertThat(tx.getStatus()).isEqualTo(TransactionStatus.PENDING);
    }

    @Test
    void deveCriarLancamentoDeReceita() {
        Transaction tx = Transaction.income("user-1", "acc-1", "cat-1", Money.of("3000.00"),
                LocalDate.of(2026, 8, 5), "Salario", true);

        assertThat(tx.getType()).isEqualTo(TransactionType.INCOME);
        assertThat(tx.isRecurrent()).isTrue();
    }

    @Test
    void deveCriarTransferenciaEntreContas() {
        Transaction tx = Transaction.transfer("user-1", "acc-origem", "acc-destino", Money.of("500.00"),
                LocalDate.now(), "Reserva mensal");

        assertThat(tx.getType()).isEqualTo(TransactionType.TRANSFER);
        assertThat(tx.getTransferDetails().getFromAccountId()).isEqualTo("acc-origem");
        assertThat(tx.getTransferDetails().getToAccountId()).isEqualTo("acc-destino");
    }

    @Test
    void deveRecusarValorNaoPositivo() {
        assertThatThrownBy(() -> Transaction.expense("user-1", "acc-1", "cat-1", Money.of("0"),
                LocalDate.now(), "Invalido", false))
                .isInstanceOf(BusinessRuleException.class);
    }

    @Test
    void deveConcluirLancamento() {
        Transaction tx = Transaction.expense("user-1", "acc-1", "cat-1", Money.of("50.00"),
                LocalDate.now(), "Teste", false);
        tx.complete();
        assertThat(tx.getStatus()).isEqualTo(TransactionStatus.COMPLETED);
    }

    @Test
    void deveCancelarLancamentoPendente() {
        Transaction tx = Transaction.expense("user-1", "acc-1", "cat-1", Money.of("50.00"),
                LocalDate.now(), "Teste", false);
        tx.cancel();
        assertThat(tx.getStatus()).isEqualTo(TransactionStatus.CANCELLED);
    }

    @Test
    void deveRecusarCancelamentoDeLancamentoJaConcluido() {
        Transaction tx = Transaction.expense("user-1", "acc-1", "cat-1", Money.of("50.00"),
                LocalDate.now(), "Teste", false);
        tx.complete();

        assertThatThrownBy(tx::cancel).isInstanceOf(BusinessRuleException.class);
    }

    @Test
    void deveAnexarMetadadosDeImportacao() {
        Transaction tx = Transaction.expense("user-1", "acc-1", "cat-1", Money.of("50.00"),
                LocalDate.now(), "Teste", false);
        tx.attachImportMetadata("OFX_IMPORT", "20260817001");

        assertThat(tx.getImportMetadata().getSource()).isEqualTo("OFX_IMPORT");
    }
}