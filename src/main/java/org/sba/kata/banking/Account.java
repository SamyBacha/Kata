package org.sba.kata.banking;

public interface Account {

  void deposit(final Money anAmount);

  void withdraw(final Money anAmount);

  void transfer(Money money, Account destinationAccount);
}
