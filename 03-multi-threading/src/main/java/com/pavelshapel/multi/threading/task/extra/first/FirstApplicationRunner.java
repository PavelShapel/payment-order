package com.pavelshapel.multi.threading.task.extra.first;

import java.math.BigInteger;
import java.text.DecimalFormat;

public class FirstApplicationRunner {
    public static final BigInteger DEFAULT_VALUE = BigInteger.valueOf(20);
    public static final String PATTERN = "###,###.###";

    public static void main(String[] args) {
        long start = System.nanoTime();
        BigInteger forkJoinFactorial = new ForkJoinFactorial().factorial(DEFAULT_VALUE);
        long end = System.nanoTime();
        System.out.printf("FJP implementation duration [%s]; result [%s]%n", formatNumber(end - start), formatNumber(forkJoinFactorial.longValue()));

        start = System.nanoTime();
        BigInteger factorial = new Factorial().factorial(DEFAULT_VALUE);
        end = System.nanoTime();
        System.out.printf("sequential implementation duration [%s]; result [%s]%n", formatNumber(end - start), formatNumber(factorial.longValue()));
    }

    public static String formatNumber(long value) {
        DecimalFormat formatter = new DecimalFormat(PATTERN);
        return formatter.format(value);
    }
}
