package aoc.day03

import splitIntoTwo
import toSetOfAscii

val lowers = (97..122).map { Pair(it, it-96) }
val uppers = (65..90).map { Pair(it, it-64+26) }
val priorities = (lowers + uppers).toMap()

fun sharedItems(input: String) : List<Int> {
    val (c1, c2) = input.splitIntoTwo()
    return c1.toSetOfAscii().intersect(c2.toSetOfAscii())
        .map { priorities.getOrDefault(it,0) }
}

fun part1(input : String) : Int = input.lines().map { sharedItems(it).sum() }.sum()
fun part2(input : String): Int = input.lines().chunked(3).map { findBadge(it) }.sum()
fun findBadge(group: List<String>) : Int{
    val (a,b,c) = group

    val badge = a.toSetOfAscii().intersect(b.toSetOfAscii())
        .intersect(c.toSetOfAscii())
        .toList()
        .first()

    return priorities.getOrDefault(badge,0)
}

