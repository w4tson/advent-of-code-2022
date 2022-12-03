#!/usr/bin/env bash

SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )

echo $SCRIPT_DIR
SRC_DIR=$SCRIPT_DIR/lib/src/main/kotlin/aoc
TEST_DIR=$SCRIPT_DIR/lib/src/test/kotlin/aoc
TEST_RES_DIR=$SCRIPT_DIR/lib/src/test/resources

prev_day=`ls $SRC_DIR | grep -E "day[0-9]+" | sort | tail -n 1 | sed 's/day//'`

printf -v next_day "%02d" $(($prev_day+1))
echo "Generating Day$next_day..."

mkdir -p $SRC_DIR/day$next_day
mkdir -p $TEST_DIR/day$next_day
mkdir -p $TEST_RES_DIR/day$next_day
touch $TEST_RES_DIR/day${next_day}/day${next_day}.txt
touch $TEST_RES_DIR/day${next_day}/day${next_day}-test.txt

cat << EOF > $SRC_DIR/day${next_day}/Day${next_day}.kt
package aoc.day${next_day}

fun part1(input : String) {

}
EOF

cat << EOF > $TEST_DIR/day${next_day}/Day${next_day}Test.kt
package aoc.day${next_day}

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day${next_day}Test {

    val input = readInput("/day${next_day}/day${next_day}.txt")
    val testInput = readInput("/day${next_day}/day${next_day}-test.txt")

    val testStr = """
        ...
    """.trimIndent()

    @Test
    fun test1() {
        part1(testStr)
    }

}

EOF

echo -e "DONE \U0001f381 \U0001f384 \U0001f385"

