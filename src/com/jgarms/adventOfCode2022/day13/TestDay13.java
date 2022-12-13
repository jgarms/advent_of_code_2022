package com.jgarms.adventOfCode2022.day13;

import com.jgarms.adventOfCode2022.Utils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TestDay13 {

    @Test
    public void testSampleParsing() {
        Scanner s = new Scanner(SAMPLE_INPUT);
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.length() == 0) {
                continue;
            }
            ListPacket listPacket = Parser.parse(line);
            assertEquals(line, listPacket.toString());
        }
    }

    @Test
    public void testFullParsing() {
        Scanner s = Utils.getScanner(this, "day13.txt");
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.length() == 0) {
                continue;
            }
            ListPacket listPacket = Parser.parse(line);
            assertEquals(line, listPacket.toString());
        }
    }

    @Test
    public void testSampleComparisons() {
        ListPacket left = Parser.parse("[1,1,3,1,1]");
        ListPacket right = Parser.parse("[1,1,5,1,1]");
        assertEquals(-1, ListPacket.compare(left, right));
        assertEquals(-1, left.compareTo(right));

        left = Parser.parse("[[1],[2,3,4]]");
        right = Parser.parse("[[1],4]");
        assertEquals(-1, left.compareTo(right));

        left = Parser.parse("[9]");
        right = Parser.parse("[[8,7,6]]");
        assertEquals(1, left.compareTo(right));

        left = Parser.parse("[[4,4],4,4]");
        right = Parser.parse("[[4,4],4,4,4]");
        assertEquals(-1, left.compareTo(right));

        left = Parser.parse("[7,7,7,7]");
        right = Parser.parse("[7,7,7]");
        assertEquals(1, left.compareTo(right));

        left = Parser.parse("[]");
        right = Parser.parse("[3]");
        assertEquals(-1, left.compareTo(right));

        left = Parser.parse("[[[]]]");
        right = Parser.parse("[[]]");
        assertEquals(1, left.compareTo(right));

        left = Parser.parse("[1,[2,[3,[4,[5,6,7]]]],8,9]");
        right = Parser.parse("[1,[2,[3,[4,[5,6,0]]]],8,9]");
        assertEquals(1, left.compareTo(right));
    }

    @Test
    public void testIndexSum() {
        int sum = Day13.sumOrderedPacketIndices(new Scanner(SAMPLE_INPUT));
        assertEquals(13, sum);
    }

    public static final String SAMPLE_INPUT = """
            [1,1,3,1,1]
            [1,1,5,1,1]
                        
            [[1],[2,3,4]]
            [[1],4]
                        
            [9]
            [[8,7,6]]
                        
            [[4,4],4,4]
            [[4,4],4,4,4]
                        
            [7,7,7,7]
            [7,7,7]
                        
            []
            [3]
                        
            [[[]]]
            [[]]
                        
            [1,[2,[3,[4,[5,6,7]]]],8,9]
            [1,[2,[3,[4,[5,6,0]]]],8,9]""";
}
