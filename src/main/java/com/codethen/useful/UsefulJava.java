package com.codethen.useful;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Useful things to use when resolving algorithms.
 * Run with -ea in VM options, to enable assertions.
 */
public class UsefulJava {

    public static void main(String... args) {

        ensureAssertionsEnabled();

        lists();
        stack_queue_heap();
        maps();
        sets();
        classes();
    }

    private static void lists() {

        // Creation
        final List<Integer> list = new ArrayList<>();
        final List<Integer> listFixed1 = asList(10, 11, 12);
        final List<Integer> list2 = new ArrayList<>(asList(10, 11, 12));

        // Basic usage
        list2.add(13);
        int x2 = list2.get(0);
        int size = list2.size();

        // Loop
        for (Integer x : list)
            System.out.println(x);
        for (int i = 0; i < list.size(); i++)
            System.out.println(i + " " + list.get(i));

        // Initialise
        final List<Integer> listZeros1 = IntStream.of(new int[x2]).boxed().collect(toList());
        final List<Integer> listZeros2 = IntStream.range(0, x2).map(i -> 0).boxed().collect(toList());
        final List<Integer> listInc = IntStream.range(0, x2).boxed().collect(toList());

        // filter, map
        final List<String> list3 = list2.stream().filter(x -> x % 2 == 0).map(x -> "x = " + x).collect(toList());
        final Map<Integer, Boolean> listToMap = list2.stream().collect(toMap(x -> x, x -> x % 2 == 0));

        // Sorting
        Collections.sort(list); // If it's Comparable
        Collections.sort(list, (a,b) -> a-b); // Or specify a comparator
        list.sort((a,b) -> a-b); // Equivalent
        list.sort(Comparator.comparingInt(a -> a)); // Comparator helpers
    }

    private static void stack_queue_heap() {

        final Deque<String> stack = new ArrayDeque<>();
        //final Stack<String> stack = new Stack<>(); // Better use ArrayDeque
        stack.push("one");
        stack.push("two");
        stack.push("three");
        assert stack.pop().equals("three");
        assert stack.pop().equals("two");
        assert stack.pop().equals("one");

        final Queue<String> queue = new ArrayDeque<>();
        // final Deque<String> queue = new ArrayDeque<>(); // more methods, can be used as stack
        queue.add("one");
        queue.add("two");
        queue.add("three");
        assert queue.remove().equals("one");
        assert queue.remove().equals("two");
        assert queue.remove().equals("three");

        final Queue<Integer> heap = new PriorityQueue<>();
        heap.add(3);
        heap.add(1);
        heap.add(2);
        assert heap.remove().equals(1);
        assert heap.remove().equals(2);
        assert heap.remove().equals(3);
    }

    private static void maps() {

        // Creation
        final Map<String, Integer> map = new HashMap<>();
        final Map<String, Integer> mapOrdered1 = new LinkedHashMap<>();

        // Basic usage
        map.put("one", 1);
        int x = map.get("one");
        final int size = map.size();

        // Compute and merge
        final Map<String, Integer> map3 = new HashMap<>();
        for (int i = 1; i <= 3; i++)
            map3.compute("a", (k,v0) -> v0 == null ? 1 : v0+1); // v0 is the possible current value
        assert map3.get("a") == 3;
        map3.merge("a", 10, (v0,v) -> v0 + v); // v0 is the current value
        assert map3.get("a") == 13;
        map3.merge("b", 10, Integer::sum); // (a,b) -> a+b
        assert map3.get("b") == 10;
    }

    private static void sets() {

        final Set<Integer> set1 = new HashSet<>(asList(3, 2, 3, 1, 2, 3));
        final Set<Integer> set2 = new HashSet<>(asList(1, 2, 3));
        assert set1.equals(set2);

        final Set<Integer> setSorted = new TreeSet<>(asList(3, 2, 3, 1, 2, 3));
        assert new ArrayList<>(setSorted).equals(asList(1, 2, 3));
    }

    private static void classes() {

        final BoxJava b = new BoxJava(1);
        b.add(2);
        b.add(new BoxJava(3));
        assert b.getValue() == 6;
        b.setValue(1);
        assert b.getValue() == 1;
    }

    public static void ensureAssertionsEnabled() {
        try {
            assert false : "this must fail";
            throw new RuntimeException("Assertions are not enabled. Add -ea in VM options.");
        } catch (AssertionError e) {
            // expected
        }
    }
}

class BoxJava {

    private int value;

    public BoxJava(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void add(int value) {
        this.value += value;
    }

    public void add(BoxJava box) {
        add(box.value);
    }
}


