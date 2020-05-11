package com.melalex.ajax.prime;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConcurrentPrimeCalculatorContract extends PrimeCalculatorContract {

  public static final int THRESHOLD = 100;

  private ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

  @Override
  public List<Integer> returnAllPrimeNumbers(int limit) {
    return null;
  }

  private static class FindPrimeTask extends RecursiveTask<List<Integer>> {

    private final int from;
    private final int to;

    private FindPrimeTask(int from, int to) {
      this.from = from;
      this.to = to;
    }

    @Override
    protected List<Integer> compute() {
      if (to - from > THRESHOLD) {
//        return ForkJoinTask.invokeAll(createSubtasks());
        return List.of();
      } else {
        return computeSeq();
      }
    }

    private ForkJoinTask<?> createSubtasks() {
      return null;
    }

    private List<Integer> computeSeq() {
      return IntStream.range(from, to)
          .filter(PrimeCalculatorContract::isPrime)
          .boxed()
          .collect(Collectors.toList());
    }
  }

  private List<Integer> returnAllPrimeNumbers(int from, int to) {

    return null;
  }
}
