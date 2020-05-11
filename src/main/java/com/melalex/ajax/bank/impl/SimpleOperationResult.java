package com.melalex.ajax.bank.impl;

import com.melalex.ajax.bank.OperationResult;

import java.util.Objects;

public final class SimpleOperationResult implements OperationResult {

  private final boolean successful;
  private final int operationAmount;
  private final int balanceAfterOperation;

  public SimpleOperationResult(boolean successful, int operationAmount, int balanceAfterOperation) {
    this.successful = successful;
    this.operationAmount = operationAmount;
    this.balanceAfterOperation = balanceAfterOperation;
  }

  @Override
  public boolean isSuccessful() {
    return successful;
  }

  @Override
  public int operationAmount() {
    return operationAmount;
  }

  @Override
  public int balanceAfterOperation() {
    return balanceAfterOperation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SimpleOperationResult that = (SimpleOperationResult) o;
    return successful == that.successful &&
        operationAmount == that.operationAmount &&
        balanceAfterOperation == that.balanceAfterOperation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(successful, operationAmount, balanceAfterOperation);
  }

  @Override
  public String toString() {
    return "SimpleOperationResult{" +
        "successful=" + successful +
        ", operationAmount=" + operationAmount +
        ", balanceAfterOperation=" + balanceAfterOperation +
        '}';
  }
}
