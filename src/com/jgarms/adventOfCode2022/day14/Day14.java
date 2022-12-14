package com.jgarms.adventOfCode2022.day14;

import com.jgarms.adventOfCode2022.Utils;

public class Day14 {

    public static void main(String... args) {
        Day14 day14 = new Day14();
        day14.partOne();
        day14.partTwo();
    }

    private void partOne() {
        Cave cave = new Cave(Utils.getScanner(this, "day14.txt"));
        int numUnits = 0;
        while (cave.addSand()) {
            numUnits++;
        }
        System.out.println("Part one: " + numUnits);
    }

    private void partTwo() {
        Cave cave = new Cave(Utils.getScanner(this, "day14.txt"));
        cave.addBottom();
        int numUnits = 0;
        while (cave.addSand()) {
            numUnits++;
        }
        System.out.println("Part two: " + numUnits);
    }
}
