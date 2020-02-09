package com.codethen.useful

import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.function.Consumer
import java.util.function.Supplier

/**
 * Useful things to use when resolving algorithms.
 * Run with -ea in VM options, to enable assertions.
 */
fun main() {

    UsefulJava.ensureAssertionsEnabled()

    lists()
    stack_queue_heap()
    maps()
    classes()
    interfaces()
}

private fun lists() {

    // Creation
    val list = mutableListOf<Int>()
    val listFixed = listOf(10, 11, 12)
    val list2 = mutableListOf(10, 11, 12)

    // Basic usage
    list2.add(13)
    val x2 = list2[0]
    val size = list2.size

    // Loop
    for (x in list)
        println(x)
    for (i in 0 until list.size)
        println("$i ${list[i]}")
    for (i in list.indices)
        println("$i ${list[i]}")
    for ((i, x) in list.withIndex())
        println("$i $x")

    // Initialise
    val listZeros = MutableList(x2) { 0 } // [0, 0, ..., 0] `x` times
    val listInc = List(x2) { it } // [0, 1, 2, 3, ..., x-1]

    // filter, map
    val list3 = list2.filter { it % 2 == 0 }.map { "it = $it" }
    val listToMap = list2.map { it to (it % 2 == 0) }.toMap()

    // Sorting
    list.sort() // If it's Comparable
    list.sortWith(Comparator { a, b -> a - b })  // Or specify a comparator
    list.sortWith(Comparator.comparingInt { a -> a }) // Comparator helpers
}

private fun stack_queue_heap() {

    val stack: Deque<String> = ArrayDeque()
    //final Stack<String> stack = new Stack<>(); // Better use ArrayDeque
    stack.push("one")
    stack.push("two")
    stack.push("three")
    assert(stack.pop() == "three")
    assert(stack.pop() == "two")
    assert(stack.pop() == "one")

    val queue: Queue<String> = ArrayDeque()
    // final Deque<String> queue = new ArrayDeque<>(); // more methods, can be used as stack
    queue.add("one")
    queue.add("two")
    queue.add("three")
    assert(queue.remove() == "one")
    assert(queue.remove() == "two")
    assert(queue.remove() == "three")

    val heap: Queue<Int> = PriorityQueue()
    heap.add(3)
    heap.add(1)
    heap.add(2)
    assert(heap.remove() == 1)
    assert(heap.remove() == 2)
    assert(heap.remove() == 3)
}

private fun maps() {

    val map = mutableMapOf<String, Int>()
    val mapOrdered = linkedMapOf<String, Int>()
    val set = mutableSetOf<String>()
    val setSorted = sortedSetOf<String>()
    val map2 = mutableMapOf("one" to 1, "two" to 2)

    val x3 = map2["two"]
    map2["three"] = 3
    val size2 = map2.size

    val mapDefaultInt = mutableMapOf<String, Int>().withDefault { 0 }
    assert(mapDefaultInt["a"] == null) // returns null, not the default value
    assert(mapDefaultInt.getValue("a") == 0) // returns default value, doesn't create entry
    assert(mapDefaultInt.isEmpty()) // is empty because entry was not created

    val map3 = HashMap<String, Int>()
    for (i in 1..3)
        map3.compute("a") { _, v -> (v ?: 0) + 1 }
    assert(map3["a"] == 3)

    map3.merge("a", 10) { v0, v1 -> v0 + v1 }
    assert(map3["a"] == 13)

    map3.merge("b", 10, Int::plus) // { a, b -> a + b }
    assert(map3["b"] == 10)
}

open class BoxKotlin(var value: Int) {

    fun add(value: Int) {
        this.value += value
    }

    fun add(box: BoxKotlin) {
        this.value += box.value
    }

    operator fun plus(box: BoxKotlin): BoxKotlin {
        return BoxKotlin(this.value + box.value)
    }
}

fun classes() {

    val b = BoxKotlin(1)
    b.add(2)
    b.add(BoxKotlin(3))
    assert(b.value == 6)
    b.value = 1
    assert(b.value == 1)
    val b2 = BoxKotlin(2) + BoxKotlin(3)
    assert(b2.value == 5)
}

private fun interfaces() {

    // Implementing Java functional interfaces
    val greeter1 = Consumer { x: String -> println(x) } // can't say println(it), type of it is unknown
    val greeter2 = Consumer<String> { println("Hi $it") } // type of 'it' is known
    val dice = Supplier { ThreadLocalRandom.current().nextInt(1, 7) }

    val greeter3 = object : MyConsumer<String> {
        override fun accept(x: String) {
            println("Hi $x")
        }
    }

    // Strangely, with Kotlin interfaces you cannot use the short form
    val adder1 = object : MyFunctionalInterface {
        override fun call(x: Int): Int = x + 1
    }
    assert( adder1.call(2) == 3 )

    val adder2 = object : MyFunctionalInterface2 {
        override fun invoke(x: Int): Int = x + 1
    }
    assert( adder2(2) == 3 ) // Implicitly calls invoke

    // It's better to use a function
    val greeter4: MyConsumerFunction<String> = { println("Hi $it") }
}

interface MyFunctionalInterface {
    fun call(x:Int): Int
}

interface MyFunctionalInterface2 {
    operator fun invoke(x:Int): Int
}

@FunctionalInterface
interface MyConsumer<T> {
    fun accept(x:T)
}

typealias MyConsumerFunction<T> = (T) -> Unit
