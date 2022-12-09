import java.lang.IllegalStateException
import java.nio.charset.StandardCharsets
import java.util.*
import kotlin.math.absoluteValue
import kotlin.streams.toList

class Util {

    companion object {
        fun readInput(fileName: String): String =
            this::class.java.getResource(fileName).readText(StandardCharsets.UTF_8)
    }
}

fun repeatRange(repeat : Int) : Sequence<Int> {
    return generateSequence(0, { (it+1) % repeat })
}

fun <T> List<T>.cycle() : Sequence<T> {
    return repeatRange(this.size).map { this[it] }
}

fun naturalNumbers() : Sequence<Int> = (0..Int.MAX_VALUE).asSequence()

fun <T> Sequence<T>.takeWhileInclusive(predicate : (T) -> Boolean) : Sequence<T> {
    var keepGoing: Boolean
    var prevPred = true
    return this.takeWhile{
        keepGoing = prevPred
        prevPred = predicate(it)
        keepGoing
    }
}

fun <T> List<T>.takeWhileInclusive(predicate : (T) -> Boolean) : List<T> {
    var keepGoing: Boolean
    var prevPred = true
    return this.takeWhile{
        keepGoing = prevPred
        prevPred = predicate(it)
        keepGoing
    }
}

fun Sequence<Long>.multiply() : Long = this.reduce { acc, i -> acc * i}
fun Iterable<Long>.multiply() : Long = this.reduce { acc, i -> acc * i}


fun <T> permutations(list : List<T>) : Sequence<List<T>> {
    return sequence {
        if (list.size <= 1) yield(list) else {
            list.indices.forEach { i ->
                permutations(list.slice(0 until i) + list.slice(i+1 until list.size)).forEach { p ->
                    yield(listOf(list[i]) +p)
                }
            }
        }
    }
}

fun String.charList() : List<Char> = this.toCharArray().toList()

fun <T> List<List<T>>.contentDeepEquals(other: List<List<T>>) : Boolean {
    this.indices.forEach {
        val a = this[it]
        val b = other[it]
        a.indices.forEach {
            if (a[it] == b[it]) return false
        }
    }
    return true
}

fun <T> List<T>.toDeque() :Deque<T> {
    val d = ArrayDeque<T>()
    this.forEach { d.addLast(it) }
    return d
}

fun String.splitIntoTwo() : Pair<String, String> {
    val halfWay = this.length /2
    return Pair(this.substring(0,halfWay),this.substring(halfWay,this.length))
}

fun String.toSetOfAscii() : Set<Int> = this.chars().toList().toSet()

fun ClosedRange<Int>.fullyContains(other: ClosedRange<Int>) : Boolean {
    return other.start >= this.start && other.endInclusive <= this.endInclusive
}

fun ClosedRange<Int>.hasOverlapWith(other: ClosedRange<Int>) : Boolean {
    return (other.start >= this.start && other.start <= this.endInclusive) ||
     (other.endInclusive >= this.start && other.endInclusive <= this.endInclusive) ||
            (this.start >= other.start && this.start <= other.endInclusive) ||
            (this.endInclusive >= other.start && this.endInclusive <= other.endInclusive)
}

fun <T> kotlin.collections.ArrayDeque<T>.removeLast(n : Int) : List<T> {
    return (0 until n ).map { this.removeLast() }.toList().reversed()
}

class Coord(val x: Int, val y : Int) {

    companion object {
        val origin = Coord(0, 0)

    }

    fun isOneSquareAway(other: Coord) : Boolean {
        val xDist = (x - other.x).absoluteValue
        val yDist = (y - other.y).absoluteValue
        val manhattanDistance = xDist + yDist
        return this == other || manhattanDistance == 1 || (xDist == 1 && yDist == 1)
    }

    fun moveByChar(ch : Char) : Coord = when (ch) {
        'U' -> Coord(x,y-1)
        'D' -> Coord(x,y+1)
        'L' -> Coord(x-1,y)
        'R' -> Coord(x+1,y)
        else -> throw IllegalStateException("Can't move in direction $ch")
    }

    fun coordBehind(ch : Char) : Coord {
        return when (ch) {
            'U' -> Coord(x,y+1)
            'D' -> Coord(x,y-1)
            'L' -> Coord(x+1,y)
            'R' -> Coord(x-1,y)
            else -> throw IllegalStateException("Can't find coord behind in direction $ch")
        }
    }



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coord

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    override fun toString(): String {
        return "Coord(x=$x, y=$y)"
    }
}