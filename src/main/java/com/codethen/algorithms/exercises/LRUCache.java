package com.codethen.algorithms.exercises;

import java.util.LinkedHashMap;

/**
 * https://leetcode.com/problems/lru-cache/submissions/
 */
public class LRUCache {

    private int capacity;
    private LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            cache.put(key, cache.remove(key)); // updates LRU
            return cache.get(key);
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cache.size() == capacity && !cache.containsKey(key)) {
            cache.remove(cache.keySet().iterator().next()); // evicts LRU
        } else {
            cache.remove(key); // updates LRU
        }
        cache.put(key, value);
    }
}
