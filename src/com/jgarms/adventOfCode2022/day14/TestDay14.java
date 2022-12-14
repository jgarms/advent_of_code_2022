package com.jgarms.adventOfCode2022.day14;

import com.jgarms.adventOfCode2022.Utils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestDay14 {

    @Test
    public void testParseStructure() {
        RockStructure rockStructure = new RockStructure("498,4 -> 498,6 -> 496,6");
        assertEquals(3, rockStructure.vertices.size());
        assertEquals(new Point(498, 4), rockStructure.vertices.get(0));
        Set<Point> allPoints = rockStructure.getAllPoints();
        assertEquals(5, allPoints.size());
        assertTrue(allPoints.contains(new Point(497, 6)));
        rockStructure = new RockStructure("503,4 -> 502,4 -> 502,9 -> 494,9");
        assertEquals(15, rockStructure.getAllPoints().size());
    }

    @Test
    public void testParseCave() {
        Cave cave = new Cave(new Scanner(SAMPLE_INPUT));
        assertEquals(20, cave.contents.size());
        assertEquals(Content.ROCK, cave.contents.get(new Point(497, 6)));
        assertNull(cave.contents.get(new Point(494, 3)));
        cave = new Cave(Utils.getScanner(this, "day14.txt"));
        assertEquals(572, cave.contents.size());
    }

    @Test
    public void testAddingSand() {
        Cave cave = new Cave(new Scanner(SAMPLE_INPUT));
        assertTrue(cave.addSand());
        assertEquals(21, cave.contents.size());
        assertEquals(Content.SAND, cave.contents.get(new Point(500, 8)));

        cave = new Cave(new Scanner(SAMPLE_INPUT));
        int numUnits = 0;
        while (cave.addSand()) {
            numUnits++;
        }
        assertEquals(24, numUnits);
    }

    @Test
    public void testPartOne() {
        Cave cave = new Cave(Utils.getScanner(this, "day14.txt"));
        int numUnits = 0;
        while (cave.addSand()) {
            numUnits++;
        }
        assertEquals(655, numUnits);
    }

    public static final String SAMPLE_INPUT = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9""";
}
