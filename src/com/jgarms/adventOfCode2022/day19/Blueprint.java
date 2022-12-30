package com.jgarms.adventOfCode2022.day19;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Blueprint {
    final int idNumber;
    final int oreRobotOreCost;
    final int clayRobotOreCost;
    final int obsidianRobotOreCost;
    final int obsidianRobotClayCost;
    final int geodeRobotOreCost;
    final int geodeRobotObsidianCost;

    final Set<State> seen = new HashSet<>();
    int maxSoFar = 0;

    public Blueprint(String input) {
        Scanner scanner = new Scanner(input).useDelimiter("[^0-9]+");
        idNumber = scanner.nextInt();
        oreRobotOreCost = scanner.nextInt();
        clayRobotOreCost = scanner.nextInt();
        obsidianRobotOreCost = scanner.nextInt();
        obsidianRobotClayCost = scanner.nextInt();
        geodeRobotOreCost = scanner.nextInt();
        geodeRobotObsidianCost = scanner.nextInt();
    }
}
