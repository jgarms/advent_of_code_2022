package com.jgarms.adventOfCode2022.day14;

public enum Content {
    ROCK('#'), SAND('o');

    final char c;

    Content(char c) {
        this.c = c;
    }
}
