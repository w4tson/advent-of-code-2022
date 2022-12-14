/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package aoc.day01

import Util.Companion.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    val testInput = readInput("/day01/test.txt")
    val input = readInput("/day01/day01.txt")

    @Test
    fun test() {
        assertEquals(24000, calcMostCalories(testInput))
    }

    @Test
    fun part1() {
        println(calcMostCalories(input))
    }

    @org.junit.jupiter.api.Test
    fun test2() {
        assertEquals(45000, top3(testInput))
    }
    @Test
    fun part2() {
        println(top3(input))
    }
}
