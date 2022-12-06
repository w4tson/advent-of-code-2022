package aoc.day05

import removeLast

val regex = "[\\d\\s]+".toRegex()
val whiteSpace = "\\s+".toRegex()
val move = """move (\d+) from (\d+) to (\d+)""".toRegex()
class Crane(val input: String ) {

    var stacks : Array<ArrayDeque<Char>>
    val stackData = input.lines().takeWhile { !it.matches(regex) }
    val instructions = input.split("\n\n")[1]

    init {
        val num = input.lines()
            .dropWhile { !it.matches(regex) }
            .first().trim().split(whiteSpace)
            .last()
            .toInt()

        this.stacks = Array(num) { ArrayDeque() }

        stackData.forEach {
            it.toCharArray().forEachIndexed { i, c ->
                if (c.isLetter()) {
                    val index = (i / 4)
                    stacks[index].addFirst(c)
                }
            }
        }
    }
    fun move() : String {
        instructions.lines().forEach {
            val matchResult = move.find(it)
            val (numToMove, from, to) = matchResult!!.destructured
            val fromStack = stacks[from.toInt() - 1]
            val toStack = stacks[to.toInt() - 1]

            (0 until numToMove.toInt()).forEach {
                val c = fromStack.removeLast()
                toStack.addLast(c)
            }
        }

        return stacks.map { it.last() }.joinToString("")
    }

    fun move9001() : String {
        instructions.lines().forEach {
            val matchResult = move.find(it)
            val (numToMove, from, to) = matchResult!!.destructured
            val fromStack = stacks[from.toInt() - 1]
            val toStack = stacks[to.toInt() - 1]

            val moving = fromStack.removeLast(numToMove.toInt())
            moving.forEach {
                toStack.addLast(it)
            }
        }

        return stacks.map { it.last() }.joinToString("")
    }
}