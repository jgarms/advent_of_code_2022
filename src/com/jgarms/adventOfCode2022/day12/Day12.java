package com.jgarms.adventOfCode2022.day12;

import com.jgarms.adventOfCode2022.Utils;

public class Day12 {

    public static void main(String... args) {
        Day12 day12 = new Day12();
        day12.partOne();
    }

    public void partOne() {
        Grid partOneGrid = new Grid(Utils.getInputAsList(this, "day12.txt"));
        partOneGrid.calculateShortestPaths(partOneGrid.start);
        System.out.println("Part one: " + partOneGrid.end.distanceFromStart);
    }
}
