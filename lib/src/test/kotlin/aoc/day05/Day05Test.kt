package aoc.day05

import Util.Companion.readInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day05Test {

    val input = readInput("/day05/day05.txt")
    val testInput = readInput("/day05/day05-test.txt")

    val testStr = """
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals("CMZ",  Crane(testStr).move())
    }

    @Test
    fun part1() {
        println(Crane(input).move())
    }

    @Test
    fun part2test() {
        assertEquals("MCD",  Crane(testStr).move9001())
    }

    @Test
    fun part2() {
        println(Crane(input).move9001())
    }
}

