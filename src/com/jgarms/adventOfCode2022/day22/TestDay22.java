package com.jgarms.adventOfCode2022.day22;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay22 {

    @Test
    public void testParsing() {
        Grove grove = new Grove(new Scanner(SAMPLE_INPUT));
        assertEquals(96, grove.contents.size());
        assertEquals(13, grove.instructions.size());
    }

    @Test
    public void testExecution() {
        Grove grove = new Grove(new Scanner(SAMPLE_INPUT));
        int password = grove.execute();
        assertEquals(6032, password);
    }

    public static final String SAMPLE_INPUT = """
                    ...#
                    .#..
                    #...
                    ....
            ...#.......#
            ........#...
            ..#....#....
            ..........#.
                    ...#....
                    .....#..
                    .#......
                    ......#.
                        
            10R5L5R10L4R5L5""";
}
