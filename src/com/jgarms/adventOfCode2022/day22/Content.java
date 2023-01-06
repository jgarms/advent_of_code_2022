package com.jgarms.adventOfCode2022.day22;

public enum Content {
    OPEN('.'), FORCE_FIELD('#');

    final char c;

    Content(char c) {
        this.c = c;
    }

    public static Content getContent(char c) {
        return switch(c) {
            case '.' -> OPEN;
            case '#' -> FORCE_FIELD;
            default -> null;
        };
    }
}
