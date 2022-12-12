package aoc.day12

import Coord
import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day12Test {

    val input = readInput("/day12/day12.txt")
    val testInput = readInput("/day12/day12-test.txt")

    val testStr = """
        Sabqponm
        abcryxxl
        accszExk
        acctuvwj
        abdefghi
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(31, part1(testStr))
    }

    @Test
    fun name() {
        assertTrue(canMove('a','a'))
        assertTrue(canMove('a','b'))
        assertFalse(canMove('a','c'))
        assertTrue(canMove('S','a'))
        assertTrue(canMove('S','b'))
        assertFalse(canMove('S','c'))
        assertFalse(canMove('x','E'))
        assertTrue(canMove('z','E'))
        assertTrue(canMove('y','E'))
        assertTrue(canMove('x','t'))
        assertTrue(canMove('S','b'))
    }

    @Test
    fun part1() {
        part1(input)
    }

    @Test
    fun test2() {
        assertEquals(29, part2(testStr))
    }

    @Test
    fun part2() {
        part2(input)
    }
}

