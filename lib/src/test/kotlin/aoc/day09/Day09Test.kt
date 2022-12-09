package aoc.day09

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import Coord
import Util.Companion.readInput


val testStr = """
    R 4
    U 4
    L 3
    D 1
    R 4
    D 1
    L 5
    R 2
""".trimIndent()

val testStr2 = """
    R 5
    U 8
    L 8
    D 3
    R 17
    D 10
    L 25
    U 20
""".trimIndent()

val input = readInput("/day09/day09.txt")
class Day09Test {
    @Test
    fun part1Test() {
        assertEquals(13,  simulateRope(testStr))
    }

    @Test
    fun part1() {
        simulateRope(input)
    }

    @Test
    fun part2Test() {
        assertEquals(36, simulateRope(testStr2, ropeLength = 10))
    }

    @Test
    fun part2() {
        simulateRope(input, ropeLength = 10)
    }
}

