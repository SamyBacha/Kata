package org.sba.kata.banking;


import static org.sba.kata.banking.Money.anAmountOf;
import static org.sba.kata.banking.exception.BankingException.OperationType.TRANSFER;

import java.math.BigDecimal;
import java.util.Objects;
import org.sba.kata.banking.exception.BankingException;

public class UnboundedAccount2 implements Account{

    private final Money balance;

    public static UnboundedAccount2 anAccountWith(Money amount) {
        return new UnboundedAccount2(amount);
    }

    public static UnboundedAccount2 anEmptyAccount() {
        return new UnboundedAccount2(anAmountOf(BigDecimal.ZERO));
    }

    private UnboundedAccount2(Money anAmount) {
        this.balance = anAmount;
    }

    @Override
    public void deposit(final Money anAmount) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UnboundedAccount2 account = (UnboundedAccount2) o;
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