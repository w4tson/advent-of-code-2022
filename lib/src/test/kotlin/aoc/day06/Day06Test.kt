package aoc.day06

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day06Test {

    val input = readInput("/day06/day06.txt")

    @Test
    fun test1() {
        assertEquals(7,  startOfPacket("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
        assertEquals(5,  startOfPacket("bvwbjplbgvbhsrlpgdmjqwftvncz"))
        assertEquals(6,  startOfPacket("nppdvjthqldpwncqszvftbrmjlhg"))
        assertEquals(10,  startOfPacket("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
        assertEquals(11,  startOfPacket("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
    }

    @Test
    fun part1() {
        println(startOfPacket(input))
    }

    @Test
    fun test2() {
        assertEquals(19,  startOfMessage("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
        assertEquals(23,  startOfMessage("bvwbjplbgvbhsrlpgdmjqwftvncz"))
        assertEquals(23,  startOfMessage("nppdvjthqldpwncqszvftbrmjlhg"))
        assertEquals(29,  startOfMessage("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
        assertEquals(26,  startOfMessage("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
    }

    @Test
    fun part2() {
        println(startOfMessage(input))
    }
}

