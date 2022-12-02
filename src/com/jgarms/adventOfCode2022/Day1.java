package com.jgarms.adventOfCode2022;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {

    public static void main(String... args) throws Exception {
        part1();
        part2();
    }

    private static void part1() throws Exception {
        BufferedReader reader = Utils.getInput("day2.txt");
        int currentElfCalories = 0;
        int maxElfCalories = 0;
        while (reader.ready()) {
            final String line = reader.readLine();
            if (line.isEmpty()) {
                // End of current elf
                if (currentElfCalories > maxElfCalories) {
                    maxElfCalories = currentElfCalories;
                }
                currentElfCalories = 0;
            } else {
                int calories = Integer.parseInt(line);
                currentElfCalories += calories;
            }
        }
        System.out.println("Part 1: " + maxElfCalories);
    }

    private static void part2() throws Exception {
        BufferedReader reader = Utils.getInput("day1.txt");
        String line;
        List<Integer> elvesCalories = new ArrayList<>();
        int currentElfCalories = 0;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // End of current elf
                elvesCalories.add(currentElfCalories);
                currentElfCalories = 0;
            } else {
                int calories = Integer.parseInt(line);
                currentElfCalories += calories;
            }
        }
        elvesCalories.sort(Collections.reverseOrder());
        int topThree = 0;
        for (int i=0; i<3; i++) {
            topThree += elvesCalories.get(i);
        }
        System.out.println("Part 2: " + topThree);
    }
}
