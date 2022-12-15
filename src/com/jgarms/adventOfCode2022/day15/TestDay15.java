package com.jgarms.adventOfCode2022.day15;

import com.jgarms.adventOfCode2022.Utils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay15 {

    @Test
    public void testManhattanDistance() {
        Point p = new Point(2, 18);
        Point o = new Point(-2, 15);
        assertEquals(7, p.getManhattanDistance(o));
    }

    @Test
    public void testSampleInput() {
        Sensor sensor = new Sensor("Sensor at x=13, y=2: closest beacon is at x=15, y=3");
        assertEquals(new Point(13, 2), sensor.location);
        assertEquals(new Point(15, 3), sensor.beaconLocation);
        sensor = new Sensor("Sensor at x=2, y=18: closest beacon is at x=-2, y=15");
        assertEquals(new Point(2, 18), sensor.location);
        assertEquals(new Point(-2, 15), sensor.beaconLocation);

        Cave cave = new Cave(new Scanner(SAMPLE_INPUT));
        assertEquals(26, cave.getNumForbiddenBeaconPositions(10));
    }

    @Test
    public void testPartOne() {
        Cave cave = new Cave(Utils.getScanner(this, "day15.txt"));
        assertEquals(5299855, cave.getNumForbiddenBeaconPositions(2000000));
    }

    public static final String SAMPLE_INPUT = """
            Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            Sensor at x=9, y=16: closest beacon is at x=10, y=16
            Sensor at x=13, y=2: closest beacon is at x=15, y=3
            Sensor at x=12, y=14: closest beacon is at x=10, y=16
            Sensor at x=10, y=20: closest beacon is at x=10, y=16
            Sensor at x=14, y=17: closest beacon is at x=10, y=16
            Sensor at x=8, y=7: closest beacon is at x=2, y=10
            Sensor at x=2, y=0: closest beacon is at x=2, y=10
            Sensor at x=0, y=11: closest beacon is at x=2, y=10
            Sensor at x=20, y=14: closest beacon is at x=25, y=17
            Sensor at x=17, y=20: closest beacon is at x=21, y=22
            Sensor at x=16, y=7: closest beacon is at x=15, y=3
            Sensor at x=14, y=3: closest beacon is at x=15, y=3
            Sensor at x=20, y=1: closest beacon is at x=15, y=3""";
}
