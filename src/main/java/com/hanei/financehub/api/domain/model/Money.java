package com.hanei.financehub.api.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Value Object que representa um valor monetario.
 *
 * RNF01 - Precisao Monetaria Estrita: usa exclusivamente BigDecimal com
 * RoundingMode.HALF_EVEN. Nunca usar float/double para valores financeiros.
 *
 * Classe imutavel e livre de qualquer dependencia de framework (Spring,
 * MongoDB, etc.) conforme RNF11 - Clean Architecture / SOLID.
 */
public final class Money implements Comparable<Money> {

    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING = RoundingMode.HALF_EVEN;

    public static final Money ZERO = Money.of(BigDecimal.ZERO);

    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount.setScale(SCALE, ROUNDING);
    }

    public static Money of(BigDecimal amount) {
        Objects.requireNonNull(amount, "amount nao pode ser nulo");
        return new Money(amount);
    }

    public static Money of(String amount) {
        return Money.of(new BigDecimal(amount));
    }

    public static Money of(long amount) {
        return Money.of(BigDecimal.valueOf(amount));
    }

    public Money add(Money other) {
        return Money.of(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        return Money.of(this.amount.subtract(other.amount));
    }

    public Money multiply(BigDecimal factor) {
        return Money.of(this.amount.multiply(factor));
    }

    public boolean isNegative() {
        return amount.signum() < 0;
    }

    public boolean isPositive() {
        return amount.signum() > 0;
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return this.amount.compareTo(other.amount) >= 0;
    }

    public boolean isLessThan(Money other) {
        return this.amount.compareTo(other.amount) < 0;
    }

    public BigDecimal asBigDecimal() {
        return amount;
    }

    @Override
    public int compareTo(Money other) {
        return this.amount.compareTo(other.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return amount.compareTo(money.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount.stripTrailingZeros());
    }

    @Override
    public String toString() {
        return amount.toPlainString();
    }
}
