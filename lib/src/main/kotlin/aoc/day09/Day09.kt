package aoc.day09

import Coord
import Coord.Companion.origin
data class State(val h : Coord, val t : Coord, val visited : Set<Coord>)
fun toMoves(input: String) : List<Char> = input.lines().flatMap {
    val (l, num) = it.split(" ")
    (0 until num.toInt()).map { l.toCharArray().first()!! }
}
fun part1(input: String): Int = toMoves(input)
    .fold(State(origin, origin, setOf(origin))) { acc, move ->
        val newHead = acc.h.moveByChar(move)
        val newTail = if (newHead.isOneSquareAway(acc.t)) {
            acc.t
        } else {
            newHead.coordBehind(move)
        }
        State(newHead, newTail, acc.visited + newTail)//.also { println("$newHead, $newTail") }
    }.visited.size.also { println(it) }