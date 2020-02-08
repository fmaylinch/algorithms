package com.codethen.algorithms.exercises;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {

    public static void main(String... args) {

        System.out.println(new ValidParentheses().isValid("()"));
    }

    public boolean isValid(String s) {

        Deque<Integer> stack = new ArrayDeque<>();

        return s.chars().allMatch(c -> {

            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    return true;
                case ')':
                    return checkPop(stack, '(');
                case ']':
                    return checkPop(stack, '[');
                case '}':
                    return checkPop(stack, '{');
                default:
                    return false;
            }
        });
    }

    private boolean checkPop(Deque<Integer> stack, int c) {
        System.out.println(stack.peek() + " " + c);
        return !stack.isEmpty() && stack.pop() == c;
    }
}
