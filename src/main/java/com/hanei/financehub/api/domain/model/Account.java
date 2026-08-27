package com.hanei.financehub.api.domain.model;

import com.hanei.financehub.api.domain.exception.BusinessRuleException;
import com.hanei.financehub.api.domain.exception.InsufficientBalanceException;

import java.time.Instant;
import java.util.Objects;

public class Account {

    private final String id;
    private final String userId;
    private String name;
    private final AccountType type;
    private String colorHex;
    private final Money initialBalance;
    private Money currentBalance;
    private Money targetGoal;
    private boolean archived;
    private final Instant createdAt;
    private Instant updatedAt;

    private Account(String id, String userId, String name, AccountType type, String colorHex,
                    Money initialBalance, Money targetGoal, Instant createdAt) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId, "userId nao pode ser nulo");
        this.name = Objects.requireNonNull(name, "name nao pode ser nulo");
        this.type = Objects.requireNonNull(type, "type nao pode ser nulo");
        this.colorHex = colorHex;
        this.initialBalance = initialBalance;
        this.currentBalance = initialBalance;
        this.targetGoal = targetGoal;
        this.archived = false;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public static Account open(String userId, String name, AccountType type, String colorHex,
                               Money initialBalance, Money targetGoal) {
        return new Account(null, userId, name, type, colorHex, initialBalance, targetGoal, Instant.now());
    }

    public void credit(Money amount) {
        requireActive();
        requirePositive(amount);
        this.currentBalance = this.currentBalance.add(amount);
        touch();
    }

    public void debit(Money amount) {
        requireActive();
        requirePositive(amount);
        if (this.currentBalance.isLessThan(amount)) {
            throw new InsufficientBalanceException(this.id);
        }
        this.currentBalance = this.currentBalance.subtract(amount);
        touch();
    }

    public void archive() {
        this.archived = true;
        touch();
    }

    public void unarchive() {
        this.archived = false;
        touch();
    }

    public void rename(String newName) {
        this.name = Objects.requireNonNull(newName);
        touch();
    }

    private void requireActive() {
        if (archived) {
            throw new BusinessRuleException("Nao e possivel movimentar uma conta arquivada.");
        }
    }

    private void requirePositive(Money amount) {
        if (!amount.isPositive()) {
            throw new BusinessRuleException("O valor da movimentacao deve ser positivo.");
        }
    }

    private void touch() {
        this.updatedAt = Instant.now();
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public AccountType getType() { return type; }
    public String getColorHex() { return colorHex; }
    public Money getInitialBalance() { return initialBalance; }
    public Money getCurrentBalance() { return currentBalance; }
    public Money getTargetGoal() { return targetGoal; }
    public boolean isArchived() { return archived; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}