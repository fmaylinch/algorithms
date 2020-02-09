package com.codethen.algorithms.exercises

fun main() {
    groupAnagrams(listOf("eat","tea","tan","ate","nat","raz","bat","zar"))
}

fun <K,V> MutableMap<K,V>.ensureDefault(key: K) : V {
    val value = this.getValue(key)
    this[key] = value
    return value
}

fun groupAnagrams(words: List<String>) {

    val groups = mutableMapOf<List<Int>, MutableList<String>>().withDefault { ArrayList() }

    for (word in words) {
        val key = makeKey(word)
        // groups.compute(key) { _, list -> (list ?: mutableListOf()).apply { add(word) } }
        // groups[key] = groups.getValue(key).apply { add(word) }
        groups.ensureDefault(key).add(word) // By implementing the extension function
    }

    println(groups.values)
}

fun makeKey(word: String): List<Int> {

    val size = 'z' - 'a' + 1
    val counts = MutableList(size) { 0 }
    for (c in word) {
        counts[c - 'a'] += 1
    }

    return counts
}
