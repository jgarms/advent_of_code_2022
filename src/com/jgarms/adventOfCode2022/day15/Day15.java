package com.jgarms.adventOfCode2022.day15;

import com.jgarms.adventOfCode2022.Utils;

public class Day15 {

    public static void main(String... args) {
        Day15 day15 = new Day15();
        day15.partOne();
        day15.partTwo();
    }

    private void partOne() {
        Cave cave = new Cave(Utils.getScanner(this, "day15.txt"));
        System.out.println("Part one: " + cave.getNumForbiddenBeaconPositions(2000000));
    }

    private void partTwo() {
        Cave cave = new Cave(Utils.getScanner(this, "day15.txt"));
        Point point = cave.getUndetectedPoint(0, 0, 4000000, 4000000);
        long frequency = computeFrequency(point);
        System.out.println("Part two: " + frequency);
    }

    public static long computeFrequency(Point point) {
        return 4000000L * (long)point.x() + (long)point.y();
    }
}
