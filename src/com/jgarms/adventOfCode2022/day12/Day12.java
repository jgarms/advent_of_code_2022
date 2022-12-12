package com.jgarms.adventOfCode2022.day12;

import com.jgarms.adventOfCode2022.Utils;

public class Day12 {

    public static void main(String... args) {
        Day12 day12 = new Day12();
        day12.partOne();
        day12.partTwo();
    }

    public void partOne() {
        Grid grid = new Grid(Utils.getInputAsList(this, "day12.txt"));
        grid.calculateShortestPaths(grid.start);
        System.out.println("Part one: " + grid.end.distanceFromStart);
    }

    public void partTwo() {
        Grid grid = new Grid(Utils.getInputAsList(this, "day12.txt"));
        int shortestDistance = grid.getShortestDistanceFromElevation(0);
        System.out.println("Part two: " + shortestDistance);
    }
}
