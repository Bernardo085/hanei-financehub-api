package com.hanei.financehub.api.domain.model;

import com.hanei.financehub.api.domain.exception.BusinessRuleException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

public class Transaction {

    private final String id;
    private final String userId;
    private final String accountId;
    private String categoryId;
    private final TransactionType type;
    private final Money amount;
    private LocalDate competenceDate;
    private String description;
    private TransactionStatus status;
    private final boolean recurrent;
    private TransferDetails transferDetails;
    private ImportMetadata importMetadata;
    private final Instant createdAt;
    private Instant updatedAt;

    private Transaction(String id, String userId, String accountId, String categoryId, TransactionType type,
                        Money amount, LocalDate competenceDate, String description, boolean recurrent,
                        Instant createdAt) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId);
        this.accountId = Objects.requireNonNull(accountId);
        this.categoryId = categoryId;
        this.type = Objects.requireNonNull(type);
        if (!amount.isPositive()) {
            throw new BusinessRuleException("O valor de um lancamento deve ser positivo.");
        }
        this.amount = amount;
        this.competenceDate = Objects.requireNonNull(competenceDate);
        this.description = description;
        this.status = TransactionStatus.PENDING;
        this.recurrent = recurrent;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public static Transaction expense(String userId, String accountId, String categoryId, Money amount,
                                      LocalDate competenceDate, String description, boolean recurrent) {
        return new Transaction(null, userId, accountId, categoryId, TransactionType.EXPENSE, amount,
                competenceDate, description, recurrent, Instant.now());
    }

    public static Transaction income(String userId, String accountId, String categoryId, Money amount,
                                     LocalDate competenceDate, String description, boolean recurrent) {
        return new Transaction(null, userId, accountId, categoryId, TransactionType.INCOME, amount,
                competenceDate, description, recurrent, Instant.now());
    }

    public static Transaction transfer(String userId, String fromAccountId, String toAccountId, Money amount,
                                       LocalDate competenceDate, String description) {
        Transaction tx = new Transaction(null, userId, fromAccountId, null, TransactionType.TRANSFER, amount,
                competenceDate, description, false, Instant.now());
        tx.transferDetails = new TransferDetails(fromAccountId, toAccountId);
        return tx;
    }

    public static Transaction reconstitute(String id, String userId, String accountId, String categoryId,
                                           TransactionType type, Money amount, LocalDate competenceDate, String description,
                                           TransactionStatus status, boolean recurrent, TransferDetails transferDetails,
                                           ImportMetadata importMetadata, Instant createdAt, Instant updatedAt) {
        Transaction tx = new Transaction(id, userId, accountId, categoryId, type, amount, competenceDate,
                description, recurrent, createdAt);
        tx.status = status;
        tx.transferDetails = transferDetails;
        tx.importMetadata = importMetadata;
        tx.updatedAt = updatedAt;
        return tx;
    }

    public void complete() {
        this.status = TransactionStatus.COMPLETED;
        touch();
    }

    public void cancel() {
        if (this.status == TransactionStatus.COMPLETED) {
            throw new BusinessRuleException("Nao e possivel cancelar um lancamento ja concluido.");
        }
        this.status = TransactionStatus.CANCELLED;
        touch();
    }

    public void attachImportMetadata(String source, String externalFitId) {
        this.importMetadata = new ImportMetadata(source, externalFitId);
        touch();
    }

    private void touch() {
        this.updatedAt = Instant.now();
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getAccountId() { return accountId; }
    public String getCategoryId() { return categoryId; }
    public TransactionType getType() { return type; }
    public Money getAmount() { return amount; }
    public LocalDate getCompetenceDate() { return competenceDate; }
    public String getDescription() { return description; }
    public TransactionStatus getStatus() { return status; }
    public boolean isRecurrent() { return recurrent; }
    public TransferDetails getTransferDetails() { return transferDetails; }
    public ImportMetadata getImportMetadata() { return importMetadata; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}