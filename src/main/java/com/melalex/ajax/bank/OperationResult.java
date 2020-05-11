package com.melalex.ajax.bank;

public interface OperationResult {

  /* Returns true if the operation was successful, otherwise false */
  boolean isSuccessful();

  /* Returns the amount of money that was given for operation. Amount is always in cents. If you are putting $15 on your account then operationAmount() will return 1500*/
  int operationAmount();

  /* Returns the amount of money that remains on bank account. If you have $5 on your account and you are putting $10, that means that after the operation you will have $15 on your account. */
  int balanceAfterOperation();
}
