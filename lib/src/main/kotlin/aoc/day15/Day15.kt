package aoc.day15

import Coord
import kotlin.math.absoluteValue
import kotlin.math.max

// Sensor at x=20, y=1: closest beacon is at x=15, y=3
val r = """Sensor at x=([\d-]+), y=([\d-]+): closest beacon is at x=([\d-]+), y=([\d-]+)""".toRegex()


class Tunnels(val map : Map<Coord, Coord>) {

    val sensorsAndBeacons = map.entries.flatMap { listOf(it.key, it.value) }

    val minx = sensorsAndBeacons.minOf { it.x }
    val miny = sensorsAndBeacons.minOf { it.y }
    val maxx = sensorsAndBeacons.maxOf { it.x }
    val maxy = sensorsAndBeacons.maxOf { it.y }

    fun part1(y : Int) : Int {
        val sensorRadius = map.entries.associate { Pair(it.key, it.key.manhattenDistanceTo(it.value)) }

        val intersectingSensors = sensorRadius.entries.filter{ (it.key.y + it.value) >= y && (it.key.y - it.value) <= y  }

        return intersectingSensors
            .map {
                val deadZoneRange = it.key.allCoordsWithinARadius(it.value, y)
                Pair(deadZoneRange.first.x, deadZoneRange.second.x)
            }.sortedWith(compareBy({it.first} , {it.second }))
            .fold(listOf<Pair<Int, Int>>()) { acc, it ->
                if (acc.isEmpty()) listOf(it)
                else if (acc.last().second >= it.first) {
                    acc.dropLast(1) + listOf(Pair(acc.last().first, max(acc.last().second, it.second)))
                } else {
                    acc + it
                }
            }
          .sumOf {deadZoneRange ->
              val beaconsInTheMix = map.values.filter { it.y == y && it.x >= deadZoneRange.first && it.x <= deadZoneRange.second }.toSet().count()
              (deadZoneRange.first..deadZoneRange.second).count() - beaconsInTheMix
          }.also { println(it) }
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


    fun show() {
        val sensor = Coord(8, 7)
        val beacon = map.get(sensor)!!

        val r = sensor.manhattenDistanceTo(beacon)
        println("r = $r")
//        val deadZone = sensor.allCoordsWithinARadius(r)


        (miny..maxy).forEach { y ->
            print("$y ".padStart(5))
            (minx..maxx).forEach { x ->
                val message: Char = map.get(Coord(x, y))?.let { 'S' } ?: run {
                    if (map.values.contains(Coord(x,y)))  'B' else '.'
//                    if (map.values.contains(Coord(x,y)))  'B'  else if (deadZone.contains(Coord(x,y))) '#' else '.'
                }
                print("$message")
            }
            println()
        }
    }


}





