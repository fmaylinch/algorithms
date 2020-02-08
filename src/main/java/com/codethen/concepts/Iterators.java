package com.codethen.concepts;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Iterators {

    public static void main(String... args) {

        final Items<Integer> integers = new Items<>(Arrays.asList(1, 2, 3));
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}

class Items<T> implements Iterable<T> {

    private List<T> items;

    public Items(List<T> items) {
        this.items = items;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<>() {

            int i = 0;

            @Override
            public boolean hasNext() {
                return i < items.size();
            }

            @Override
            public T next() {
                return items.get(i++);
            }
        };
    }
}