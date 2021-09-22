package org.sba.kata.banking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.sba.kata.banking.Money.anAmountOf;
import static org.sba.kata.banking.UnboundedAccount.anAccountWith;
import static org.sba.kata.banking.UnboundedAccount.anEmptyAccount;
import static org.sba.kata.banking.exception.BankingException.OperationType.DEPOSIT;
import static org.sba.kata.banking.exception.BankingException.OperationType.TRANSFER;
import static org.sba.kata.banking.exception.BankingException.OperationType.WITHDRAW;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.sba.kata.banking.exception.BankingException;

class UnboundedAccountTest {

  @Test
  void deposit_an_amount_should_increase_the_balance() {
    UnboundedAccount account = anEmptyAccount();
    account.deposit(anAmountOf(new BigDecimal("10.0")));
    assertThat(account).isEqualTo(anAccountWith(anAmountOf(new BigDecimal("10.0"))));
  }

  @Test
  void deposit_an_amount_should_be_positive() {
    UnboundedAccount account = anEmptyAccount();

    BankingException exception = assertThrows(BankingException.class, () -> account.deposit(anAmountOf(new BigDecimal("-10.0"))));
    assertThat(exception.hasType(DEPOSIT)).isTrue();
  }

  @Test
  void withdraw_an_amount_should_decrease_the_balance() {
    UnboundedAccount account = anAccountWith(anAmountOf(new BigDecimal("20.0")));
    account.withdraw(anAmountOf(new BigDecimal("10.0")));
    assertThat(account).isEqualTo(anAccountWith(anAmountOf(new BigDecimal("10.0"))));
  }

  @Test
  void transfer_money_from_one_account_to_another() {
    UnboundedAccount destinationAccount = anEmptyAccount();
    UnboundedAccount sourceAccount = anAccountWith(anAmountOf(new BigDecimal("50.0")));

    sourceAccount.transfer(anAmountOf(new BigDecimal("20.0")), destinationAccount);

    assertThat(sourceAccount).isEqualTo(anAccountWith(anAmountOf(new BigDecimal("30.0"))));
    assertThat(destinationAccount).isEqualTo(anAccountWith(anAmountOf(new BigDecimal("20.0"))));
  }

  @Test
  void transfer_destination_account_should_not_be_source_account() {
    UnboundedAccount sourceAccount = anAccountWith(anAmountOf(new BigDecimal("50.0")));

    BankingException exception = assertThrows(BankingException.class, () -> sourceAccount.transfer(anAmountOf(new BigDecimal("20.0")), sourceAccount));
    assertThat(exception.hasType(TRANSFER)).isTrue();

  }

}