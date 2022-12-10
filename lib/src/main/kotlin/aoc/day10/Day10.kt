package aoc.day10


sealed class Instr()
class Addx(val value: Int, var cycle: Int = 2) : Instr()
class Noop : Instr()

fun part1(crt: CRT) : Int = (listOf(1) + (20 .. 220 step 40))
    .windowed(2, 1)
    .sumOf { (prev, next) -> crt.run(next- prev) * next }

fun part2(crt: CRT) {
    crt.run(Int.MAX_VALUE)
}

class CRT(instructions : Array<Instr>) {
    private var cycle = 0;
    private var x = 1;
    private val program = mutableListOf(*instructions)
    private val DISPLAY_WIDTH = 40
    private val DISPLAY_HEIGHT = 6
    private var display = Array(DISPLAY_WIDTH * DISPLAY_HEIGHT){'.'}

    fun drawBeam() {
        val currentCycle = cycle + 1

        display[cycle] = when(cycle % DISPLAY_WIDTH) {
            x-1, x, x+1 -> '#'
            else -> '.'
        }

        (0 until currentCycle).forEach {
            if (it % DISPLAY_WIDTH == 0 && it !=0) println()
            print(display[it])
        }
        println()
    }

    fun run(cycleLimit : Int = 5) : Int {
        println()
        val max = cycle + cycleLimit

        while (cycle < max && program.isNotEmpty()) {
            drawBeam()

            when (val instr = program.first()) {
                is Noop -> program.removeFirst();
                is Addx -> {
                    instr.cycle -= 1
                    if (instr.cycle == 0) {
                        program.removeFirst()
                        x += instr.value
                    }
                }
            }

            this.cycle += 1
        }

        return x.also { println(it) }
    }
}