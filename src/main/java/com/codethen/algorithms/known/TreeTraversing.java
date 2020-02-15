package com.codethen.algorithms.known;

import java.util.*;

import static com.codethen.algorithms.known.Node.node;
import static com.codethen.algorithms.known.Node.nullNode;

public class TreeTraversing {

    public static void main(String... args) {

        final Node<Integer> tree =
            node(1,
                node(2,
                    node(4),
                    node(5,
                        nullNode(),
                        node(8))),
                node(3,
                    node(6),
                    node(7)));

        System.out.println(depthFirstSearch_rec(tree, new ArrayList<>()));
        System.out.println(depthFirstSearch_iter(tree, new ArrayList<>()));
        System.out.println(depthFirstSearch_iter_v2(tree, new ArrayList<>()));
        System.out.println(breathFirstSearch_iter(tree, new ArrayList<>()));
        // Breath First Search recursive??
        // I guess it would be a bit forced since BFS is typically implemented with a queue, and
        // a recursive algorithm is more related to stacks..
    }

    private static <T> List<T> depthFirstSearch_rec(Node<T> node, List<T> output) {
        if (node == Node.nullNode) return output;
        output.add(node.value);
        depthFirstSearch_rec(node.left, output);
        depthFirstSearch_rec(node.right, output);
        return output;
    }

    private static <T> List<T> depthFirstSearch_iter(Node<T> tree, List<T> output) {

        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(tree);

        while (!stack.isEmpty()) {
            final Node<T> node = stack.pop();
            if (node == Node.nullNode) continue;
            output.add(node.value);
            /**
             * DFS is usually recursive, so here we "simulate" recursion using a stack.
             * We push the right child first so we actually process (pop) the left node first.
             * Actually, we push the left child just to immediately pop it, so in
             * {@link #depthFirstSearch_iter_v2(Node, List)} we optimise that.
             */
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return output;
    }

    /**
     * Alternative implementation where we only push the right children
     * because we can process the left children right away.
     *
     * This is actually similar to the recursive implementation, since there the call stack
     * is used to "push the right children" too or, more precisely, to push the calls that
     * process the left children, just to remember to process the right child in reverse order.
     */
    private static <T> List<T> depthFirstSearch_iter_v2(Node<T> tree, List<T> output) {

        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(tree);

        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            // Process the node and the left child "recursively",
            // while pushing the right child when necessary
            while (node != Node.nullNode) {
                output.add(node.value);
                if (node.right != null) stack.push(node.right);
                node = node.left;
            }
        }

        return output;
    }

    private static <T> List<List<T>> breathFirstSearch_iter(Node<T> tree, List<List<T>> output) {

        Queue<Node<T>> remaining = new ArrayDeque<>();
        remaining.add(tree);

        while (!remaining.isEmpty()) {

            final Queue<Node<T>> children = new ArrayDeque<>();
            List<T> levelOutput = new ArrayList<>();

            while (!remaining.isEmpty()) {
                final Node<T> node = remaining.remove();
                if (node != Node.nullNode) {
                    levelOutput.add(node.value);
                    children.add(node.left);
                    children.add(node.right);
                } else {
                    // levelOutput.add(node.value); // add values for null nodes
                }
            }

            remaining = children;

            // We process null nodes, but maybe we don't add values for them, so last level may be empty
            if (!levelOutput.isEmpty()) {
                output.add(levelOutput);
            }
        }

        return output;
    }
}

class Node<T> {

    public static <U> Node<U> node(U value) {
        return new Node<>(value, nullNode(), nullNode());
    }

    public static Node<?> nullNode = new Node<>(null);
    public static <U> Node<U> nullNode() {
        return (Node<U>) nullNode;
    }

    public static <U> Node<U> node(U value, Node<U> left, Node<U> right) {
        return new Node<>(value, left, right);
    }

    public Node(T value) {
        this(value, null, null);
    }

    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    T value;
    Node<T> left;
    Node<T> right;
}