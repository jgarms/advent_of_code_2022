package com.jgarms.adventOfCode2022.day18;

import com.jgarms.adventOfCode2022.Utils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay18 {

    @Test
    public void testParsing() {
        Cube cube = new Cube(new Scanner(SAMPLE_INPUT));
        assertEquals(13, cube.voxels.size());
        cube = new Cube(Utils.getScanner(this, "day18.txt"));
        assertEquals(2686, cube.voxels.size());
    }

    @Test
    public void testSurfaceArea() {
        Cube cube = new Cube(new Scanner(SAMPLE_INPUT));
        assertEquals(64, cube.getSurfaceArea());
        cube = new Cube(Utils.getScanner(this, "day18.txt"));
        assertEquals(4604, cube.getSurfaceArea());
    }

    @Test
    public void testExposedSurfaceArea() {
        Cube cube = new Cube(new Scanner(SAMPLE_INPUT));
        assertEquals(58, cube.getExposedSurfaceArea());
        cube = new Cube(Utils.getScanner(this, "day18.txt"));
        assertEquals(2604, cube.getExposedSurfaceArea());
    }

    public static final String SAMPLE_INPUT = """
            2,2,2
            1,2,2
            3,2,2
            2,1,2
            2,3,2
            2,2,1
            2,2,3
            2,2,4
            2,2,6
            1,2,5
            3,2,5
            2,1,5
            2,3,5""";
}
