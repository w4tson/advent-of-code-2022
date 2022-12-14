package aoc.day13

import chunkByEmptyLine

sealed class P(val data : MutableList<P>) {
    abstract fun toPacket() : Packet
}
data class Value(val v : Int) : P(mutableListOf()) {
    override fun toPacket(): Packet {
        return Packet(mutableListOf(this))
    }

    override fun toString(): String = "$v"
}
class Packet(data : MutableList<P>) : P(data) {
    override fun toString(): String {
        val inner = data.map { it.toString() }.joinToString(",")
        return "[$inner]"
    }

    override fun toPacket(): Packet = this
}

private val packetComparator =  Comparator<Packet> { a, b -> -1 * compare(a, b, 0) }

fun part2(input: String): Int {
    val dividers = listOf("[[2]]", "[[6]]")
    return (input.lines().filter { it.trim().isNotEmpty() } + dividers)
        .asSequence()
        .map { toPacket(it) }
        .sortedWith(packetComparator)
        .mapIndexed{ i, p -> Pair(i+1,p) }
        .filter { dividers.contains(it.second.toString()) }
        .map { it.first }
        .reduce(Int::times)
        .also { println(it) }
}

fun part1(input : String) : Int = input.chunkByEmptyLine()
    .mapIndexed { i, it ->
        Pair(i+1, isOrdered(toPacket(it.split("\n")[0]), toPacket(it.split("\n")[1])))
    }
    .filter { it.second }
    .sumOf { it.first }
    .also { println(it) }

fun isOrdered(left : Packet, right: Packet) : Boolean = comparePacket(left, right, 0) > 0

fun comparePacket(left : Packet, right: Packet, deep: Int) : Int {
    val result = left.data.zip(right.data)
        .asSequence()
        .map { compare(it.first, it.second, deep+1) }
        .dropWhile { it == 0 }
        .firstOrNull()

    return when (result) {
        null, 0 -> right.data.size - left.data.size
        else ->  result
    }
}

fun compare(left : P, right: P, deep : Int) : Int {
    return if (left is Value && right is Value) {
        right.v - left.v
    } else if (left is Packet && right is Packet) {
        comparePacket(left, right, deep +1)
    } else {
        comparePacket(left.toPacket(), right.toPacket(), deep+1)
    }
}

fun toPacket(packetStr : String): Packet {
    val root = Packet(mutableListOf())
    val stack = ArrayDeque<P>(listOf(root))
    var curr= ""
    packetStr.drop(1).forEach {
        if (it.isDigit()) {
            curr = "$curr$it"
        } else if (it == '[') {
            val newPacket = Packet(mutableListOf())
            stack.last().data.add(newPacket)
            stack.addLast(newPacket)
        } else if (it == ']' || it == ',') {
            if (curr.isNotEmpty()) {
                val v = Value(curr.toInt())
                stack.last().data.add(v)
                curr=""
            }

            if (it==']') { stack.removeLast() }
        }
    }

    return root
}
