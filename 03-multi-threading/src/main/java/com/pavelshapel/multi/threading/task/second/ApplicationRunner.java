package com.pavelshapel.multi.threading.task.second;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class ApplicationRunner {
    public static final String WINDOWS_PATH = "C:/Windows";
    public static final String PATTERN = "###,###.###";

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        Terminator terminator = new Terminator(pool);
        FileWalker fileWalker = new FileWalker(WINDOWS_PATH, pool);
        pool.execute(terminator);
        pool.execute(fileWalker);
        pool.shutdown();
        List<Object> result = fileWalker.join();
        List<Long> filesCount = result.stream()
                .filter(Long.class::isInstance)
                .map(Long.class::cast)
                .collect(Collectors.toList());
        long amountFilesSize = filesCount.stream()
                .mapToLong(Long.class::cast)
                .sum();
        List<String> directoriesCount = result.stream()
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .collect(Collectors.toList());
        System.out.printf("directories count: %s", format(directoriesCount.size()));
        System.out.printf("%nfiles count: %s", format(filesCount.size()));
        System.out.printf("%namount files size: %s", format(amountFilesSize));
    }

    public static String format(long value) {
        DecimalFormat formatter = new DecimalFormat(PATTERN);
        return formatter.format(value);
    }
}