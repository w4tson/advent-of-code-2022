package aoc.day14

import Coord
import java.lang.IllegalStateException


class Cave(val map: MutableMap<Coord, Char>, private val hasFloor : Boolean){
    private val source = Coord(500,0)
    private val minx : Int
    private val miny : Int
    private val maxy : Int
    private val maxx : Int
    var sand = source

    init {
        map[source] = '+'
        minx = map.keys.minOf { it.x }
        miny = map.keys.minOf { it.y }
        maxx = map.keys.maxOf { it.x }
        maxy = map.keys.maxOf { it.y } + 2
    }

    fun simulatePart1() = simulate { sand.y <= maxy }
    fun simulatePart2() = simulate { map[source] != 'O' }

    fun simulate(isTrue : () -> Boolean): Int {
        while(isTrue()) {
            flow(sand)?.let {
                sand = it
            } ?: run {
                map[sand] = 'O'
                sand = source
            }
        }

        return map.entries.count{ it.value == 'O' }.also { println(it) }
    }

    fun flow(sand : Coord) : Coord? {
        val south = sand.south()
        val southEast = sand.southEast()
        val southWest = sand.southWest()

        return if (lookup(south) == '.') {
            south
        } else if (lookup(southWest) == '.') {
            southWest
        } else if (lookup(southEast) == '.') {
            southEast
        } else null
    }

    fun lookup(coord : Coord) : Char = when (coord.y) {
        maxy -> if (hasFloor) '#' else '.'
        else -> map.getOrDefault(coord, '.')
    }
}

fun toCave(input: String, hasFloor: Boolean = false) : Cave = Cave(toCoords(input).associateWith { '#' }.toMutableMap(), hasFloor)
fun toCoords(input: String) : List<Coord> = input.lines().flatMap(::toCoordsForLine)

fun toCoordsForLine(line : String) : Set<Coord> {
    return line.split(" -> ")
        .map {
            val (x,y) = it.split(",")
            Coord(x.toInt(), y.toInt())
        }
        .windowed(2, 1)
        .fold(setOf()) { acc, (from, to) ->
            val newCoords = if (from.x == to.x) {
                rangeBetween(from.y, to.y).map { Coord(from.x, it) }
            } else if (from.y == to.y){
                rangeBetween(from.x, to.x).map { Coord(it, from.y) }
            } else {
                throw IllegalStateException()
            }
            acc + newCoords
        }
}

fun rangeBetween(from: Int, to: Int) : IntRange {
    return if (from < to) {
        (from .. to)
    } else {
        (to..from)
    }
}
