package com.pavelshapel.multi.threading.task.first;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class QuickSort extends RecursiveAction {
    int start;
    int end;
    int[] array;

    public QuickSort(int start, int end, int[] array) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    private int sort(int start, int end, int[] arr) {
        int i = start;
        int j = end;
        int random = ThreadLocalRandom.current().nextInt(j - i) + i;

        int temp = arr[j];
        arr[j] = arr[random];
        arr[random] = temp;
        j--;

        while (i <= j) {
            if (arr[i] <= arr[end]) {
                i++;
                continue;
            }
            if (arr[j] >= arr[end]) {
                j--;
                continue;
            }
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            j--;
            i++;
        }

        temp = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = temp;
        return j + 1;
    }

    @Override
    protected void compute() {
        if (start >= end)
            return;
        int baseElement = sort(start, end, array);
        QuickSort first = new QuickSort(start, baseElement - 1, array);
        QuickSort second = new QuickSort(baseElement + 1, end, array);
        first.fork();
        second.compute();
        first.join();
    }
}