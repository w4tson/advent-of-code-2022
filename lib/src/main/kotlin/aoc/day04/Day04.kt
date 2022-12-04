package aoc.day04

import fullyContains
import hasOverlapWith

typealias ElfPair = Pair<ClosedRange<Int>, ClosedRange<Int>>

fun part1(input : String): Int = toPairs(input).count { fullyContained(it) }
fun part2(input : String): Int = toPairs(input).count { it.first.hasOverlapWith(it.second) }

fun fullyContained(elfPair : ElfPair) : Boolean =
    elfPair.first.fullyContains(elfPair.second) || elfPair.second.fullyContains(elfPair.first)

private fun toPairs(line: String): List<ElfPair> = line.lines().map {
    val (r1, r2) = it.split(",")
    Pair(toRange(r1), toRange(r2))
}

fun toRange(s : String) : ClosedRange<Int> {
    val (start, end) = s.split("-")
    return start.toInt()..end.toInt()
}