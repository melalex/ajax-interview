package com.melalex.ajax.prime;

import java.util.List;

public abstract class PrimeCalculatorContract {
  /* Calculates and returns all prime numbers from 1 to limit */
  public abstract List<Integer> returnAllPrimeNumbers(int limit);

  /* checks whether an int is prime or not. */
  public static boolean isPrime(int n) {
//check if n is a multiple of 2
    if (n % 2 == 0) return false;
//if not, then just check the odds
    for (int i = 3; i * i <= n; i += 2) {
      if (n % i == 0) return false;
    }

    return true;
  }
}
