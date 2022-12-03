package com.jgarms.adventOfCode2022.day3;

import com.jgarms.adventOfCode2022.Utils;

import java.io.BufferedReader;

public class Day3 {

    public static void main(String... args) throws Exception {
        Day3 day3 = new Day3();
        day3.part1();
        day3.part2();
    }
    private void part1() throws Exception {
        BufferedReader reader = Utils.getInput(this, "day3.txt");
        int total = 0;
        while (reader.ready()) {
            final String line = reader.readLine();
            Rucksack rucksack = new Rucksack(line);
            char sharedItem = rucksack.getSharedItemFromCompartments();
            int value = getValueForItem(sharedItem);
            total += value;
        }
        System.out.println("part 1: " + total);
    }

    private void part2() throws Exception {
        BufferedReader reader = Utils.getInput(this, "day3.txt");
        int total = 0;
        while (reader.ready()) {
            final String line = reader.readLine();
            Rucksack rucksack1 = new Rucksack(line);
            Rucksack rucksack2 = new Rucksack(reader.readLine());
            Rucksack rucksack3 = new Rucksack(reader.readLine());
            char sharedItem = rucksack1.getSharedBadgeItem(rucksack2, rucksack3);
            int value = getValueForItem(sharedItem);
            total += value;
        }
        System.out.println("part 2: " + total);
    }

    private int getValueForItem(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 1;
        } else {
            return c - 'A' + 27;
        }
    }
}
