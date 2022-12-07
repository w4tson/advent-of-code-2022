package aoc.day07

import kotlin.collections.ArrayDeque

sealed interface TreeObject
class File(val name: String, val size: Int) : TreeObject {
    override fun toString(): String {
        return "$name (file, size=$size)"
    }
}
class Dir(val name: String, val fqp : String, val files: MutableSet<TreeObject>) : TreeObject {
    override fun toString(): String = "dir $name"

    override fun equals(other: Any?): Boolean = (other is Dir && other.name == name && other.fqp == fqp)

    override fun hashCode(): Int = name.hashCode() + fqp.hashCode()

    fun getDir(dirName : String) : Dir? {
        return files.first { it is Dir && it.name == dirName } as Dir?
    }

    fun sum() : Int {
        return files.sumOf {
            when (it) {
                is File -> it.size
                is Dir -> it.sum()
            }
        }
    }
}

val r = "^(\\d+) (.*)".toRegex()

fun part1(input : String): Int {
    val path = ArrayDeque<Dir>()

    val allPaths = mutableSetOf<Dir>()

    val root = Dir("/", "/", mutableSetOf())

    input.lines().forEach { line ->
        if (line == "\$ cd /") {
            path.removeAll { true }
            path.addLast(root)
        } else if (line == "\$ cd .."){
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
                val file = File(name, size.toInt())
                path.last().files.add(file)
            }
        }
    }

    return allPaths.map { it.sum() }
        .filter { it <= 100_000 }
        .sum()
        .also { println(it) }
}
