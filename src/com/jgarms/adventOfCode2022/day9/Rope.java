package com.jgarms.adventOfCode2022.day9;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class Rope {
    @NotNull
    Space head, tail;
    final Set<Space> visitedSpaces = new HashSet<>();

    public Rope() {
        head = new Space(0, 0);
        tail = new Space(0, 0);
        visitedSpaces.add(tail);
    }

    public void move(Move move) {
        head = head.applyMove(move);
        tail = tail.getNextSpace(head);
        visitedSpaces.add(tail);
    }

    public int getNumVisitedSpaces() {
        return visitedSpaces.size();
    }
}
