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

    private int sort(int start, int end, int[] array) {
        int i = start;
        int j = end;
        int random = ThreadLocalRandom.current().nextInt(j - i) + i;

        int temp = array[j];
        array[j] = array[random];
        array[random] = temp;
        j--;

        while (i <= j) {
            if (array[i] <= array[end]) {
                i++;
                continue;
            }
            if (array[j] >= array[end]) {
                j--;
                continue;
            }
            temp = array[j];
            array[j] = array[i];
            array[i] = temp;
            j--;
            i++;
        }

        temp = array[j + 1];
        array[j + 1] = array[end];
        array[end] = temp;
        return j + 1;
    }

    @Override
    protected void compute() {
        if (start >= end) {
            return;
        }
        int baseElement = sort(start, end, array);
        QuickSort first = new QuickSort(start, baseElement - 1, array);
        QuickSort second = new QuickSort(baseElement + 1, end, array);
        first.fork();
        second.compute();
        first.join();
    }
}