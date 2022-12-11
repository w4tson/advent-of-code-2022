package aoc.day11

import java.lang.IllegalStateException
import kotlin.math.floor

data class ThrownItem(val monkeyId: Int, val item : Long)

fun part1(monkeys: List<Monkey>): Int {

    (0 until 20).map {
        round(monkeys);
    }

    return monkeys.map { it.inspections }
        .sorted()
        .takeLast(2)
        .reduce(Int::times)
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
                  val ifFalse: Int) {
    var inspections : Int = 0

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
        val newValue = floor(operatorApplied.toDouble() / 3.0).toLong()
        val monkeyDestination = if (newValue % divisibleBy == 0L) ifTrue else ifFalse

        return ThrownItem(monkeyDestination, newValue)
    }
}



