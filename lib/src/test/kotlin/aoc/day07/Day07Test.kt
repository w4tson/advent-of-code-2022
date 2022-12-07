package aoc.day07

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day07Test {

    val input = readInput("/day07/day07.txt")
    val testInput = readInput("/day07/day07-test.txt")
    val testInput2 = readInput("/day07/day07-test2.txt")

    val testStr = """
        ...
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(95437, part1(testInput))
    }

    @Test
    fun test2() {
        assertEquals(95438, part1(testInput2))
    }

    @Test
    fun part1() {
        part1(input)
    }
}

