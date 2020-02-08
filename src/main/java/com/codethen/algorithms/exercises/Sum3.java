package com.codethen.algorithms.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum3 {

    public static void main(String... args) {

        int[] nums = {2, 2, -4, 2, 1, 2, -3, 2};
        System.out.println( threeSumSorted(nums) );
    }

    private static List<List<Integer>> threeSumSorted(int[] ns) {

        Arrays.sort(ns);

        final List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < ns.length; i++) {
            if (i > 0 && ns[i] == ns[i-1]) continue;

            int j = i + 1;
            int k = ns.length - 1;

            while (j < k) {

                if (ns[i] + ns[j] + ns[k] > 0) {
                    k--;
                } else if (ns[i] + ns[j] + ns[k] < 0) {
                    j++;
                } else {
                    result.add(Arrays.asList(ns[i], ns[j], ns[k]));
                    while (j < k && ns[k] == ns[k-1]) k--;
                    while (j < k && ns[j] == ns[j+1]) j++;
                    k--;
                    j++;
                }
            }
        }

        return result;
    }
}
