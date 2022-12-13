package com.jgarms.adventOfCode2022.day13;

import com.jgarms.adventOfCode2022.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day13 {

    public static void main(String... args) {
        Day13 day13 = new Day13();
        day13.partOne();
        day13.partTwo();
    }

    private void partOne() {
        Scanner s = Utils.getScanner(this, "day13.txt");
        int sum = sumOrderedPacketIndices(s);
        System.out.println("Part one: " + sum);
    }

    private void partTwo() {
        Scanner s = Utils.getScanner(this, "day13.txt");
        int decoderKey = computeDecoderKey(s);
        System.out.println("Part two: " + decoderKey);
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

    public static int computeDecoderKey(Scanner s) {
        List<ListPacket> packets = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.length() > 0) {
                packets.add(Parser.parse(line));
            }
        }
        ListPacket divider1 = Parser.parse("[[2]]");
        packets.add(divider1);
        ListPacket divider2 = Parser.parse("[[6]]");
        packets.add(divider2);
        Collections.sort(packets);
        int divider1Index = 0;
        int index = 1;
        for (ListPacket packet : packets) {
            if (divider1.compareTo(packet) == 0) {
                divider1Index = index;
            }
            else if (divider2.compareTo(packet) == 0) {
                assert divider1Index > 0;
                return index * divider1Index;
            }
            index++;
        }
        throw new IllegalStateException("Internal error: divider packets not found");
    }
}
