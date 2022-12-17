package com.jgarms.adventOfCode2022.day16;

import com.jgarms.adventOfCode2022.Utils;

public class Day16 {
    public static void main(String... args) {
        Day16 day16 = new Day16();
        day16.partOne();
    }

    private void partOne() {
        Volcano volcano = new Volcano(Utils.getScanner(this, "day16.txt"));
        System.out.println("Part one: " + volcano.getMaxPressureReleased(30));
    }
}
