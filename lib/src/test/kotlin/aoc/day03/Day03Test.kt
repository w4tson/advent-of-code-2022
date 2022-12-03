package aoc.day03

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
val test = """
    vJrwpWtwJgWrhcsFMMfFFhFp
    jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
    PmmdzqPrVvPwwTWBwg
    wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
    ttgJtRGJQctTZtZT
    CrZsJsPPZsGzwwsLwLmpwMDw
""".trimIndent()

val input = readInput("/day03/day03.txt")

class Day03Test {
    @Test
    fun name() {
        assertEquals(157,  part1(test))
    }
    @Test
    fun part1() {
        println(part1(input))
    }
    @Test
    fun part2() {
        println(part2(input))
    }
}