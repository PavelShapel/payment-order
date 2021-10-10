package com.pavelshapel.multi.threading.task.extra.first;

import java.math.BigInteger;

public class Factorial {
    protected BigInteger factorial(BigInteger number) {
        if (number.compareTo(BigInteger.ONE) <= 0) {
            return BigInteger.ONE;
        }
        return number.multiply(new Factorial().factorial(number.subtract(BigInteger.ONE)));
    }
}