package com.jgarms.adventOfCode2022;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {
    static BufferedReader getInput(final String filename) {
        InputStream input = Day1.class.getResourceAsStream(filename);
        if (input == null) {
            throw new IllegalStateException("Could not open day1.txt");
        }
        return new BufferedReader(new InputStreamReader(input));
    }
}
