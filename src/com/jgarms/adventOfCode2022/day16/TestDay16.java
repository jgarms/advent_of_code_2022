package com.jgarms.adventOfCode2022.day16;


import com.jgarms.adventOfCode2022.Utils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDay16 {

    @Test
    public void testParse() {
        Valve valve = new Valve("Valve AW has flow rate=0; tunnels lead to valves LG, TL");
        assertEquals("AW", valve.name);
        assertEquals(0, valve.flowRate);
        assertEquals(2, valve.children.size());
        assertTrue(valve.children.contains("LG"));
        assertTrue(valve.children.contains("TL"));
        valve = new Valve("Valve JJ has flow rate=21; tunnel leads to valve II");
        assertEquals("JJ", valve.name);
        assertEquals(21, valve.flowRate);
        assertEquals(1, valve.children.size());
        assertTrue(valve.children.contains("II"));

        Volcano volcano = new Volcano(new Scanner(SAMPLE_INPUT));
        assertEquals(10, volcano.valves.size());
        for (String valveName: volcano.valves.keySet()) {
            assertEquals(10, volcano.sourceToDestDistance.get(valveName).size());
        }
    }

    @Test
    public void testPartOneSample() {
        Volcano volcano = new Volcano(new Scanner(SAMPLE_INPUT));
        assertEquals(1651, volcano.getMaxPressureReleased(30));
    }

    @Test
    public void testPartOne() {
        Volcano volcano = new Volcano(Utils.getScanner(this, "day16.txt"));
        assertEquals(1789, volcano.getMaxPressureReleased(30));
    }

    public static final String SAMPLE_INPUT = """
            Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
            Valve BB has flow rate=13; tunnels lead to valves CC, AA
            Valve CC has flow rate=2; tunnels lead to valves DD, BB
            Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
            Valve EE has flow rate=3; tunnels lead to valves FF, DD
            Valve FF has flow rate=0; tunnels lead to valves EE, GG
            Valve GG has flow rate=0; tunnels lead to valves FF, HH
            Valve HH has flow rate=22; tunnel leads to valve GG
            Valve II has flow rate=0; tunnels lead to valves AA, JJ
            Valve JJ has flow rate=21; tunnel leads to valve II""";
}
