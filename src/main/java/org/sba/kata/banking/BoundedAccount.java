package org.sba.kata.banking;


import static org.sba.kata.banking.Money.anAmountOf;
import static org.sba.kata.banking.exception.BankingException.OperationType.DEPOSIT;
import static org.sba.kata.banking.exception.BankingException.OperationType.TRANSFER;
import static org.sba.kata.banking.exception.BankingException.OperationType.WITHDRAW;

import java.math.BigDecimal;
import java.util.Objects;
import org.sba.kata.banking.exception.BankingException;

public class BoundedAccount implements Account{
    private final Money balance;

    public static BoundedAccount anAccountWith(Money amount) {
        return new BoundedAccount(amount);
    }

    public static BoundedAccount anEmptyAccount() {
        return new BoundedAccount(anAmountOf(BigDecimal.ZERO));
    }

    private BoundedAccount(Money anAmount) {
        this.balance = anAmount;
    }

    @Override
    public void deposit(final Money anAmount) {
        if(anAmount.lessThan(anAmount)) {
            BankingException.invalidOperation(DEPOSIT);
        }
        balance.add(anAmount);
    }

    @Override
    public void withdraw(final Money anAmount) {
        if(this.balance.greaterThan(anAmount)) {
            BankingException.invalidOperation(WITHDRAW);
        }
        balance.less(anAmount);
    }

    @Override
    public void transfer(Money money, Account destinationAccount) {
        if(this.equals(destinationAccount)) {
            BankingException.invalidOperation(TRANSFER);
        }
        this.withdraw(money);
        destinationAccount.deposit(money);
    }

    @Override
    public void printStatement() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BoundedAccount account = (BoundedAccount) o;
        return Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance);
    }

    @Override
    public String toString() {
        return "Account{" + "balance=" + balance + '}';
    }
}