package com.jgarms.adventOfCode2022.day5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Move {

    private static final Pattern PATTERN = Pattern.compile("move ([0-9]+) from ([0-9]+) to ([0-9]+)");
    public final int numCrates;
    public final int sourceCrate;
    public final int destinationCrate;
    public Move(final String input) {
        Matcher matcher = PATTERN.matcher(input);
        boolean found = matcher.find();
        assert found;
        numCrates = Integer.parseInt(matcher.group(1));
        sourceCrate = Integer.parseInt(matcher.group(2));
        destinationCrate = Integer.parseInt(matcher.group(3));
    }

}
