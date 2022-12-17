package com.jgarms.adventOfCode2022.day16;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Valve {
    private static final Pattern PATTERN = Pattern.compile("Valve (.+) has flow rate=(.+); tunnel.? lead.? to valve.? (.+)$");
    public final String name;
    public final int flowRate;
    Set<String> children = new HashSet<>();

    public Valve(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (matcher.matches()) {
            name = matcher.group(1);
            flowRate = Integer.parseInt(matcher.group(2));
            String[] childArray = matcher.group(3).split(", ");
            children.addAll(Arrays.asList(childArray));
        } else {
            throw new IllegalArgumentException("Could not parse " + line);
        }
    }
}
