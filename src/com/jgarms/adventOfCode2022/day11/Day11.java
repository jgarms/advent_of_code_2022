package com.jgarms.adventOfCode2022.day11;

import com.jgarms.adventOfCode2022.Utils;

public class Day11 {

    public static void main(String... args) {
        Day11 day11 = new Day11();
        day11.partOne();
    }

    private void partOne() {
        MonkeyHandler handler = new MonkeyHandler(Utils.getScanner(this, "day11.txt"), true);
        for (int i=0; i<20; i++) {
            handler.performRound();
        }
        System.out.println("Part one: " + handler.calculateMonkeyBusiness());
    }
}
