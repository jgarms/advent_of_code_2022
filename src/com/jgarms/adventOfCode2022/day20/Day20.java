package com.jgarms.adventOfCode2022.day20;

import com.jgarms.adventOfCode2022.Utils;

public class Day20 {

    public static void main(String... args) {
        Day20 day20 = new Day20();
        day20.partOne();
        day20.partTwo();
    }

    private void partOne() {
        Data data = new Data(Utils.getScanner(this, "day20.txt"));
        data.mix();
        System.out.println("Part one: " + data.getGroveCoordinates());
    }

    private void partTwo() {
        Data data = new Data(Utils.getScanner(this, "day20.txt"));
        data.applyDecryptionKey();
        for (int i=0; i<10; i++) {
            data.mix();
        }
        System.out.println("Part two: " + data.getGroveCoordinates());
    }
}
