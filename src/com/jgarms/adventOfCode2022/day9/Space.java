package com.jgarms.adventOfCode2022.day9;

import java.util.Objects;

public class Space {
    final int x, y;

    public Space(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDistance(Space otherSpace) {
        // Chebyshev, baby
        return Math.max(Math.abs(otherSpace.x - x), Math.abs(otherSpace.y - y));
    }

    public Space applyMove(Move move) {
        int newX = x + move.dx;
        int newY = y + move.dy;
        return new Space(newX, newY);
    }

    public Space getNextSpace(Space head) {
        int distance = getDistance(head);
        if (distance <= 1) {
            // spaces are touching, don't move
            return this;
        }

        int dx = head.x - x;
        int newX;
        if (dx > 0) {
            newX = x + 1;
        } else if (dx < 0) {
            newX = x - 1;
        } else {
            newX = x;
        }

        int dy = head.y - y;
        int newY;
        if (dy > 0) {
            newY = y + 1;
        } else if (dy < 0) {
            newY = y - 1;
        } else {
            newY = y;
        }
        return new Space(newX, newY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space = (Space) o;
        return x == space.x && y == space.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "[" + x + "][" + y + "]";
    }
}
