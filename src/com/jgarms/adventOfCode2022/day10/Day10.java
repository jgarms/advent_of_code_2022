package com.jgarms.adventOfCode2022.day10;

import com.jgarms.adventOfCode2022.Utils;

public class Day10 {

    public static void main(String... args) {
        Day10 day10 = new Day10();
        day10.partOne();
        day10.partTwo();
    }

    private void partOne() {
        CPU cpu = new CPU(Utils.getScanner(this, "day10.txt"));
        cpu.execute();
        System.out.println("Part one: " + cpu.signalStrengthSum);
    }

    private void partTwo() {
        CPU cpu = new CPU(Utils.getScanner(this, "day10.txt"));
        cpu.execute();
        System.out.println("Part two:");
        System.out.println(cpu.crt);
    }
}
