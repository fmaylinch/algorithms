package com.codethen.algorithms

fun main() {

    val list = mutableListOf(1, 2, 3, 4)
    val result = mutableListOf<List<Int>>()
    permutations(list, 0, result)
    result.forEach { println(it) }
    println(result.toSet().size)
}

fun <T> MutableList<T>.swap(i:Int, j:Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

fun permutations(list: MutableList<Int>, offset: Int, result: MutableList<List<Int>>) {

    if (offset == list.size - 1) {
        result.add(list.toList())
        return
    }

    for (i in offset until list.size) {
        list.swap(i, offset)
        permutations(list, offset+1, result)
        list.swap(i, offset)
    }
}
