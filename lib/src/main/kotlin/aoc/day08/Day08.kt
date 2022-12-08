package aoc.day08

import takeWhileInclusive

class Forest(private val map: List<List<Int>>) {

    val width = map[0].size
    val height = map.size

    fun isVisibleAt(x : Int, y: Int) : Boolean {
        val treeHeight = map[y][x]

        val visibleLeft = (0 until width)
            .filter { it != x }
            .map { map[y][it] }
            .none { h -> h >= treeHeight }

        val visibleRight = (x+1 until width)
            .map { map[y][it] }
            .none { h -> h >= treeHeight }

        val visibleUp = (0 until y)
            .map { map[it][x] }
            .none { h -> h >= treeHeight }

        val visibleDown = (y+1 until height)
            .map { map[it][x] }
            .none { h -> h >= treeHeight }

        return visibleUp || visibleDown || visibleLeft || visibleRight
    }

    fun scenicScore(x : Int, y : Int) : Int {
        val treeHeight = map[y][x]

        val left = (0 until x).reversed()
            .map { map[y][it] }
            .takeWhileInclusive { h -> h < treeHeight }
            .count()

        val right = (x+1 until width)
            .map { map[y][it] }
            .takeWhileInclusive { h -> h < treeHeight }
            .count()

        val up = (0 until y).reversed()
            .map { map[it][x] }
            .takeWhileInclusive { h -> h < treeHeight }
            .count()

        val down = (y+1 until height)
            .map { map[it][x] }
            .takeWhileInclusive { h -> h < treeHeight }
            .count()

        return up * down * left * right;
    }

    fun bestScenicScore(): Int = (1 until height-1).flatMap { y ->
        (1 until width-1).map {x -> scenicScore(x,y) }
    }.max().also { println(it) }

    fun visibleCount() : Int {
        val edges = (width * 2) + ((height -2) * 2)
        return (edges + (1 until height-1).sumOf {y ->
            (1 until width-1).count {x ->
                isVisibleAt(x,y)
            }
        }).also { println(it) }
    }

    companion object {
        fun toForest(input: String) : Forest {
            val map = input.lines().map { it.toList().map { it.digitToInt() } }.toList()
            return Forest(map)
        }
    }
}
