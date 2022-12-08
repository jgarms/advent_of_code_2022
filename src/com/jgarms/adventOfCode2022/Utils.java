package com.jgarms.adventOfCode2022;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static BufferedReader getInput(final Object source, final String filename) {
        InputStream input = source.getClass().getResourceAsStream(filename);
        if (input == null) {
            throw new IllegalStateException("Could not open day1.txt");
        }
        return new BufferedReader(new InputStreamReader(input));
    }

    public static List<String> getInputAsList(final Object source, final String filename) {
        Scanner s = new Scanner(source.getClass().getResourceAsStream(filename));
        List<String> list = new ArrayList<>();
        while (s.hasNext()) {
            list.add(s.nextLine());
        }
        return list;
    }
}
