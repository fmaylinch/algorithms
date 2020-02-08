package com.codethen.algorithms.exercises;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindrome {

    public static void main(String... args) {

        System.out.println( longestPalindrome("z234a5abbba54a32z") );
    }

    private static String longestPalindrome(String s) {

        if (s.isEmpty()) return "";

        int[] longest = {0, 1};

        for (int i = 0; i < s.length(); i++) {
            int[] even = findPalindrome(s, i, i+1);
            int[] odd = findPalindrome(s, i-1, i+1);
            if ((even[1] - even[0]) > (longest[1] - longest[0])) longest = even;
            if ((odd[1] - odd[0]) > (longest[1] - longest[0])) longest = odd;
        }

        return s.substring(longest[0], longest[1]);
    }

    private static int[] findPalindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return new int[]{i+1, j};
    }
}
