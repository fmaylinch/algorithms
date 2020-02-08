package com.codethen.algorithms.exercises;

import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/
 */
public class GroupAnagrams {

    public static void main(String... args) {

        String[] strs = {"eat","tea","tan","ate","nat","bat"};

        System.out.println(new GroupAnagrams().groupAnagrams(strs));
    }

    public List<List<String>> groupAnagrams(String[] words) {

        Map<List<Integer>, List<String>> groups = new HashMap<>();

        for (String word : words) {

            List<Integer> key = makeKey(word);

            groups.compute(key, (c, list) -> {
                List<String> target = list != null ? list : new ArrayList<>();
                target.add(word);
                return target;
            });
        }

        return new ArrayList<>(groups.values());
    }

    private List<Integer> makeKey(String str) {

        int first = 'a';
        int last = 'z';

        int size = last - first + 1;

        List<Integer> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(0);
        }

        str.chars().forEach(c -> {
            final int index = c - first;
            final Integer value = result.get(index);
            result.set(index, value == null ? 1 : value + 1);
        });

        return result;
    }

    public List<List<String>> groupAnagramsSorting(String[] strs) {

        Map<String, List<String>> groups = new HashMap<>();

        for (String str : strs) {

            final char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.copyValueOf(chars);

            groups.compute(key, (c, list) -> {
                List<String> target = list != null ? list : new ArrayList<>();
                target.add(str);
                return target;
            });
        }

        System.out.println(groups);

        return new ArrayList<>(groups.values());
    }
}
