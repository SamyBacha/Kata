package org.sba.kata.banking.exception;

public class BankingException extends RuntimeException{

  private final OperationType operationType;

  public BankingException(OperationType operationType) {
    super(operationType + " fail");
    this.operationType = operationType;
  }

  public static void invalidOperation(OperationType operationType) {
    throw new BankingException(operationType);
  }

  public boolean hasType(OperationType operationType) {
    return this.operationType == operationType;
  }

  public enum OperationType {
    DEPOSIT,
    TRANSFER,
    WITHDRAW
  }

}
