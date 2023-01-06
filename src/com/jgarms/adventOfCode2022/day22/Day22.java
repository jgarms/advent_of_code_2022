package com.jgarms.adventOfCode2022.day22;

import com.jgarms.adventOfCode2022.Utils;

public class Day22 {

    public static void main(String... args) {
        Day22 day22 = new Day22();
        day22.partOne();
    }

    private void partOne() {
        Grove grove = new Grove(Utils.getScanner(this, "day22.txt"));
        int password = grove.execute();
        System.out.println("Part one: " + password);
    }
}
