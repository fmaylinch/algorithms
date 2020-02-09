package com.codethen.algorithms.exercises

fun main() {

    println( longestPalindrome("z234a5abbba54a32z") )
}

fun longestPalindrome(s: String): String {

    var longest : List<Int> = listOf(0, 1)

    for (i in s.indices) {

        val odd = findPalindrome(s, i-1, i+1)
        val even = findPalindrome(s, i, i+1)

        val comp = Comparator<List<Int>> {
            x, y -> (x[1]-x[0]) - (y[1]-y[0])
        }

        longest = maxOf(longest, odd, comp)
        longest = maxOf(longest, even, comp)

        println("odd: $odd, even: $even, longest: $longest")
    }

    return s.substring(longest[0], longest[1])
}

fun findPalindrome(s: String, i: Int, j: Int): List<Int> {

    var ii = i
    var jj = j

    while (ii >= 0 && jj < s.length && s[ii] == s[jj]) {
        ii--
        jj++
    }

    return listOf(ii+1, jj)
}
