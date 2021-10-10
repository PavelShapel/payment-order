package com.pavelshapel.multi.threading.task.extra.first;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinFactorial {
    public static final BigInteger THRESHOLD = BigInteger.valueOf(5);

    private static class FactorialTask extends RecursiveTask<BigInteger> {
        private final BigInteger start;
        private final BigInteger end;

        private FactorialTask(BigInteger number) {
            this(BigInteger.ONE, number);
        }

        private FactorialTask(BigInteger start, BigInteger end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected BigInteger compute() {
            BigInteger length = end.subtract(start).add(BigInteger.ONE);
            if (length.compareTo(THRESHOLD) <= 0) {
                return factorial();
            }

            BigInteger mid = length.divide(BigInteger.valueOf(2));
            FactorialTask firstTask = new FactorialTask(start, start.add(mid));
            FactorialTask secondTask = new FactorialTask(start.add(mid).add(BigInteger.ONE), end);
            firstTask.fork();
            return secondTask.compute().multiply(firstTask.join());

        }

        private BigInteger factorial() {
            BigInteger result = BigInteger.ONE;
            for (long i = start.longValue(); i <= end.longValue(); i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        }
    }

    public BigInteger factorial(BigInteger number) {
        ForkJoinTask<BigInteger> task = new FactorialTask(number);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        return pool.invoke(task);
    }
}