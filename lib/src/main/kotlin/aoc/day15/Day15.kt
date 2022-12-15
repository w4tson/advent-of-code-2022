package aoc.day15

import Coord

// Sensor at x=20, y=1: closest beacon is at x=15, y=3
val r = """Sensor at x=([\d-]+), y=([\d-]+): closest beacon is at x=([\d-]+), y=([\d-]+)""".toRegex()

class Tunnels(val map : Map<Coord, Coord>) {
    val deadZones = map.flatMap {
        val r = it.key.manhattenDistanceTo(it.value)
        val deadZone = it.key.allCoordsWithinARadius(r)
        deadZone
    }
    .minus(map.entries.flatMap { listOf(it.key,it.value) }.toSet())
    .associateWith { true }

    val minx = deadZones.keys.minOf { it.x }
    val miny = deadZones.keys.minOf { it.y }
    val maxx = deadZones.keys.maxOf { it.x }
    val maxy = deadZones.keys.maxOf { it.y }


    fun deadZonesInRow(y : Int) : Int {
        return (minx..maxx).count {
            deadZones[Coord(it, y)] ?: false
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
        val deadZone = sensor.allCoordsWithinARadius(r)


        (miny..maxy).forEach { y ->
            print("$y ".padStart(5))
            (minx..maxx).forEach { x ->
                val message: Char = map.get(Coord(x, y))?.let { 'S' } ?: run {
                    if (map.values.contains(Coord(x,y)))  'B'  else if (deadZone.contains(Coord(x,y))) '#' else '.'
                }
                print("$message")
            }
            println()
        }
    }


}





