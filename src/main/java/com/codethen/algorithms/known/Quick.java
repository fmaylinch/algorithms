package com.codethen.algorithms.known;

import java.util.Arrays;

/** Solution in Algorithhms 4th Edition by Robert Sedgewick, Kevin Wayne */
public class Quick
{
    public static void main(String... args) {

        Integer[] array = {1, 1, 1, 1, 1, 1, 1, 1};
        sort(array);

        System.out.println(Arrays.toString(array));
    }

    public static <T extends Comparable<T>> void sort(T[] a)
    {
        // StdRandom.shuffle(a); // Eliminate dependence on input.
        sort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi)
    {
        if (hi <= lo) return;
        int j = partition(a, lo, hi); // Partition (see page 291).
        System.out.println("Partition at: " + j);
        sort(a, lo, j-1); // Sort left part a[lo .. j-1]
        sort(a, j+1, hi); // Sort right part a[j+1 .. hi].
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi + 1; // left and right scan indices
        T v = a[lo]; // partitioning item
        while (true) { // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            Util.swap(a, i, j);
        }
        Util.swap(a, lo, j);
        return j;
    }

    private static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }
}
