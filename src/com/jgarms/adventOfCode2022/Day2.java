package com.jgarms.adventOfCode2022;

import java.io.BufferedReader;


public class Day2 {

    public static void main(String... args) throws Exception {
        part1();
        part2();
    }

    private static Outcome winLoseOrDraw(Shape mine, Shape theirs) {
        if (mine == theirs) {
            return Outcome.DRAW;
        }
        if (mine == Shape.ROCK) {
            return theirs == Shape.SCISSORS ? Outcome.WIN : Outcome.LOSE;
        } else if (mine == Shape.PAPER) {
            return theirs == Shape.ROCK ? Outcome.WIN : Outcome.LOSE;
        } else {
            // SCISSORS
            return theirs == Shape.PAPER ? Outcome.WIN : Outcome.LOSE;
        }
    }

    private static Shape getShapeForOutcome(Shape theirs, Outcome outcome) {
        if (outcome == Outcome.DRAW) {
            return theirs;
        } else if (outcome == Outcome.WIN) {
            if (theirs == Shape.ROCK) {
                return Shape.PAPER;
            } else if (theirs == Shape.PAPER) {
                return Shape.SCISSORS;
            } else { // SCISSORS
                return Shape.ROCK;
            }
        } else { // LOSE
            if (theirs == Shape.ROCK) {
                return Shape.SCISSORS;
            } else if (theirs == Shape.PAPER) {
                return Shape.ROCK;
            } else { // SCISSORS
                return Shape.PAPER;
            }
        }
    }

    private static void part1() throws Exception {
        BufferedReader input = Utils.getInput("day2.txt");
        int totalScore = 0;
        while (input.ready()) {
            String line = input.readLine();
            Shape theirs = Shape.fromChar(line.charAt(0));
            Shape mine = Shape.fromChar(line.charAt(2));
            Outcome outcome = winLoseOrDraw(mine, theirs);
            int score = outcome.points;
            score += mine.points;
            //System.out.println(line + ": " + score);
            totalScore += score;
        }
        System.out.println("Part 1: " + totalScore);
    }

    private static void part2() throws Exception {
        BufferedReader input = Utils.getInput("day2.txt");
        int totalScore = 0;
        while (input.ready()) {
            String line = input.readLine();
            Shape theirs = Shape.fromChar(line.charAt(0));
            Outcome outcome = Outcome.fromChar(line.charAt(2));
            Shape mine = getShapeForOutcome(theirs, outcome);
            int score = outcome.points;
            score += mine.points;
            //System.out.println(line + ": " + score);
            totalScore += score;
        }
        System.out.println("Part 2: " + totalScore);
    }

    private enum Outcome {
        WIN(6), LOSE(0), DRAW(3);
        public final int points;

        Outcome(final int points) {
            this.points = points;
        }

        static Outcome fromChar(final char c) {
            if (c == 'X') {
                return Outcome.LOSE;
            } else if (c == 'Y') {
                return Outcome.DRAW;
            } else {
                return Outcome.WIN;
            }
        }
    }

    private enum Shape {
        ROCK(1), PAPER(2), SCISSORS(3);

        public final int points;

        Shape(final int points) {
            this.points = points;
        }

        static Shape fromChar(final char c) {
            switch (c) {
                case 'A':
                case 'X':
                    return ROCK;
                case 'B':
                case 'Y':
                    return PAPER;
                case 'C':
                case 'Z':
                    return SCISSORS;
                default:
                    throw new IllegalArgumentException("Unknown shape char: " + c);
            }
        }
    }
}
