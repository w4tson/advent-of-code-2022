package aoc.day07

import kotlin.collections.ArrayDeque

sealed interface TreeObject
class File(val name: String, val size: Int) : TreeObject
class Dir(val name: String, val fqp : String, val files: MutableSet<TreeObject>) : TreeObject {
    fun getDir(dirName : String) : Dir? = files.firstOrNull { it is Dir && it.name == dirName } as Dir?

    fun sum() : Int = files.sumOf {
        when (it) {
            is File -> it.size
            is Dir -> it.sum()
        }
    }
}

val r = "^(\\d+) (.*)".toRegex()

fun allPaths(input : String): MutableSet<Dir> {
    val root = Dir("/", "/", mutableSetOf())
    val path = ArrayDeque(listOf(root))
    val allPaths = mutableSetOf(root)

    input.lines().forEach { line ->
        if (line == "\$ cd .."){
            path.removeLast()
        } else if (line.startsWith("$ cd ")) {
            val dirName = line.substring(5)
            path.last().getDir(dirName)?.let {
                path.addLast(it)
            }
        } else if (line.startsWith("dir ")) {
            val dirName = line.substring(4)
            val newDir = Dir(dirName, path.joinToString("/"), mutableSetOf())
            allPaths.add(newDir)
            path.last().files.add(newDir)

        } else {
            r.find(line)?.let {
                val (size, name) = it.destructured
                path.last().files.add(File(name, size.toInt()))
            }
        }
    }

    return allPaths;
}

fun part1(input : String) : Int = allPaths(input).map { it.sum() }
    .filter { it <= 100_000 }
    .sum()
    .also { println(it) }

fun part2(input: String): Int {
    val root = allPaths(input).find { it.name == "/" }!!
    val TOTAL_DISK_SPACE = 70000000
    val MIN_UNUSED_SPACE = 30000000
    val needToFree = root.sum() - (TOTAL_DISK_SPACE - MIN_UNUSED_SPACE)

    return allPaths(input)
        .map { it.sum() }
        .filter { it > needToFree }
        .sorted()
        .first()
        .also { println(it) }
}