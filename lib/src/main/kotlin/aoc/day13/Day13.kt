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

fun part1(input : String) : Int = input.chunkByEmptyLine().mapIndexed { i, it ->
        val (p1, p2) = it.split("\n")
        val left = toPacket(p1)
        val right = toPacket(p2)
        println("== Pair ${i+1} ==")
        Pair(i+1, isOrdered(left, right))
    }
    .filter { it.second }
    .sumOf { it.first }
    .also { println(it) }

fun isOrdered(left : Packet, right: Packet) : Boolean = (comparePacket(left, right, 0) > 0)

fun output(s : String, deep : Int) {
    (0 until deep).forEach { print("\t") }
    println(s)
}

fun comparePacket(left : Packet, right: Packet, deep: Int) : Int {
    output("- Compare $left vs $right", deep)

    val result = left.data.zip(right.data)
        .asSequence()
        .map { compare(it.first, it.second, deep+1) }
        .dropWhile { it == 0 }.firstOrNull()

    val sizeComparison = right.data.size - left.data.size
    return if (result == null) {
        if (right.data.size > left.data.size) {
            output("Left side ran out of items", deep+1)
        } else {
            output("Right side ran out of items", deep+1)
        }
        sizeComparison
    } else if (result == 0) {
        sizeComparison
    } else {
        if (result < 0) {
            output("Right side is smaller", deep+1)
        } else {
            output("left side is smaller", deep+1)
        }
        result
    }
}

fun compare(left : P, right: P, deep : Int) : Int {
    return if (left is Value && right is Value) {
        (0 until deep).forEach { print("\t") }

        println("- Compare ${left.v} vs ${right.v}")
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
