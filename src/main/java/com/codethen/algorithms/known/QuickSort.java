package com.codethen.algorithms.known;

import com.codethen.useful.UsefulJava;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static com.codethen.algorithms.known.Util.swap;

public class QuickSort {

    public static void main(String... args) {

        UsefulJava.ensureAssertionsEnabled();

        int[] array = {1, 1, 2, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1};
        int[] arrayCopy = Arrays.copyOf(array, array.length);

        quickSort(array);
        Arrays.sort(arrayCopy);

        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(arrayCopy));

        assert Arrays.equals(array, arrayCopy);
    }

    private static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int start, int end) {

        if (start >= end) return;

        final int pivotIndex = ThreadLocalRandom.current().nextInt(start, end+1);
        final int pivot = array[pivotIndex];
        swap(array, pivotIndex, end);

        int swapIndex = 0;

        for (int i = 0; i < end; i++) {
            if (array[i] < pivot) {
                swap(array, swapIndex, i);
                swapIndex++;
            }
        }

        swap(array, swapIndex, end);

        /** Skip repeated pivot numbers. See {@link Quick} for a better solution. */
        final int endLeft = skipRepeatedLeft(array, swapIndex, start);
        final int startRight = skipRepeatedRight(array, swapIndex, end);

        quickSort(array, start, endLeft);
        quickSort(array, startRight, end);
    }

    /** Skips numbers equal to array[i] to the left */
    private static int skipRepeatedLeft(int[] array, int i, int limit) {

        int key = array[i];

        for (int j = i-1; j > limit; j--) {
            if (array[j] != key) {
                return j;
            }
        }

        return limit;
    }

    /** Skips numbers equal to array[i] to the right */
    private static int skipRepeatedRight(int[] array, int i, int limit) {

        int key = array[i];

        for (int j = i+1; j < limit; j++) {
            if (array[j] != key) {
                return j;
            }
        }

        return limit;
    }
}
