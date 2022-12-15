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

    @Test
    fun test1() {
        val t = Tunnels.toTunnels(testInput)
//        t.show()
        assertEquals(26, t.part1(10))
    }

    @Test
    fun part1() {
        val t = Tunnels.toTunnels(input)
        t.part1(2000000)
    }
}

