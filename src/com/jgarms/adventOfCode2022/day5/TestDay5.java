package com.jgarms.adventOfCode2022.day5;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay5 {

    private final Day5 day5 = new Day5();

    @Test
    void testCrateParsing() {
        Crates crates = day5.getCrates(new Scanner(Day5.SAMPLE_INPUT));
        assertEquals(3, crates.getNumCrates());
    }

    @Test
    void testMoveParsing() {
        Move move = new Move("move 1 from 2 to 1");
        assertEquals(1, move.numCrates);
        assertEquals(2, move.sourceCrate);
        assertEquals(1, move.destinationCrate);
    }

    @Test
    void test9000() {
        Scanner s = new Scanner(Day5.SAMPLE_INPUT);
        Crates crates = day5.getCrates(s);
        List<Move> moves = day5.getMoves(s);
        assertEquals(3, crates.getNumCrates());
        assertEquals(4, moves.size());
        assertEquals("NDP", crates.peek());
        for (Move move : moves) {
            crates.applyMove9000(move);
        }
        assertEquals("CMZ", crates.peek());
    }

    @Test
    void test9001() {
        Scanner s = new Scanner(Day5.SAMPLE_INPUT);
        Crates crates = day5.getCrates(s);
        List<Move> moves = day5.getMoves(s);
        assertEquals(3, crates.getNumCrates());
        assertEquals(4, moves.size());
        assertEquals("NDP", crates.peek());
        for (Move move : moves) {
            crates.applyMove9001(move);
        }
        assertEquals("MCD", crates.peek());
    }
}
