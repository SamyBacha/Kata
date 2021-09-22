package org.sba.kata.banking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.sba.kata.banking.Money.anAmountOf;
import static org.sba.kata.banking.BoundedAccount.anAccountWith;
import static org.sba.kata.banking.BoundedAccount.anEmptyAccount;
import static org.sba.kata.banking.exception.BankingException.OperationType.DEPOSIT;
import static org.sba.kata.banking.exception.BankingException.OperationType.TRANSFER;
import static org.sba.kata.banking.exception.BankingException.OperationType.WITHDRAW;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.sba.kata.banking.exception.BankingException;

class BoundedAccountTest {

  @Test
  void deposit_an_amount_should_increase_the_balance() {
    BoundedAccount account = anEmptyAccount();
    account.deposit(anAmountOf(new BigDecimal("10.0")));
    assertThat(account).isEqualTo(anAccountWith(anAmountOf(new BigDecimal("10.0"))));
  }

  @Test
  void deposit_an_amount_should_be_positive() {
    UnboundedAccount account = UnboundedAccount.anEmptyAccount();

    BankingException exception = assertThrows(BankingException.class, () -> account.deposit(anAmountOf(new BigDecimal("-10.0"))));
    assertThat(exception.hasType(DEPOSIT)).isTrue();
  }

  @Test
  void withdraw_an_amount_should_decrease_the_balance() {
    BoundedAccount account = anAccountWith(anAmountOf(new BigDecimal("20.0")));
    account.withdraw(anAmountOf(new BigDecimal("10.0")));
    assertThat(account).isEqualTo(anAccountWith(anAmountOf(new BigDecimal("10.0"))));
  }

  @Test
  void withdraw_an_amount_gt_the_balance_shouldnt_work() {
    BoundedAccount account = anAccountWith(anAmountOf(new BigDecimal("20.0")));
    BankingException exception = assertThrows(BankingException.class, () -> account.withdraw(anAmountOf(new BigDecimal("30.0"))));
    assertThat(exception.hasType(WITHDRAW)).isTrue();
  }

  @Test
  void transfer_money_from_one_account_to_another_shouldnt_pass_if_not_enought_money() {
    BoundedAccount destinationAccount = anEmptyAccount();
    BoundedAccount sourceAccount = anAccountWith(anAmountOf(new BigDecimal("20.0")));


    BankingException exception = assertThrows(BankingException.class, () -> sourceAccount.transfer(anAmountOf(new BigDecimal("50.0")), destinationAccount));
    assertThat(exception.hasType(WITHDRAW)).isTrue();
    assertThat(sourceAccount).isEqualTo(anAccountWith(anAmountOf(new BigDecimal("20.0"))));
    assertThat(destinationAccount).isEqualTo(anEmptyAccount());
  }

  @Test
  void transfer_money_from_one_account_to_another() {
    BoundedAccount destinationAccount = anEmptyAccount();
    BoundedAccount sourceAccount = anAccountWith(anAmountOf(new BigDecimal("50.0")));

    sourceAccount.transfer(anAmountOf(new BigDecimal("20.0")), destinationAccount);

    assertThat(sourceAccount).isEqualTo(anAccountWith(anAmountOf(new BigDecimal("30.0"))));
    assertThat(destinationAccount).isEqualTo(anAccountWith(anAmountOf(new BigDecimal("20.0"))));
  }

  @Test
  void transfer_destination_account_should_not_be_source_account() {
    BoundedAccount sourceAccount = anAccountWith(anAmountOf(new BigDecimal("50.0")));

    assertThrows(BankingException.class, () -> sourceAccount.transfer(anAmountOf(new BigDecimal("20.0")), sourceAccount));

  }

}