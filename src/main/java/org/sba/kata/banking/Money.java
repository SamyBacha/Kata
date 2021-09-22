package org.sba.kata.banking;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private BigDecimal amount;

    private Money(final BigDecimal anAmount) {
        this.amount = anAmount;
    }

    public static Money anAmountOf(final BigDecimal anAmount) {
        return new Money(anAmount);
    }

    public void add(final Money anAmount) {
        amount = this.amount.add(anAmount.amount);
    }

    public void less(final Money anAmount) {
        amount = this.amount.subtract(anAmount.amount);
    }

    public boolean greaterThan(final Money anAmount) {
        return this.amount.compareTo(anAmount.amount) < 0;
    }

    public boolean lessThan(final Money anAmount) {
        return !greaterThan(anAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Money{" + "amount=" + amount + '}';
    }

}