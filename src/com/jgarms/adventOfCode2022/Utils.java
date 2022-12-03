package com.jgarms.adventOfCode2022;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {
    public static BufferedReader getInput(final Object source, final String filename) {
        InputStream input = source.getClass().getResourceAsStream(filename);
        if (input == null) {
            throw new IllegalStateException("Could not open day1.txt");
        }
        return new BufferedReader(new InputStreamReader(input));
    }
}
