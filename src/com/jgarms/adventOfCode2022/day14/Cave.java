package com.jgarms.adventOfCode2022.day14;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cave {
    final Point sandOrigin = new Point(500, 0);
    final Map<Point, Content> contents = new HashMap<>();
    int maxY = 0;

    public Cave(Scanner scanner) {
        while (scanner.hasNext()) {
            RockStructure rockStructure = new RockStructure(scanner.nextLine());
            for (Point rockPoint : rockStructure.getAllPoints()) {
                contents.put(rockPoint, Content.ROCK);
                if (rockPoint.y() > maxY) {
                    maxY = rockPoint.y();
                }
            }
        }
    }

    /**
     * Add a unit of sand; returns true if the sand stops somewhere in the cave;
     * false if it goes into the abyss or it's full.
     */
    public boolean addSand() {
        int x = sandOrigin.x();
        int y = sandOrigin.y();
        if (contents.containsKey(new Point(x, y))) {
            // already full
            return false;
        }
        while (y <= maxY) {
            if (!contents.containsKey(new Point(x, y+1))) {
                // we can go down
                y++;
            } else if (!contents.containsKey(new Point(x-1, y+1))) {
                // we can go diagonally left
                x--;
                y++;
            } else if (!contents.containsKey(new Point(x+1, y+1))) {
                // we can go diagonally right
                x++;
                y++;
            } else {
                // We're stuck
                contents.put(new Point(x, y), Content.SAND);
                return true;
            }
        }
        return false;
    }
}
