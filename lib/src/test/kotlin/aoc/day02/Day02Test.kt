package aoc.day02

import Util.Companion.readInput
import org.junit.jupiter.api.Test

class Day02Test {
    val input = """
    A Y
    B X
    C Z
""".trimIndent()

    val asdf = readInput("/day02/day02.txt")


    @Test
    fun asdf() {
        val play2 = play2("A", "Y")
        println(play2)
    }

    @Test
    fun name() {
        println(playAll(input))
    }

    @Test
    fun part1() {
        println(playAll(asdf))
    }
}