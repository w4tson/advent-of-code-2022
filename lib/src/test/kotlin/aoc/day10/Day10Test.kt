package aoc.day10

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

fun String.toInstructions() : Array<Instr> = this.lines().map {
    if (it == "noop") {
        Noop(1)
    } else {
        val (_, value) = it.split(" ")
        Addx(value.toInt(), 2)
    }
}.toTypedArray()


class Day10Test {

    val input = readInput("/day10/day10.txt")
    val testInput = readInput("/day10/day10-test.txt")

    val testStr = """
        noop
        addx 3
        addx -5
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(-1, CRT(testStr.toInstructions()).run())
        assertEquals(21, CRT(testInput.toInstructions()).run(19))
    }

    @Test
    fun test2() {
        val crt = CRT(testInput.toInstructions())
        assertEquals(13140, part1(crt))
    }

    @Test
    fun name() {
        val crt = CRT(input.toInstructions())
        println(part1(crt))

    }

    @Test
    fun part2Test() {
        val crt = CRT(testInput.toInstructions())
        part2(crt)
    }

    @Test
    fun part2() {
        val crt = CRT(input.toInstructions())
        part2(crt)
    }
}



