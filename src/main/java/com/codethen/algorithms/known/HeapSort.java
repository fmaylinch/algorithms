package com.codethen.algorithms.known;

import com.codethen.useful.UsefulJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HeapSort {

    public static void main(String... args) {

        UsefulJava.ensureAssertionsEnabled();

        Integer[] array = {5, 3, 234, 23, 50, 1, -23, 34, 3, -99};

        final Heap<Integer> minHeap = new Heap<>(Comparator.comparing(x->x));

        for (int x : array) {
            minHeap.push(x);
        }

        final List<Integer> listFromHeap = new ArrayList<>();
        while (minHeap.size() > 0) {
            listFromHeap.add(minHeap.pop());
        }

        Arrays.sort(array);
        assert Arrays.asList(array).equals(listFromHeap);
    }

}

class Heap<T> {

    private List<T> list = new ArrayList<>();
    private Comparator<T> comparator;

    public Heap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(T value) {
        list.add(value);
        siftUp();
    }

    public T pop() {
        if (list.size() == 1) {
            return list.remove(0);
        }
        final T first = list.set(0, list.remove(list.size()-1));
        siftDown();
        return first;
    }

    public T peek() {
        return list.get(0);
    }

    private void siftUp() {
        int i = list.size() - 1;
        while (true) {
            int iParent = (i - 1) / 2;
            if (i <= 0 || comparator.compare(list.get(iParent), list.get(i)) <= 0) {
                break;
            }
            swap(i, iParent);
            i = iParent;
        }
    }

    private void siftDown() {
        int i = 0;
        while (true) {
            int iLowest = i;
            int iChild = i*2 + 1;
            // check child 1
            if (iChild < list.size() && comparator.compare(list.get(iChild), list.get(iLowest)) < 0) {
                iLowest = iChild;
            }
            iChild++;
            // check child 2
            if (iChild < list.size() && comparator.compare(list.get(iChild), list.get(iLowest)) < 0) {
                iLowest = iChild;
            }
            // check that we found a lower child
            if (iLowest != i) {
                swap(i, iLowest);
                i = iLowest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        final T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
