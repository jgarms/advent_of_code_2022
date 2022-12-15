package com.jgarms.adventOfCode2022.day15;

import com.jgarms.adventOfCode2022.Utils;

public class Day15 {

    public static void main(String... args) {
        Day15 day15 = new Day15();
        day15.partOne();
    }

    private void partOne() {
        Cave cave = new Cave(Utils.getScanner(this, "day15.txt"));
        System.out.println("Part one: " + cave.getNumForbiddenBeaconPositions(2000000));
    }
}
