package com.codethen.algorithms.known;

import com.codethen.useful.UsefulJava;

import java.util.Arrays;
import java.util.Collections;

public class MergeSort {

    public static void main(String... args) {

        UsefulJava.ensureAssertionsEnabled();

        int[] array = {5, 3, 234, 23, 1, -23, 34, 3, -99};
        int[] arrayCopy = Arrays.copyOf(array, array.length);

        mergeSort(array);
        Arrays.sort(arrayCopy);
        Collections.sort(Arrays.asList(1, 2, 3));

        System.out.println("My sort:     " + Arrays.toString(array));
        System.out.println("System sort: " + Arrays.toString(arrayCopy));

        assert Arrays.equals(array, arrayCopy);
    }

    private static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length-1, new int[array.length]);
    }

    /** Merges array in place, which has two parts (start..mid, mid+1..end) sorted independently */
    private static void mergeSort(int[] array, int start, int end, int[] aux) {

        int mid = (start + end) / 2;

        // 1 element to sort, so it's sorted
        if (start == end) return;

        // Trivial sort of 2 elements (optimisation)
        // Actually, "Switching to insertion sort for small subarrays (length 15 or less, say) will improve the running time of a typical mergesort implementation by 10 to 15 percent (see Exercise 2.2.23).
        if (start == end-1) {
            if (array[end] < array[start])
                swap(array, start, end);
            return;
        }

        // Sort left part and right part
        mergeSort(array, start, mid, aux);
        mergeSort(array, mid+1, end, aux);

        // Now merge the parts
        merge(array, start, end, mid, aux);
    }

    private static void merge(int[] array, int start, int end, int mid, int[] aux) {

        // Copy from start to end in aux
        for (int i = start; i <= end; i++) {
            aux[i] = array[i];
        }

        int i1 = start;   // index for left part of array
        int i2 = mid + 1; // index for left part of array

        // System.out.println("start=" + start + ", mid=" + mid + ", end=" + end + ", i1=" + i1 + ", i2=" + i2 + ", aux.length=" + aux.length);

        for (int i = start; i <= end; i++) {

            if (i1 > mid) {
                array[i] = aux[i2++];
            } else if (i2 > end || aux[i1] < aux[i2]) {
                array[i] = aux[i1++];
            } else {
                array[i] = aux[i2++];
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        System.out.println("Swapping " + array[i] + " and " + array[j]);
        final int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
