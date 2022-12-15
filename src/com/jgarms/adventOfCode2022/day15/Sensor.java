package com.jgarms.adventOfCode2022.day15;

import java.util.HashSet;
import java.util.Set;
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

    /**
     * Returns the set of points one greater than the manhattan distance from this sensor.
     */
    Set<Point> getPerimeter() {
        Set<Point> points = new HashSet<>();

        // take the four corners and rotate until the left corner is directly above the middle
        Point leftPoint = new Point(location.x() - radius - 1, location.y());
        Point rightPoint = new Point(location.x() + radius + 1, location.y());
        Point topPoint = new Point(location.x(), location.y() - radius - 1);
        Point bottomPoint = new Point(location.x(), location.y() + radius + 1);
        while (leftPoint.x() <= location.x()) {
            points.add(leftPoint);
            points.add(rightPoint);
            points.add(topPoint);
            points.add(bottomPoint);

            leftPoint = new Point(leftPoint.x() + 1, leftPoint.y() - 1);
            rightPoint = new Point(rightPoint.x() - 1, rightPoint.y() + 1);
            topPoint = new Point(topPoint.x() + 1, topPoint.y() + 1);
            bottomPoint = new Point(bottomPoint.x() - 1, bottomPoint.y() - 1);
        }

        return points;
    }

    @SuppressWarnings("unused")
    String getPerimeterDisplay() {
        Set<Point> perimeter = getPerimeter();
        StringBuilder sb = new StringBuilder();
        for (int y=getMinY() - 1; y<=getMaxY() + 1; y++) {
            for (int x=getMinX() - 1; x<=getMaxX() + 1; x++) {
                Point point = new Point(x, y);
                if (point.equals(location)) {
                    sb.append("S");
                } else if (point.equals(beaconLocation)) {
                    sb.append("B");
                } else if (perimeter.contains(point)) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
