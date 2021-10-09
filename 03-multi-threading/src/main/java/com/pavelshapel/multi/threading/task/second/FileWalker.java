package com.pavelshapel.multi.threading.task.second;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

import static com.pavelshapel.multi.threading.task.second.SecondApplicationRunner.format;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileWalker extends RecursiveTask<List<Object>> {
    String path;
    ForkJoinPool pool;

    @SneakyThrows
    @Override
    protected List<Object> compute() {
        List<Object> result = new ArrayList<>();
        List<FileWalker> tasks = new ArrayList<>();
        File file = new File(path);
        Optional.ofNullable(file.listFiles()).ifPresent(files -> IntStream.range(0, files.length)
                .forEach(index -> {
                    if (files[index].isDirectory()) {
                        forkSubTask(tasks, files[index]);
                        result.add(files[index].getAbsolutePath());
                    } else {
                        result.add(files[index].length());
                    }
                }));
        tasks.forEach(fileWalker -> joinTask(result, fileWalker));
        return result;
    }

    private void forkSubTask(List<FileWalker> tasks, File path) {
        FileWalker task = new FileWalker(path.getAbsolutePath(), pool);
        task.fork();
        tasks.add(task);
    }

    private void joinTask(List<Object> fileSizes, FileWalker task) {
        List<Object> taskResult = task.join();
        long amountFilesSize = taskResult.stream()
                .filter(Long.class::isInstance)
                .mapToLong(Long.class::cast)
                .sum();
        System.out.printf("thread count[%s] task count[%s] files count[%s] amount files size[%s]\r",
                format(pool.getActiveThreadCount()),
                format(pool.getQueuedTaskCount()),
                format(taskResult.size()),
                format(amountFilesSize));
        fileSizes.addAll(taskResult);
    }
}