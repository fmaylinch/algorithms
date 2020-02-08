package com.codethen.algorithms

fun main() {
    println( threeSumSorted(listOf(2, 2, -4, 2, 1, 2, -3)) )
}

fun threeSumSorted(nums: List<Int>) : List<List<Int>> {

    val ns = nums.sorted()
    val result = mutableListOf<List<Int>>()

    for (i in ns.indices) {
        if (i > 0 && ns[i] == ns[i-1]) continue

        var j = i + 1
        var k = ns.size - 1

        while (j < k) {

            when {
                ns[i] + ns[j] + ns[k] > 0 -> k--
                ns[i] + ns[j] + ns[k] < 0 -> j++
                else -> {
                    result.add(listOf(ns[i], ns[j], ns[k]))
                    while (j < k && ns[k] == ns[k-1]) k--
                    while (j < k && ns[j] == ns[j+1]) j++
                    k--
                    j++
                }
            }
        }
    }

    return result
}

