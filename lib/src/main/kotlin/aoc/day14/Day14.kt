package aoc.day14

import Coord
import java.lang.IllegalStateException


class Cave(val map: MutableMap<Coord, Char>){
    private val source = Coord(500,0)
    private val minx : Int
    private val miny : Int
    private val maxy : Int
    private val maxx : Int

    init {
        map.put(source, '+')
        minx = map.keys.minOf { it.x }
        miny = map.keys.minOf { it.y }
        maxx = map.keys.maxOf { it.x }
        maxy = map.keys.maxOf { it.y }
    }

    fun showMap() {
        (miny..maxy).forEach {  y ->
            print("$y ")
            (minx..maxx).forEach { x ->
                print(map.getOrDefault(Coord(x,y),'.'))
            }
            println("  $maxx")
        }
    }

    fun simulate(): Int {
        var sand = source.south()
        while(sand.y <= maxy) {
            flow(sand)?.let {
                sand = it
            } ?: run {
                map[sand] = 'O'
                sand = source.south()
            }
        }

        return map.entries.count{ it.value == 'O' }.also { println(it) }
    }

    fun flow(sand : Coord) : Coord? {
        val south = sand.south()
        val southEast = sand.southEast()
        val southWest = sand.southWest()

        return if (map.getOrDefault(south, '.') == '.') {
            south
        } else if (map.getOrDefault(southWest, '.') == '.') {
            southWest
        } else if (map.getOrDefault(southEast, '.') == '.') {
            southEast
        } else null
    }
}

fun toCave(input: String) : Cave {
    val map = toCoords(input).map { Pair(it, '#') }.toMap().toMutableMap()
    return Cave(map)
}

fun toCoords(input: String) : List<Coord> = input.lines().flatMap { toCoordsForLine(it) }

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
