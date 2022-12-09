package com.jgarms.adventOfCode2022.day9;

public enum Move {
    RIGHT(1, 0), LEFT(-1, 0), UP(0, 1), DOWN(0, -1);
    final int dy, dx;

    Move(int dx, int dy) {
        this.dx = dx; this.dy = dy;
    }

    public static Move getMoveForChar(char c) {
        switch (c) {
            case 'R' -> {
                return RIGHT;
            }
            case 'L' -> {
                return LEFT;
            }
            case 'U' -> {
                return UP;
            }
            case 'D' -> {
                return DOWN;
            }
        }
        throw new IllegalArgumentException("Unknown char: " + c);
    }
}
