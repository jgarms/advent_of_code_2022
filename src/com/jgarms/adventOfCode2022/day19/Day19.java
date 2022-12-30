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
        long start = System.currentTimeMillis();
        while (scanner.hasNext()) {
            Blueprint blueprint = new Blueprint(scanner.nextLine());
            long blueprintStart = System.currentTimeMillis();
            int max = new State(blueprint, 24).getMaxGeodes();
            long blueprintTime = System.currentTimeMillis() - blueprintStart;
            int score = max * blueprint.idNumber;
            System.out.println("Blueprint " + blueprint.idNumber + ": " + score + ". Took " + blueprintTime + "ms.");
            total += score;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("Part one: " + total + ". Took " + time + "ms.");
    }

    private void partTwo() {
        Scanner scanner = Utils.getScanner(this, "day19.txt");
        int score = 1;
        long start = System.currentTimeMillis();
        for (int i=0; i<3; i++) {
            Blueprint blueprint = new Blueprint(scanner.nextLine());
            long blueprintStart = System.currentTimeMillis();
            int max = new State(blueprint, 32).getMaxGeodes();
            score = score * max;
            long blueprintTime = System.currentTimeMillis() - blueprintStart;
            System.out.println("Blueprint " + blueprint.idNumber + ": " + max + ". Took " + blueprintTime + "ms.");
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("Part two: " + score + ". Took " + time + "ms.");
    }
}
