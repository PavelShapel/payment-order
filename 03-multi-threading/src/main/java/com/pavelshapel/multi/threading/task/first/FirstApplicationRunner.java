package com.pavelshapel.multi.threading.task.first;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FirstApplicationRunner {
    public static final int BOUND = 50;
    public static final String BEFORE = "before";
    public static final String AFTER = "after";

    public static void main(String[] args) {
        int[] array = createArray();
        logBeforeSort(array);
        sortArray(array);
        logAfterSort(array);
    }

    private static void sortArray(int[] array) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        QuickSort task = new QuickSort(0, array.length - 1, array);
        pool.invoke(task);
    }

    private static int[] createArray() {
        return Stream.generate(() -> ThreadLocalRandom.current().nextInt(BOUND))
                .limit(BOUND)
                .distinct()
                .mapToInt(value -> value)
                .toArray();
    }

    private static void logBeforeSort(int[] array) {
        log(array, BEFORE);
    }

    private static void logAfterSort(int[] array) {
        log(array, AFTER);
    }

    private static void log(int[] array, String prefix) {
        String arrayAsString = Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.printf("%s sort: %s%n", prefix, arrayAsString);
    }
}
