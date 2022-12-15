package com.jgarms.adventOfCode2022.day15;

public enum Content {
    BEACON('B'), SENSOR('S'), SIGNAL('#');

    final char c;

    Content(char c) {
        this.c = c;
    }
}
