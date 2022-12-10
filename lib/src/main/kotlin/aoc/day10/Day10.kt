package aoc.day10


sealed class Instr()
class Addx(val value: Int, var cycle: Int = 2) : Instr()
class Noop(var cycle: Int = 1) : Instr()

fun part1(crt: CRT) : Int {
    return  (listOf(1) + (20 .. 220 step 40))
        .windowed(2, 1)
        .sumOf { (prev, next) ->
            crt.run(next- prev) * next
        }
}


class CRT(val instructions : Array<Instr>) {
    private var cycles = 0;
    private var x = 1;
    val program = mutableListOf(*instructions)


    fun run(numOfCycles : Int = 5) : Int{
        val max = cycles + numOfCycles
        println("cyclesDone = $cycles, numOfCycles= $numOfCycles, max = $max")

        while (cycles < max && program.isNotEmpty()) {

            when (val instr = program.first()) {
                is Noop -> { program.removeFirst(); cycles += 1 }
                is Addx -> {
                    this.cycles += 1
                    instr.cycle -= 1
                    if (instr.cycle == 0) {
                        program.removeFirst()

                        x += instr.value
                    }

                }
            }
        }

        return x.also { println(it) }
    }
}
