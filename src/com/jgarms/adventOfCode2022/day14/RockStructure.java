package com.jgarms.adventOfCode2022.day14;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class RockStructure {
    final List<Point> vertices = new ArrayList<>();

    public RockStructure(String input) {
        String[] pointPairStrings = input.split(" -> "); // "e.g. 498,4"
        assert pointPairStrings.length > 0;
        for (String pointPair: pointPairStrings) {
            String[] pointPairArray = pointPair.split(",");
            assert pointPairArray.length == 2;
            int x = Integer.parseInt(pointPairArray[0]);
            int y = Integer.parseInt(pointPairArray[1]);
            vertices.add(new Point(x, y));
        }
    }

    public RockStructure(Collection<Point> vertices) {
        this.vertices.addAll(vertices);
    }

    public Set<Point> getAllPoints() {
        Set<Point> points = new HashSet<>();
        Point previous = vertices.get(0);
        points.add(previous);
        for (int i=1; i<vertices.size(); i++) { // starting at 1 since we already pulled the head
            Point current = vertices.get(i);
            addAllPointsBetweenVertices(points, previous, current);
            previous = current;
        }
        return points;
    }

    private static void addAllPointsBetweenVertices(Set<Point> set, Point a, Point b) {
        if (a.x() != b.x()) {
            // Horizontal line
            assert a.y() == b.y();
            for (int xIndex = min(a.x(), b.x()); xIndex <= max(a.x(), b.x()); xIndex++) {
                set.add(new Point(xIndex, a.y()));
            }
        } else {
            // Vertical line
            assert a.y() != b.y();
            for (int yIndex = min(a.y(), b.y()); yIndex <= max(a.y(), b.y()); yIndex++) {
                set.add(new Point(a.x(), yIndex));
            }
        }
    }

}
