package com.jgarms.adventOfCode2022.day4;

import java.util.Scanner;

public class ClearingAssignment {

    final int firstLow, firstHigh, secondLow, secondHigh;

    /**
     * Expects input in the form aa-bb,cc-dd
     */
    public ClearingAssignment(final String line) {
        Scanner s = new Scanner(line);
        s.useDelimiter("[-,]");
        firstLow = s.nextInt();
        firstHigh = s.nextInt();
        secondLow = s.nextInt();
        secondHigh = s.nextInt();
    }

    /**
     * Does one range fully contain the other?
     */
    public boolean isRedundant() {
        // NB: No code golfing here
        // Does the first range contain the second range?
        if (firstLow <= secondLow && firstHigh >= secondHigh) {
            return true;
        }
        // Does the second range contain the first?
        if (secondLow <= firstLow && secondHigh >= firstHigh) {
            return true;
        }
        return false;
    }

    /**
     * Is there any overlap at all?
     */
    public boolean doesOverlap() {
        // Easier to reason about no overlap: does the first range start after the second ends,
        // or the second start after the first ends?
        boolean doesNotOverlap = firstLow > secondHigh || secondLow > firstHigh;
        return !doesNotOverlap;
    }
}
