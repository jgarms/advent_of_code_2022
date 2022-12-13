package com.jgarms.adventOfCode2022.day13;

import com.jgarms.adventOfCode2022.Utils;

import java.util.Scanner;

public class Day13 {

    public static void main(String... args) {
        Day13 day13 = new Day13();
        day13.partOne();
    }

    private void partOne() {
        Scanner s = Utils.getScanner(this, "day13.txt");
        int sum = sumOrderedPacketIndices(s);
        System.out.println("Part one: " + sum);
    }

    public static int sumOrderedPacketIndices(Scanner s) {
        int index = 1;
        int sum = 0;
        while (s.hasNext()) {
            ListPacket left = Parser.parse(s.nextLine());
            ListPacket right = Parser.parse(s.nextLine());
            if (left.compareTo(right) < 0) {
                sum += index;
            }
            // consume blank line
            if (s.hasNext()) {
                String blank = s.nextLine();
                assert blank.length() == 0;
            }
            index++;
        }
        return sum;
    }
}
