package com.melalex.ajax.bank.impl;

import com.melalex.ajax.bank.BankAccountContract;
import com.melalex.ajax.bank.OperationResult;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerBankAccount implements BankAccountContract {

  private final AtomicInteger balance = new AtomicInteger(0);

  @Override
  public OperationResult withdraw(int amount) {
    final var oldBalance = balance.getAndAccumulate(amount, this::tryWithdraw);
    final var couldWithdraw = oldBalance >= amount;
    final var newBalance = couldWithdraw ? oldBalance - amount : oldBalance;

    return new SimpleOperationResult(couldWithdraw, amount, newBalance);
  }

  @Override
  public OperationResult put(int amount) {
    final var newBalance = balance.addAndGet(amount);

    return new SimpleOperationResult(true, amount, newBalance);
  }

  @Override
  public OperationResult balance() {
    return new SimpleOperationResult(true, 0, balance.get());
  }

  private int tryWithdraw(int prev, int diff) {
    if (prev >= diff) {
      return prev - diff;
    } else {
      return prev;
    }
  }
}
