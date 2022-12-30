package com.jgarms.adventOfCode2022.day19;

import com.jgarms.adventOfCode2022.Utils;

import java.util.Scanner;

public class Day19 {

    public static void main(String... args) {
        Day19 day19 = new Day19();
        day19.partOne();
        day19.partTwo();
    }

    private void partOne() {
        Scanner scanner = Utils.getScanner(this, "day19.txt");
        int total = 0;
        while (scanner.hasNext()) {
            Blueprint blueprint = new Blueprint(scanner.nextLine());
            int max = new State(blueprint, 24).getMaxGeodes();
            int score = max * blueprint.idNumber;
            total += score;
        }
        System.out.println("Part one: " + total);
    }

    private void partTwo() {
        Scanner scanner = Utils.getScanner(this, "day19.txt");
        int score = 1;
        for (int i=0; i<3; i++) {
            Blueprint blueprint = new Blueprint(scanner.nextLine());
            int max = new State(blueprint, 32).getMaxGeodes();
            score = score * max;
        }
        System.out.println("Part two: " + score);
    }
}
