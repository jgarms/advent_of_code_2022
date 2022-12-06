package com.jgarms.adventOfCode2022.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Test6 {
    private final Day6 day6 = new Day6();
    @Test
    public void testSamplesPartOne() {
        assertEquals(5, day6.getNumCharsUntilStartOfPacket("bvwbjplbgvbhsrlpgdmjqwftvncz", 0, 4));
        assertEquals(6, day6.getNumCharsUntilStartOfPacket("nppdvjthqldpwncqszvftbrmjlhg", 0, 4));
        assertEquals(10, day6.getNumCharsUntilStartOfPacket("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 0, 4));
        assertEquals(11, day6.getNumCharsUntilStartOfPacket("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 0, 4));
    }

    @Test
    public void testSamplesPartTwo() {
        assertEquals(19, day6.getNumCharsUntilStartOfPacket("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 0, 14));
        assertEquals(23, day6.getNumCharsUntilStartOfPacket("bvwbjplbgvbhsrlpgdmjqwftvncz", 0, 14));
        assertEquals(23, day6.getNumCharsUntilStartOfPacket("nppdvjthqldpwncqszvftbrmjlhg", 0, 14));
        assertEquals(29, day6.getNumCharsUntilStartOfPacket("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 0, 14));
        assertEquals(26, day6.getNumCharsUntilStartOfPacket("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 0, 14));
    }

    @Test
    public void testUniqueness() {
        assertTrue(day6.containsAllUniqueChars("jpqm", 0, 4));
        assertFalse(day6.containsAllUniqueChars("mjqj", 0, 4));
        assertTrue(day6.containsAllUniqueChars("mjqjjpqm", 4, 4));
    }
}
