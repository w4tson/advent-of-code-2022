package aoc.day02

enum class Outcome {
    WIN,
    LOSE,
    DRAW
}
enum class RPS(val value: Int, val beats: String) {
    ROCK(1, "Z"),
    PAPER(2, "X"),
    SCISSORS(3, "Y");

    fun play(opponent : String) : Int{
        us[opponent]?.let {
            if (this.value == it.value) return 3
            if (this.beats == opponent) return 0
        }
        return 6
    }

    fun outcome(o : Outcome) : RPS {
        return when (o) {
            Outcome.LOSE -> us[this.beats]!!
            Outcome.DRAW -> this
            else -> RPS.values().filter { it != this && us[this.beats] != it }.first()
        }
    }
}
val them = mapOf(
    "A" to RPS.ROCK,
    "B" to RPS.PAPER,
    "C" to RPS.SCISSORS,
)

val us = mapOf(
    "X" to RPS.ROCK,
    "Y" to RPS.PAPER,
    "Z" to RPS.SCISSORS,
)

val outcomes = mapOf(
    "X" to Outcome.LOSE,
    "Y" to Outcome.DRAW,
    "Z" to Outcome.WIN,
)


fun playAll(input: String) : Int {
    return input.lines().map{
        val (a,b) = it.split(" ")
        play2(a,b)
    }.sum()
}


fun play(theirGo: String, ourGo: String) : Int {
    val result = them[theirGo]?.let {
        us[ourGo]!!.value + it.play(ourGo)
    }

    return result!!
}

fun play2(theirGo: String, desiredOutcome: String) : Int {
    val result = them[theirGo]?.let {
        val ourGo = them[theirGo]!!.outcome(outcomes[desiredOutcome]!!)
        val a = us.entries.first { it.value == ourGo }
        ourGo.value + it.play(a.key)
    }

    return result!!
}


