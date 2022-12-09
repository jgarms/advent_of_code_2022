package com.jgarms.adventOfCode2022.day9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rope {
    final int size;
    final List<Space> knots = new ArrayList<>();
    final Set<Space> visitedSpaces = new HashSet<>();

    public Rope(int size) {
        this.size = size;
        for (int i=0; i<size; i++) {
            knots.add(new Space(0, 0));
        }
        visitedSpaces.add(knots.get(size - 1));
    }

    public void move(Move move) {
        Space newHead = knots.get(0).applyMove(move);
        knots.set(0, newHead);
        for (int i=1; i<size; i++) {
            Space newKnot = knots.get(i).getNextSpace(knots.get(i-1));
            knots.set(i, newKnot);
        }
        visitedSpaces.add(knots.get(size - 1));
    }

    public int getNumVisitedSpaces() {
        return visitedSpaces.size();
    }
}
