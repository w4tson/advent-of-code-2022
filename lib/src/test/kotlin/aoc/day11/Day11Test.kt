package aoc.day11

import Util.Companion.readInput
import charList
import chunkByEmptyLine
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import stripAndLastToInt

val r = "Operation: new = old ([^\\s]+) (\\w+)".toRegex()

fun toMonkey(input : String) : Monkey {
    val lines = input.lines()
    val id = lines[0].replace(":","").stripAndLastToInt()
    val items = lines[1]
        .substring(18)
        .split(", ")
        .map(String::toLong)
        .toMutableList()

    val (operator, rhs) = r.find(lines[2])!!.destructured
    val divisibleBy = lines[3].stripAndLastToInt()
    val ifTrue = lines[4].stripAndLastToInt()
    val ifFalse = lines[5].stripAndLastToInt()

    return Monkey(id, items, operator.charList()[0], rhs, divisibleBy.toLong(), ifTrue, ifFalse)
}

class Day11Test {

    val input = readInput("/day11/day11.txt")
    val testInput = readInput("/day11/day11-test.txt")

    val testStr = """
        ...
    """.trimIndent()
    val testMonkeys = toMonkeys(testInput)
    val monkeys = toMonkeys(input)

    fun toMonkeys(input: String) : List<Monkey> {
        val m = input.chunkByEmptyLine().map(::toMonkey)
        val commonMuliple = m.map{ it.divisibleBy }.reduce(Long::times)
        m.forEach { it.commonMultiple = commonMuliple }
        return m
    }

    @Test
    fun test1() {
        assertEquals(10605, part1(testMonkeys))
    }

    @Test
    fun name() {
        part1(monkeys)
    }

    @Test
    fun part2Experiment() {
        part2(monkeys)
    }

}

