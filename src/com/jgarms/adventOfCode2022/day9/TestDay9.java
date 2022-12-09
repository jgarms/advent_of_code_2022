package com.jgarms.adventOfCode2022.day9;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay9 {

    @Test
    void testSimpleMove() {
        Space head = new Space(0, 0);
        Space tail = new Space(0, 0);
        assertEquals(0, tail.getDistance(head));
        assertEquals(tail, tail.getNextSpace(head));

        head = new Space(1, 0);
        assertEquals(1, tail.getDistance(head));
        assertEquals(tail, tail.getNextSpace(head));

        head = new Space(1, 1);
        assertEquals(1, tail.getDistance(head));
        assertEquals(tail, tail.getNextSpace(head));

        head = new Space(2, 0);
        assertEquals(2, tail.getDistance(head));
        assertEquals(new Space(1, 0), tail.getNextSpace(head));

        head = new Space(1, 2);
        assertEquals(2, tail.getDistance(head));
        assertEquals(new Space(1, 1), tail.getNextSpace(head));
    }

    @Test
    void testParsing() {
        Scanner s = new Scanner(SAMPLE_INPUT);
        List<Move> moves = Day9.parseMoves(s);
        assertEquals(24, moves.size());
    }

    @Test
    public void testSample() {
        Scanner s = new Scanner(SAMPLE_INPUT);
        List<Move> moves = Day9.parseMoves(s);
        Rope rope = new Rope(2);
        for (Move move: moves) {
            rope.move(move);
        }
        int numVisited = rope.getNumVisitedSpaces();
        assertEquals(13, numVisited);
    }
    public static final String SAMPLE_INPUT = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2""";
}
