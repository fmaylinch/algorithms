package com.codethen.algorithms.exercises;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/
 */
public class ReverseWords {

    public static void main(String... args) {

        System.out.println("'" + new ReverseWords("a good   example").get() + "'");
    }

    private final StringBuilder builder;
    private final String s;
    private int idx;

    public ReverseWords(String s) {
        this.s = s;
        this.builder = new StringBuilder();
        this.idx = s.length();
    }

    public String get() {

        while (idx > 0) {

            skipSpaces();
            if (idx == 0) break;

            String word = findWord();
            if (word != null) {
                if (builder.length() > 0) {
                    builder.append(' ');
                }
                builder.append(word);
            }
        }

        return builder.toString();
    }

    private String findWord() {

        final int currentIdx = idx;
        while (idx > 0 && s.charAt(idx-1) != ' ') {
            idx--;
        }

        if (idx < currentIdx) {
            return s.substring(idx, currentIdx);
        } else {
            return null;
        }
    }

    private void skipSpaces() {
        while (idx > 0 && s.charAt(idx-1) == ' ') {
            idx--;
        }
    }
}

class Solution {

    public String reverseWords(String s) {

        return new ReverseWords(s).get();
    }
}
