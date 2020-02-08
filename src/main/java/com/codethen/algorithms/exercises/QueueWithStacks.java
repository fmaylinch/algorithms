package com.codethen.algorithms.exercises;

/**
 * https://leetcode.com/problems/implement-queue-using-stacks
 */
public class QueueWithStacks {
}

class MyQueue {

    MyStack output; // values from input reversed, so FIFO
    MyStack input; // values are first pushed here, so LIFO

    /** Initialize your data structure here. */
    public MyQueue() {
        output = new MyStack();
        input = new MyStack();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        input.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        reverseInputToOutput();
        return output.pop();
    }

    /** Get the front element. */
    public int peek() {
        reverseInputToOutput();
        return output.peek();
    }

    private void reverseInputToOutput() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return output.isEmpty() && input.isEmpty();
    }
}

class MyStack {

    Node top;

    void push(int x) {
        top = new Node(x, top);
    }

    int pop() {
        int x = top.x;
        top = top.next;
        return x;
    }

    int peek() {
        return top.x;
    }

    boolean isEmpty() {
        return top == null;
    }

    class Node {

        Node next;
        private int x;

        public Node(int x, Node next) {
            this.x = x;
            this.next = next;
        }
    }
}


