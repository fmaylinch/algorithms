package com.codethen.algorithms.known;

import com.codethen.useful.UsefulJava;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String... args) {

        UsefulJava.ensureAssertionsEnabled();

        int[] array = {5, 3, 234, 23, 1, -23, 34, 3, -99};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        int[] numbers = {-200, -98, -99, 1, 3, 4, 5, 233, 234, 235, 300};

        for (int n : numbers) {
            // System.out.println("Looking for " + n);
            final int expected = Arrays.binarySearch(array, n);
            final int actual = myBinarySearch(array, n);
            if (actual != expected) {
                assert false : "Index of " + n + " should be " + expected + " but is " + actual;
            }
        }
    }

    private static int myBinarySearch(int[] array, int key) {
        return myBinarySearch(array, 0, array.length, key);
    }

    private static int myBinarySearch(int[] array, int from, int to, int key) {

        while (from < to) {
            // System.out.println("from = " + from + ", to = " + to);
            int mid = (to + from) / 2;
            if (array[mid] < key) {
                from = mid+1;
            } else if (array[mid] > key) {
                to = mid-1;
            } else {
                return mid;
            }
        }

        // System.out.println("from = " + from + ", to = " + to);

        // TODO: Can we simplify this?
        if (from < array.length && array[from] == key) {
            return from;
        } else {
            final boolean placeAfterFrom = from < array.length && array[from] < key;
            return -(from + (placeAfterFrom ? 1 : 0) + 1);
        }
    }
}
