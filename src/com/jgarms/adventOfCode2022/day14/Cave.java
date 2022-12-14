package com.jgarms.adventOfCode2022.day14;

import java.util.*;

public class Cave {
    final Point sandOrigin = new Point(500, 0);
    final Map<Point, Content> contents = new HashMap<>();
    int maxY = 0;
    int minX = Integer.MAX_VALUE;
    int maxX = 0;

    public Cave(Scanner scanner) {
        while (scanner.hasNext()) {
            RockStructure structure = new RockStructure(scanner.nextLine());
            addStructure(structure);
        }
    }

    private void addStructure(RockStructure structure) {
        for (Point rockPoint : structure.getAllPoints()) {
            contents.put(rockPoint, Content.ROCK);
            if (rockPoint.y() > maxY) {
                maxY = rockPoint.y();
            }
            if (rockPoint.x() < minX) {
                minX = rockPoint.x();
            }
            if (rockPoint.x() > maxX) {
                maxX = rockPoint.x();
            }
        }
    }

    /**
     * Add a unit of sand; returns true if the sand stops somewhere in the cave;
     * false if it goes into the abyss or if the cave is full.
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

    /**
     * By default, the cave is a bottomless abyss. This adds a bottom two points below the lowest
     * rock structure.
     */
    public void addBottom() {
        List<Point> vertices = new ArrayList<>();
        vertices.add(new Point(500 - (maxY + 2), maxY + 2));
        vertices.add(new Point(500 + (maxY + 2), maxY + 2));
        RockStructure structure = new RockStructure(vertices);
        addStructure(structure);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y=0; y<=maxY; y++) {
            sb.append(getStringForNum(y)).append(": ");
            for (int x = minX; x<=maxX; x++) {
                Content c = contents.get(new Point(x, y));
                if (c == null) {
                    sb.append('.');
                } else {
                    sb.append(c.c);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String getStringForNum(int num) {
        if (num < 10) {
            return "  " + num;
        }
        if (num < 100) {
            return " " + num;
        }
        else return "" + num;
    }
}
