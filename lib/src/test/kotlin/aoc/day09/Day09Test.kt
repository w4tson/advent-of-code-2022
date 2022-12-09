package aoc.day09

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import Coord
import Util.Companion.readInput


val testStr = """
    R 4
    U 4
    L 3
    D 1
    R 4
    D 1
    L 5
    R 2
""".trimIndent()

val input = readInput("/day09/day09.txt")
class Day09Test {
    @Test
    fun part1Test() {
        val c1 = Coord(1,1)
        val c2 = Coord(1,1)
        assert(c1 == c2)

        assertEquals(13,  part1(testStr))
    }

    @Test
    fun part1() {
        part1(input)
    }
}