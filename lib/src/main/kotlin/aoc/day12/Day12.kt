package aoc.day12

import Coord
import charList

data class HeightMap(val heights: MutableMap<Coord, Char>, val width: Int, val height: Int)

fun part1(input : String): Int {
    val heightMap = toHeightMap(input)
    val start: Coord = heightMap.heights.entries.filter { it.value == 'S' }.first().key
    return shortestPathToE(heightMap, start)
}

fun part2(input : String) : Int {
    val startingPoints = toHeightMap(input).heights.entries.filter { it.value == 'a' }.map { it.key }
    return startingPoints.map {
        val heightMap = toHeightMap(input)

        shortestPathToE(heightMap, it)
    }.min().also { println(it) }
}

fun shortestPathToE(heightMap : HeightMap, start: Coord): Int {
    val map = heightMap.heights

    val destination: Coord = map.entries.first { it.value == 'E' }.key

    val unvisited = map.entries.filter { it.key != start }.map { it.key }.toMutableSet()

    val rest = unvisited.map { Pair(it, Int.MAX_VALUE) }.toTypedArray()
    val distances = mutableMapOf(Pair(start, 0), *rest)
    val visited = mutableSetOf(start)
    var currentNode = start

    while (!visited.contains(destination)) {

        currentNode.getAdjacent(heightMap.width, heightMap.height, false)
            .filter {
                val m = canMove(map[currentNode]!!, map[it]!!)
                !visited.contains(it) && m
            }
            .forEach {  adjacent ->
                val newDistance = if (distances[currentNode]!! == Int.MAX_VALUE) distances[currentNode]!! else distances[currentNode]!! + 1
                distances.compute(adjacent) { key, old ->
                    val currDistance = (old?.let { old } ?: Int.MAX_VALUE)
                    val i: Int? = if (newDistance < currDistance) newDistance else currDistance
                    i?.let {
                        if (it < 0) {
                            println(it)
                        }
                    }
                    i
                }
            }
        visited.add(currentNode)
        unvisited.remove(currentNode)

        if (unvisited.isNotEmpty()) {
            currentNode = unvisited.map { Pair(it, distances[it]!!) }.minBy { it.second }.first
        }
    }

    val shortestDistance = distances[destination]!!

    return shortestDistance
}

fun canMove(from: Char, to: Char) : Boolean {
    val newTo = when(to) {
        'E' -> 'z'
        'S' -> 'a'
        else -> to
    }
    val newFrom = when(from) {
        'E' -> 'z'
        'S' -> 'a'
        else -> from
    }
    return ((newTo - newFrom == 0) || (newTo - newFrom == 1) || newTo < newFrom)
}

fun toHeightMap(input: String) : HeightMap {
    val map = mutableMapOf<Coord, Char>()
    val lines = input.lines()
    lines.forEachIndexed { y, line ->
        line.charList().forEachIndexed{ x, c -> map.put(Coord(x,y), c) }
    }

    val width = lines[0].length
    val height = lines.size

    return HeightMap(map, width, height)
}


