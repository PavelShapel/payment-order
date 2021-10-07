package com.pavelshapel.multi.threading.task.first;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {
    public static final int BOUND = 50;
    public static final String BEFORE = "before";
    public static final String AFTER = "after";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        int[] array = createArray();
        logBefore(array);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        QuickSort task = new QuickSort(0, array.length - 1, array);
        pool.invoke(task);
        logAfter(array);
    }

    private int[] createArray() {
        return Stream.generate(() -> ThreadLocalRandom.current().nextInt(BOUND))
                .limit(BOUND)
                .distinct()
                .mapToInt(value -> value)
                .toArray();
    }

    private void logBefore(int[] array) {
        log(array, BEFORE);
    }

    private void logAfter(int[] array) {
        log(array, AFTER);
    }

    private void log(int[] array, String prefix) {
        String arrayAsString = Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        log.info("{} sort: {}", prefix, arrayAsString);
    }
}
