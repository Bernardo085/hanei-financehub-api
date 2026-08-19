    package com.hanei.financehub.api.domain.exception;

    /**
     * Excecao base de dominio para violacoes de regra de negocio.
     * Nao depende de nenhum framework - e traduzida para HTTP/RFC 7807
     * apenas na camada adapters/in/web/handler (GlobalExceptionHandler).
     */
    public class BusinessRuleException extends RuntimeException {

        public BusinessRuleException(String message) {
            super(message);
        }

        public BusinessRuleException(String message, Throwable cause) {
            super(message, cause);
        }
    }
