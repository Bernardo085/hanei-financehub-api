package com.hanei.financehub.api.domain.model;

public final class TransferDetails {

    private final String fromAccountId;
    private final String toAccountId;

    public TransferDetails(String fromAccountId, String toAccountId) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
    }

    public String getFromAccountId() { return fromAccountId; }
    public String getToAccountId() { return toAccountId; }
}