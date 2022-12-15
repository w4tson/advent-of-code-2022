package aoc.day15

import Coord
import java.lang.Integer.min
import kotlin.math.max

val r = """Sensor at x=([\d-]+), y=([\d-]+): closest beacon is at x=([\d-]+), y=([\d-]+)""".toRegex()
class Tunnels(val map : Map<Coord, Coord>) {
    fun countDeadzones(y : Int, restrictBy: Pair<Int,Int> = Pair(Int.MIN_VALUE, Int.MAX_VALUE), excludeBeacons: Boolean = true) : Int {
          return horizontalDeadZones(y).sumOf { deadZoneRange ->
              println(deadZoneRange)
              val start = max(restrictBy.first, deadZoneRange.first)
              val end = min(restrictBy.second, deadZoneRange.second)
              val beaconsInTheMix = map.values.filter { it.y == y && it.x >= start && it.x <= end }.toSet().count()
              (start..end).count() - if (excludeBeacons) beaconsInTheMix else 0
          }.also { println(it) }
    }

    fun horizontalDeadZones(y: Int): List<Pair<Int, Int>> {
        val sensorRadius = map.entries.associate { Pair(it.key, it.key.manhattenDistanceTo(it.value)) }

        val intersectingSensors =
            sensorRadius.entries.filter { (it.key.y + it.value) >= y && (it.key.y - it.value) <= y }

        return intersectingSensors
            .map {
                val deadZoneRange = it.key.allCoordsWithinARadius(it.value, y)
                Pair(deadZoneRange.first.x, deadZoneRange.second.x)
            }.sortedWith(compareBy({ it.first }, { it.second }))
            .fold(listOf()) { acc, it ->
                if (acc.isEmpty()) listOf(it)
                else if (acc.last().second >= it.first) {
                    acc.dropLast(1) + listOf(Pair(acc.last().first, max(acc.last().second, it.second)))
                } else {
                    acc + it
                }
            }
    }

    fun calcFrequency(max: Int): Long {
        val y = (0..max).first { horizontalDeadZones(it).size > 1 }
        val x = horizontalDeadZones(y).first().second + 1
        return ((x.toLong() * 4_000_000L) + y.toLong()).also { println(it) }
    }

    companion object {
        fun toTunnels(input: String) : Tunnels {
            val beaconLocations = input.lines()
                .associate {
                    val (sx, sy, bx, by) = r.find(it)!!.destructured
                    val sensor = Coord(sx.toInt(), sy.toInt())
                    val beacon = Coord(bx.toInt(), by.toInt())
                    Pair(sensor, beacon)
                }
            val minx = beaconLocations.keys.minOf { it.x }
            val maxx = beaconLocations.keys.maxOf { it.x }
            val miny = beaconLocations.keys.minOf { it.y }
            val maxy = beaconLocations.keys.maxOf { it.y }
            val size = (maxx-minx)*(maxy-miny)

            println("minx=$minx maxx=$maxx miny=$miny maxy=$maxy    size=$size")
            return Tunnels(beaconLocations)
        }
    }
}