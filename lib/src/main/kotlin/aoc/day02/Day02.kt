package aoc.day02
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
fun playAll(input: String) : Int {
    return input.lines().map{
        val (a,b) = it.split(" ")
        play(a,b)
    }.sum()
}
fun play(theirGo: String, ourGo: String) : Int {
    val result = them[theirGo]?.let {
        us[ourGo]!!.value + it.play(ourGo)
    }

    return result!!
}
