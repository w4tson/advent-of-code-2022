package aoc.day11

import java.lang.IllegalStateException
import kotlin.math.floor

data class ThrownItem(val monkeyId: Int, val item : Long)

fun part2(monkeys: List<Monkey>): Long {
    return performRounds(monkeys, 10_000).map { it.inspections }
        .sorted()
        .takeLast(2)
        .reduce(Long::times)
        .also { println(it) }
}
fun performRounds(monkeys: List<Monkey>, rounds : Int): List<Monkey> {
    (0 until rounds).map { round(monkeys) }

    return monkeys
}

fun part1(monkeys: List<Monkey>) : Long {
    return performRounds(monkeys, 20).map { it.inspections }
        .sorted()
        .takeLast(2)
        .reduce(Long::times)
        .also { println(it) }
}

fun round(monkeys : List<Monkey>) {
    monkeys.forEach {m ->
        m.turn().forEach {(id, item) ->
            monkeys[id].items.add(item)
        }
    }
}

data class Monkey(val id: Int,
                  val items : MutableList<Long>,
                  val operator: Char,
                  val rhs: String,
                  val divisibleBy: Long,
                  val ifTrue: Int,
                  val ifFalse: Int,
                  var commonMultiple: Long = 0) {
    var inspections : Long = 0

    fun turn() : List<ThrownItem> {
        return items.map { processItem(it) }.also { items.clear() }
    }

    fun processItem(item: Long) : ThrownItem {
        inspections += 1
        val value = when(rhs) {
            "old" -> item
            else -> rhs.toLong()
        }

        val operatorApplied = when(operator) {
            '+' -> item + value
            '*' -> item * value
            else -> throw IllegalStateException()
        }
        val newValue = operatorApplied % commonMultiple
        val monkeyDestination = if (operatorApplied % divisibleBy == 0L) ifTrue else ifFalse
        if (operatorApplied < 0) {
            println("warning ${operatorApplied}")
        }

        return ThrownItem(monkeyDestination, newValue)
    }
}



