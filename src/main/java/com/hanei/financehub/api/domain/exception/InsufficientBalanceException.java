package com.hanei.financehub.api.domain.exception;

/**
 * Lancada quando uma operacao de debito ou transferencia excede o saldo
 * disponivel em uma Account.
 */
public class InsufficientBalanceException extends BusinessRuleException {

    public InsufficientBalanceException(String accountId) {
        super("Saldo insuficiente na conta " + accountId + " para concluir a operacao.");
    }
}
