package aoc.day13

import Util.Companion.readInput
import chunkByEmptyLine
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class   Day13Test {

    val input = readInput("/day13/day13.txt")
    val testInput = readInput("/day13/day13-test.txt")

    @Test
    fun test1() {
        assertEquals(13,  part1(testInput))
    }

    @Test
    fun name() {
        toPacket("[[1],10,0,[0]").also { println(it) }
    }

    @Test
    fun part1() {
        part1(input)
    }

    @Test
    fun test2() {
        part2(input)
    }
}

