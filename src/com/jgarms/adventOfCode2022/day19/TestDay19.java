package com.jgarms.adventOfCode2022.day19;

import com.jgarms.adventOfCode2022.Utils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay19 {

    @Test
    public void testParsing() {
        Blueprint blueprint = new Blueprint("Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.");
        assertEquals(1, blueprint.idNumber);
        assertEquals(4, blueprint.oreRobotOreCost);
        assertEquals(2, blueprint.clayRobotOreCost);
        assertEquals(3, blueprint.obsidianRobotOreCost);
        assertEquals(14, blueprint.obsidianRobotClayCost);
        assertEquals(2, blueprint.geodeRobotOreCost);
        assertEquals(7, blueprint.geodeRobotObsidianCost);

        Scanner scanner = new Scanner(SAMPLE_INPUT);
        while (scanner.hasNext()) {
            new Blueprint(scanner.nextLine());
        }
        scanner = Utils.getScanner(this, "day19.txt");
        while (scanner.hasNext()) {
            new Blueprint(scanner.nextLine());
        }
    }

    @Test
    public void testMaxGeodes() {
        Blueprint blueprint = new Blueprint("Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.");
        State startingState = new State(blueprint, 24);
        System.out.println("Calculating Blueprint 1...");
        long start = System.currentTimeMillis();
        assertEquals(9, startingState.getMaxGeodes());
        long total = System.currentTimeMillis() - start;
        System.out.println("took " + total + "ms");

        blueprint = new Blueprint("Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.");
        startingState = new State(blueprint, 24);
        System.out.println("Calculating Blueprint 2...");
        start = System.currentTimeMillis();
        assertEquals(12, startingState.getMaxGeodes());
        total = System.currentTimeMillis() - start;
        System.out.println("took " + total + "ms");
    }

    public static final String SAMPLE_INPUT = """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.""";
}
