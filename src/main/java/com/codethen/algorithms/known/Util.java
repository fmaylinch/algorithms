package com.codethen.algorithms.known;

public class Util {

    public static void swap(int[] array, int i, int j) {
        final int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T> void swap(T[] array, int i, int j) {
        final T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
