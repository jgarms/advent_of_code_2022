package com.jgarms.adventOfCode2022.day20;

import com.jgarms.adventOfCode2022.Utils;

public class Day20 {

    public static void main(String... args) {
        Day20 day20 = new Day20();
        day20.partOne();
    }

    private void partOne() {
        Data data = new Data(Utils.getScanner(this, "day20.txt"));
        data.mix();
        System.out.println("Part one: " + data.getGroveCoordinates());
    }
}
