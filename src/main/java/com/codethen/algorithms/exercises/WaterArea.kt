package com.codethen.algorithms.exercises

import kotlin.math.max
import kotlin.math.min

fun main() {
    println(waterArea(listOf(0, 8, 0, 0, 5, 0, 0, 10, 0, 0, 1, 1, 0, 3)))
}

fun waterArea(heights: List<Int>):Int {

    val maxes = List(heights.size) { Array(2){0} }

    // detect max to the left
    var maxLeft = 0
    for (i in heights.indices) {
        maxLeft = max(maxLeft, heights[i])
        maxes[i][0] = maxLeft
    }

    // detect max to the right
    var maxRight = 0
    for (i in heights.indices.reversed()) {
        maxRight = max(maxRight, heights[i])
        maxes[i][1] = maxRight
    }

    // Now maxes[i] contains [maxLeft, MaxRight] for every index
    var total = 0
    for (i in heights.indices) {
        total += min(maxes[i][0], maxes[i][1]) - heights[i]
    }

    return total
}