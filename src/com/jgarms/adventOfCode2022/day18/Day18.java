package com.jgarms.adventOfCode2022.day18;

import com.jgarms.adventOfCode2022.Utils;

public class Day18 {

    public static void main(String... args) {
        Day18 day18 = new Day18();
        day18.partOne();
        day18.partTwo();
    }

    private void partOne() {
        Cube cube = new Cube(Utils.getScanner(this, "day18.txt"));
        System.out.println("Part one: " + cube.getSurfaceArea());
    }

    private void partTwo() {
        Cube cube = new Cube(Utils.getScanner(this, "day18.txt"));
        System.out.println("Part two: " + cube.getExposedSurfaceArea());
    }
}
