package org.sba.kata.banking;


import static org.sba.kata.banking.Money.anAmountOf;
import static org.sba.kata.banking.exception.BankingException.OperationType.DEPOSIT;
import static org.sba.kata.banking.exception.BankingException.OperationType.TRANSFER;

import java.math.BigDecimal;
import java.util.Objects;
import org.sba.kata.banking.exception.BankingException;

public class UnboundedAccount implements Account{

    private final Money balance;

    public static UnboundedAccount anAccountWith(Money amount) {
        return new UnboundedAccount(amount);
    }

    public static UnboundedAccount anEmptyAccount() {
        return new UnboundedAccount(anAmountOf(BigDecimal.ZERO));
    }

    private UnboundedAccount(Money anAmount) {
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
        balance.less(anAmount);
    }

    @Override
    public void transfer(Money money, Account destinationAccount) {
        if(this.equals(destinationAccount)) {
            BankingException.invalidOperation(TRANSFER);
        }
        destinationAccount.deposit(money);
        this.withdraw(money);
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
        UnboundedAccount account = (UnboundedAccount) o;
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