package com.jgarms.adventOfCode2022.day6;

import com.jgarms.adventOfCode2022.Utils;

import java.util.HashSet;
import java.util.Set;

public class Day6 {

    public static void main(String... args) throws Exception {
        Day6 day6 = new Day6();
        day6.partOne();
        day6.partTwo();
    }

    private void partOne() throws Exception {
        String input = Utils.getInput(this, "day6.txt").readLine();
        int numChars = getNumCharsUntilStartOfPacket(input, 0, 4);
        System.out.println("Part 1: " + numChars);
    }

    private void partTwo() throws Exception {
        String input = Utils.getInput(this, "day6.txt").readLine();
        int numChars = getNumCharsUntilStartOfPacket(input, 0, 14);
        System.out.println("Part 2: " + numChars);
    }

    int getNumCharsUntilStartOfPacket(String data, int startIndex, int numPacketChars) {
        for (int index = startIndex; index < data.length() - numPacketChars; index++) {
            if (containsAllUniqueChars(data, index, numPacketChars)) {
                return index + numPacketChars;
            }
        }
        return -1;
    }

    boolean containsAllUniqueChars(String data, int startIndex, int length) {
        if (startIndex + length > data.length()) {
            throw new IllegalArgumentException("Walked off the end. start: " + startIndex + ", length: " + length);
        }
        Set<Character> set = new HashSet<>();
        for (int index = startIndex; index < startIndex + length; index++) {
            set.add(data.charAt(index));
        }
        return set.size() == length;
    }
}
