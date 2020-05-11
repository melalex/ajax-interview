package com.melalex.ajax.bank.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class AtomicIntegerAccountTest {

  private com.melalex.ajax.bank.impl.AtomicIntegerBankAccount bankAccountContract;

  @Before
  public void setUp() throws Exception {
    bankAccountContract = new AtomicIntegerBankAccount();
  }

  @Test
  public void shouldPut() {
    bankAccountContract.put(1500);

    final var actual = bankAccountContract.balance();

    assertEquals(new SimpleOperationResult(true, 0, 1500), actual);
  }

  @Test
  public void shouldReturnSuccessResultWhenWithdrawWithEnoughMoney() {
    bankAccountContract.put(1500);

    final var actual = bankAccountContract.withdraw(1400);

    assertEquals(new SimpleOperationResult(true, 1400, 100), actual);
  }

  @Test
  public void shouldReturnUnSuccessResultWhenWithdrawWithoutEnoughMoney() {
    bankAccountContract.put(1500);

    final var actual = bankAccountContract.withdraw(2000);

    assertEquals(new SimpleOperationResult(false, 2000, 1500), actual);
  }

  @Test
  public void shouldWithdrawAndPutInParallel() throws InterruptedException {
    final var executorService = Executors.newFixedThreadPool(10);
    bankAccountContract.put(1500);

    final var tasks = List.of(putTask(), withdrawTask(), putTask(), withdrawTask(), putTask(), withdrawTask(), putTask(), withdrawTask(), putTask(), withdrawTask());

    executorService.invokeAll(tasks).forEach(join());

    assertEquals(new SimpleOperationResult(true, 0, 1500), bankAccountContract.balance());

    executorService.shutdown();
  }

  @Test
  public void shouldWithdrawInParallel() throws InterruptedException {
    final var executorService = Executors.newFixedThreadPool(10);
    bankAccountContract.put(450);

    final var tasks = List.of(withdrawTask(), withdrawTask(), withdrawTask(), withdrawTask(), withdrawTask());

    executorService.invokeAll(tasks).forEach(join());

    assertEquals(new SimpleOperationResult(true, 0, 50), bankAccountContract.balance());

    executorService.shutdown();
  }

  private Consumer<Future<Void>> join() {
    return f -> {
      try {
        f.get();
      } catch (InterruptedException | ExecutionException e) {
        throw new RuntimeException(e);
      }
    };
  }

  private Callable<Void> putTask() {
    return () -> {
      for (int i = 0; i < 10; i++) {
        bankAccountContract.put(100);
        Thread.sleep(500);
      }

      return null;
    };
  }

  private Callable<Void> withdrawTask() {
    return () -> {
      for (int i = 0; i < 10; i++) {
        bankAccountContract.withdraw(100);
        Thread.sleep(500);
      }

      return null;
    };
  }
}
