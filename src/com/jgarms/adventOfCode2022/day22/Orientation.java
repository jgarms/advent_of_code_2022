package com.jgarms.adventOfCode2022.day22;

public enum Orientation {
    RIGHT(0), DOWN(1), LEFT(2), UP(3) ;

    final int value;

    Orientation(int value) {
        this.value = value;
    }

    Orientation turnLeft() {
        return switch(this) {
            case RIGHT -> UP;
            case DOWN -> RIGHT;
            case LEFT -> DOWN;
            case UP -> LEFT;
        };
    }

    Orientation turnRight() {
        return switch(this) {
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
            case UP -> RIGHT;
        };
    }

    Position getNextPosition(Position pos) {
        return switch(this) {
            case RIGHT -> new Position(pos.x() + 1, pos.y());
            case DOWN -> new Position(pos.x(), pos.y() + 1);
            case LEFT -> new Position(pos.x() - 1, pos.y());
            case UP -> new Position(pos.x(), pos.y() - 1);
        };
    }
}
