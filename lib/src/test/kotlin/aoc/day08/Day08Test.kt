package aoc.day08

import Util.Companion.readInput
import aoc.day08.Forest.Companion.toForest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day08Test {

    val input = readInput("/day08/day08.txt")
    val testInput = readInput("/day08/day08-test.txt")

    val testStr = """
            30373
            25512
            65332
            33549
            35390
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(21, toForest(testStr).visibleCount())
    }

    @Test
    fun part1() {
        toForest(input).visibleCount()
    }

    @Test
    fun part2Test() {
        assertEquals(4, toForest(testStr).scenicScore(2,1))
        assertEquals(8, toForest(testStr).scenicScore(2,3))
        assertEquals(8, toForest(testStr).bestScenicScore())
    }

    @Test
    fun part2() {
        toForest(input).bestScenicScore()
    }
}

