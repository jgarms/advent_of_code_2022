package com.jgarms.adventOfCode2022.day9;

import com.jgarms.adventOfCode2022.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9 {

    public static void main(String... args) {
        Day9 day9 = new Day9();
        day9.partOne();
        day9.partTwo();
    }

    private void partOne() {
        Scanner s = Utils.getScanner(this, "day9.txt");
        List<Move> moves = parseMoves(s);
        Rope rope = new Rope(2);
        for (Move move: moves) {
            rope.move(move);
        }
        int numVisited = rope.getNumVisitedSpaces();
        System.out.println("Part one: " + numVisited);
    }

    private void partTwo() {
        Scanner s = Utils.getScanner(this, "day9.txt");
        List<Move> moves = parseMoves(s);
        Rope rope = new Rope(10);
        for (Move move: moves) {
            rope.move(move);
        }
        int numVisited = rope.getNumVisitedSpaces();
        System.out.println("Part two: " + numVisited);
    }

    public static List<Move> parseMoves(Scanner s) {
        List<Move> moves = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            Move move = Move.getMoveForChar(line.charAt(0));
            int numMoves = Integer.parseInt(line.substring(2));
            for (int i=0; i<numMoves; i++) {
                moves.add(move);
            }
        }
        return moves;
    }
}

