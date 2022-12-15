package com.jgarms.adventOfCode2022.day15;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sensor {

    static final Pattern PATTERN = Pattern.compile("Sensor at x=(.+), y=(.+): closest beacon is at x=(.+), y=(.+)");
    final Point location;
    final Point beaconLocation;
    final int radius;

    public Sensor(Point location, Point beaconLocation) {
        this.location = location;
        this.beaconLocation = beaconLocation;
        this.radius = location.getManhattanDistance(beaconLocation);
    }

    public Sensor(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Could not match line: " + line);
        }
        int x = Integer.parseInt(matcher.group(1));
        int y = Integer.parseInt(matcher.group(2));
        int beaconX = Integer.parseInt(matcher.group(3));
        int beaconY = Integer.parseInt(matcher.group(4));
        this.location = new Point(x, y);
        this.beaconLocation = new Point(beaconX, beaconY);
        this.radius = location.getManhattanDistance(beaconLocation);
    }

    public Content getContentAtPoint(Point point) {
        if (beaconLocation.equals(point)) {
            return Content.BEACON;
        }
        if (location.equals(point)) {
            return Content.SENSOR;
        }
        if (location.getManhattanDistance(point) <= radius) {
            return Content.SIGNAL;
        }
        return null;
    }

    public int getMinX() {
        return location.x() - radius;
    }

    public int getMaxX() {
        return location.x() + radius;
    }

    public int getMinY() {
        return location.y() - radius;
    }

    public int getMaxY() {
        return location.y() + radius;
    }

}
