package com.jgarms.adventOfCode2022.day4;

import com.jgarms.adventOfCode2022.Utils;
import java.io.BufferedReader;
import java.util.Scanner;

public class Day4 {

    public static void main(String... args) throws Exception {
        Day4 day4 = new Day4();
        day4.partOneTest();
        day4.partOne();

        day4.partTwoTest();
        day4.partTwo();
    }

    private void partOneTest() {
        Scanner s = new Scanner(sampleInput);
        int total = 0;
        while (s.hasNext()) {
            final String line = s.nextLine();
            ClearingAssignment assignment = new ClearingAssignment(line);
            //System.out.println(assignment.firstLow + "-" + assignment.firstHigh + "," + assignment.secondLow + "-" + assignment.secondHigh + ": " + assignment.isRedundant());
            if (assignment.isRedundant()) {
                total++;
            }
        }
        System.out.println("Part 1 test: " + total);
    }

    private void partOne() throws Exception {
        BufferedReader r = Utils.getInput(this, "day4.txt");
        int total = 0;
        while (r.ready()) {
            ClearingAssignment c = new ClearingAssignment(r.readLine());
            if (c.isRedundant()) {
                total++;
            }
        }
        System.out.println("Part 1: " + total);
    }

    private void partTwoTest() {
        Scanner s = new Scanner(sampleInput);
        int total = 0;
        while (s.hasNext()) {
            final String line = s.nextLine();
            ClearingAssignment assignment = new ClearingAssignment(line);
            //System.out.println(assignment.firstLow + "-" + assignment.firstHigh + "," + assignment.secondLow + "-" + assignment.secondHigh + ": " + assignment.doesOverlap());
            if (assignment.doesOverlap()) {
                total++;
            }
        }
        System.out.println("Part 2 test: " + total);
    }

    private void partTwo() throws Exception {
        BufferedReader r = Utils.getInput(this, "day4.txt");
        int total = 0;
        while (r.ready()) {
            ClearingAssignment c = new ClearingAssignment(r.readLine());
            if (c.doesOverlap()) {
                total++;
            }
        }
        System.out.println("Part 2: " + total);
    }

    private final String sampleInput = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8""";
}
