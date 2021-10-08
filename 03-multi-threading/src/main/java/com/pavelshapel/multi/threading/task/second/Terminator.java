package com.pavelshapel.multi.threading.task.second;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Terminator extends RecursiveAction {
    ForkJoinPool pool;

    @Override
    protected void compute() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("press enter for exit");
            scanner.nextLine();
            System.out.println("exit");
            System.exit(0);
        }
    }
}
