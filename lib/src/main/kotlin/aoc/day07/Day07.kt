package aoc.day07

sealed interface TreeObject

val dirTree = mutableMapOf<String, MutableList<TreeObject>>()


class File(val name: String, val size: Int) : TreeObject {
    override fun toString(): String {
        return "$name (file, size=$size)"
    }
}
class Dir(val name: String) : TreeObject {
    override fun toString(): String {
        return "dir $name"
    }

    fun sum() : Int {
        val children = dirTree.getOrDefault(name, emptyList())
        return children.sumOf {
            when (it) {
                is File -> it.size
                is Dir -> it.sum()
            }
        }
    }
}

val <T> List<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()

val cache = mutableMapOf<String, Int>()

fun sum(obj : Dir) : Int {
    return cache.computeIfAbsent(obj.name) {
        sum(listOf(obj), 0)
    }
}

tailrec fun sum(objects : List<TreeObject>, acc: Int) : Int {
    if (objects.isEmpty() || acc > 100001) return acc

    val head = objects.first()
    val extras = mutableListOf<TreeObject>()

    val inc = when (head) {
        is Dir -> {
            extras.addAll(dirTree.getOrDefault(head.name, emptyList()))
            0
        }
        is File ->  head.size
    }

    return sum( extras + objects.tail, acc + inc)
}

val r = "^(\\d+) (.*)".toRegex()

fun part1(input : String): Int {

    var path = ArrayDeque<String>()


    input.lines().forEach {
        if (it == "\$ cd /") {
            path.removeAll { true }
            path.addLast("/")
        } else if (it.startsWith("$ cd ")) {
            path.addLast(it.substring(5))
        } else if (it.startsWith("dir ")){
            val dirName = it.substring(4)
            dirTree.computeIfAbsent(path.last()) { mutableListOf() }.add(Dir(dirName))
        } else if (it == "\$ cd .."){
            path.removeLast()
        } else {
            r.find(it)?.let {
                val (size, name) = it.destructured
                val file = File(name, size.toInt())
                dirTree.computeIfAbsent(path.last()) { mutableListOf() }.add(file)
            }
        }
    }

//    dirTree.forEach {
//        println(it)
//    }

    return dirTree.keys.map { Dir(it) }
        .map{ sum(it) }
        .filter { it <= 100000 }
        .sum()
        .also { println(it) }
}
