package aoc.day15

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day15Test {

    val input = readInput("/day15/day15.txt")
    val testInput = readInput("/day15/day15-test.txt")

    val testStr = """
        ...
    """.trimIndent()

    val t = Tunnels.toTunnels(testInput)

    @Test
    fun test1() {
//        t.show()
        assertEquals(26, t.countDeadzones(10))
    }

    @Test
    fun test2() {
        assertEquals(56000011, t.calcFrequency(20))
    }


    @Test
    fun part2() {
        val t = Tunnels.toTunnels(input)
        t.calcFrequency(4_000_000)

    }

    @Test
    fun part1() {
        val t = Tunnels.toTunnels(input)
        t.countDeadzones(2000000)
    }
}

