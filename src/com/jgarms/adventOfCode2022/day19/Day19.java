package com.jgarms.adventOfCode2022.day19;

import com.jgarms.adventOfCode2022.Utils;

import java.util.Scanner;

public class Day19 {

    public static void main(String... args) {
        Day19 day19 = new Day19();
        day19.partOne();
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
}
