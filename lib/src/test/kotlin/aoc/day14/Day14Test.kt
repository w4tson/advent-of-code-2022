package aoc.day14

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day14Test {

    val input = readInput("/day14/day14.txt")
    val testInput = readInput("/day14/day14-test.txt")

    val testStr = """
        498,4 -> 498,6 -> 496,6
        503,4 -> 502,4 -> 502,9 -> 494,9
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(24, toCave(testStr).simulate())
    }

    @Test
    fun part1() {
        toCave(input).simulate()
    }
}

