package com.jgarms.adventOfCode2022.day2;

import com.jgarms.adventOfCode2022.Utils;

import java.io.BufferedReader;


public class Day2 {

    public static void main(String... args) throws Exception {
        final Day2 day2 = new Day2();
        day2.part1();
        day2.part2();
    }

    private void part1() throws Exception {
        BufferedReader input = Utils.getInput(this, "day2.txt");
        int totalScore = 0;
        while (input.ready()) {
            String line = input.readLine();
            Shape theirs = Shape.fromChar(line.charAt(0));
            Shape mine = Shape.fromChar(line.charAt(2));
            Outcome outcome = Outcome.winLoseOrDraw(mine, theirs);
            int score = outcome.points;
            score += mine.points;
            //System.out.println(line + ": " + score);
            totalScore += score;
        }
        System.out.println("Part 1: " + totalScore);
    }

    private void part2() throws Exception {
        BufferedReader input = Utils.getInput(this, "day2.txt");
        int totalScore = 0;
        while (input.ready()) {
            String line = input.readLine();
            Shape theirs = Shape.fromChar(line.charAt(0));
            Outcome outcome = Outcome.fromChar(line.charAt(2));
            Shape mine = outcome.getShapeToPlay(theirs);
            int score = outcome.points;
            score += mine.points;
            //System.out.println(line + ": " + score);
            totalScore += score;
        }
        System.out.println("Part 2: " + totalScore);
    }

    private enum Outcome {
        WIN(6) {
            @Override
            Shape getShapeToPlay(Shape theirs) {
                return theirs.getShapeToLoseAgainst();
            }
        }, LOSE(0) {
            @Override
            Shape getShapeToPlay(Shape theirs) {
                return theirs.getShapeToWinAgainst();
            }
        }, DRAW(3) {
            @Override
            Shape getShapeToPlay(Shape theirs) {
                return theirs;
            }
        };
        public final int points;

        Outcome(final int points) {
            this.points = points;
        }

        abstract Shape getShapeToPlay(Shape theirs);

        static Outcome fromChar(final char c) {
            if (c == 'X') {
                return Outcome.LOSE;
            } else if (c == 'Y') {
                return Outcome.DRAW;
            } else {
                return Outcome.WIN;
            }
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
    }

    private enum Shape {
        ROCK(1) {
            @Override
            Shape getShapeToWinAgainst() {
                return SCISSORS;
            }

            @Override
            Shape getShapeToLoseAgainst() {
                return PAPER;
            }
        }, PAPER(2) {
            @Override
            Shape getShapeToWinAgainst() {
                return ROCK;
            }

            @Override
            Shape getShapeToLoseAgainst() {
                return SCISSORS;
            }
        }, SCISSORS(3) {
            @Override
            Shape getShapeToWinAgainst() {
                return PAPER;
            }

            @Override
            Shape getShapeToLoseAgainst() {
                return ROCK;
            }
        };

        public final int points;

        Shape(final int points) {
            this.points = points;
        }

        abstract Shape getShapeToWinAgainst();

        abstract Shape getShapeToLoseAgainst();

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
