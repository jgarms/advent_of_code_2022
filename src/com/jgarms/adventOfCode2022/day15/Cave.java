package com.jgarms.adventOfCode2022.day15;

import java.util.*;

public class Cave {
    final Map<Point, Content> contents = new HashMap<>();

    int minX = Integer.MAX_VALUE;
    int maxX = 0;
    int minY = Integer.MAX_VALUE;
    int maxY = 0;

    public Cave(Scanner scanner) {
        while (scanner.hasNext()) {
            addSensor(new Sensor(scanner.nextLine()));
        }
    }

    public void addSensor(Sensor sensor) {
        System.out.println("Adding sensor with manhattan distance of " + sensor.radius  + "...");
        contents.put(sensor.location, Content.SENSOR);
        contents.put(sensor.beaconLocation, Content.BEACON);
        for (Point point: sensor.getAllCoveredPoints()) {
            updateMinMaxes(point);
            if (!contents.containsKey(point)) {
                contents.put(point, Content.SIGNAL);
            }
        }
        System.out.println("Done adding sensor.");
    }

    private void updateMinMaxes(Point point) {
        int x = point.x();
        int y = point.y();
        if (x < minX) {
            minX = x;
        }
        if (x > maxX) {
            maxX = x;
        }
        if (y < minY) {
            minY = y;
        }
        if (y > maxY) {
            maxY = y;
        }
    }

    public int getNumForbiddenBeaconPositions(int y) {
        int num = 0;
        for (int x=minX; x<=maxX; x++) {
            Content c = contents.get(new Point(x, y));
            if (c == Content.SENSOR || c == Content.SIGNAL) {
                num++;
            }
        }
        return num;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y=minY; y<=maxY; y++) {
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

    private String getStringForNum(int num) {
        int biggestNum = Math.max(maxX, maxY);
        int numDigits = Integer.toString(biggestNum).length();
        return String.format("%1$" + numDigits + "s", num);
    }
}
