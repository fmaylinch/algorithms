package com.codethen.algorithms.known;

import com.codethen.useful.UsefulJava;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public static void main(String... args) {

        UsefulJava.ensureAssertionsEnabled();

        int[] array = {5, 3, 234, 23, 1, -23, 34, 3, -99};
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

        quickSort(array, start, swapIndex-1);
        quickSort(array, swapIndex+1, end);
    }

    private static void swap(int[] array, int i, int j) {
        final int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
