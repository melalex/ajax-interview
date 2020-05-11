package com.melalex.ajax.bank;

public interface BankAccountContract {

  /* Takes money from account. If you have $25 on your account and you are withdrawing $10, that means that after the operation you will have $15 on your account. You can’t withdraw more money than you have. Amount is always in cents.*/
  OperationResult withdraw(int amount);

  /* Puts money on account. If you have $10 on your account and you are putting $25, that means that after the operation you will have $35 on your account. Amount is always in cents.*/
  OperationResult put(int amount);

  /* Checks how much money is present on account. If you have $10 on your account this method should return OperationResult with $10.*/
  OperationResult balance();
}
