package aoc.day04

import Util.Companion.readInput
import hasOverlapWith
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day04Test {

    val input = readInput("/day04/day04.txt")
    val testInput = readInput("/day04/day04-test.txt")

    val testStr = """
        2-4,6-8
        2-3,4-5
        5-7,7-9
        2-8,3-7
        6-6,4-6
        2-6,4-8
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(2, part1(testStr))
    }

    @Test
    fun part1() {
        println(part1(input))
    }

    @Test
    fun testRange() {
        assertFalse((2..4).hasOverlapWith(5..7))
        assertTrue((2..4).hasOverlapWith(4..7))
        assertTrue((2..4).hasOverlapWith(1..2))
    }

    @Test
    fun part2Test() {
        assertEquals(4, part2(testStr))
    }

    @Test
    fun part2() {
        println(part2(input))
    }
}

