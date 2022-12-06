package aoc.day06

import toSetOfAscii

fun startOfPacket(input : String): Int = searchDataStream(input, 4)
fun startOfMessage(input : String): Int = searchDataStream(input, 14)

private fun searchDataStream(input: String, window: Int): Int = input.windowed(window, 1)
    .takeWhile { !it.isStart() }
    .count() + window

fun String.isStart() : Boolean = this.toSetOfAscii().size == this.length