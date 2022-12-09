package aoc.day09

import Coord
import Coord.Companion.origin

data class State(val rope: List<Coord>, val visited : Set<Coord>)

fun toMoves(input: String) : List<Char> = input.lines().flatMap {
    val (l, num) = it.split(" ")
    (0 until num.toInt()).map { l.toCharArray().first()!! }
}

fun simulateRope(input: String, ropeLength: Int = 2): Int {
    val rope = (0 until ropeLength).map { origin }
    val initial = State(rope, setOf(origin))

    return toMoves(input)
        .fold(initial) { state, move ->
            val newHead = state.rope.first().moveByChar(move)

            val newRope = state.rope
                .drop(1)
                .fold(listOf(newHead)) { acc, tail -> acc + tail.follow(acc.last()) }

            State( newRope, state.visited + newRope.last())
        }.visited.size.also { println(it) }
}