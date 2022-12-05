package com.jgarms.adventOfCode2022.day5;

import com.jgarms.adventOfCode2022.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Day5 {
    
    public static void main(String... args) {
        Day5 day5 = new Day5();
        day5.partOne();
        day5.partTwo();
    }
    
    void partOne() {
        Scanner s = new Scanner(Utils.getInput(this, "day5.txt"));
        Crates crates = getCrates(s);
        List<Move> moves = getMoves(s);
        for (Move move : moves) {
            crates.applyMove9000(move);
        }
        System.out.println("Part one: " + crates.peek());
    }

    void partTwo() {
        Scanner s = new Scanner(Utils.getInput(this, "day5.txt"));
        Crates crates = getCrates(s);
        List<Move> moves = getMoves(s);
        for (Move move : moves) {
            crates.applyMove9001(move);
        }
        System.out.println("Part two: " + crates.peek());
    }

    Crates getCrates(Scanner s) {
        // Given our input, pull out the lines that contain the crates and reverse them
        Stack<String> stack = new Stack<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.startsWith(" 1 ")) {
                // Needless text that numbers the stacks of crates. We're done reading input, and
                // can ignore this line and the blank line that follows.
                s.nextLine();
                break;
            }
            stack.push(line);
        }
        return new Crates(stack);
    }

    List<Move> getMoves(Scanner s) {
        List<Move> moves = new ArrayList<>();
        while (s.hasNext()) {
            moves.add(new Move(s.nextLine()));
        }
        return moves;
    }

    static final String SAMPLE_INPUT = """
                [D]   \s
            [N] [C]   \s
            [Z] [M] [P]
             1   2   3\s
                        
            move 1 from 2 to 1
            move 3 from 1 to 3
            move 2 from 2 to 1
            move 1 from 1 to 2""";
}
