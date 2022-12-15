package com.jgarms.adventOfCode2022.day15;

public record Point(int x, int y) {

    public int getManhattanDistance(Point otherPoint) {
        // Not quite chebyshev, baby
        return Math.abs(otherPoint.x() - x()) + Math.abs(otherPoint.y() - y());
    }
}
