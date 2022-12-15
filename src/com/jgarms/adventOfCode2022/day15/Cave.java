package com.jgarms.adventOfCode2022.day15;

import java.util.*;

public class Cave {
    List<Sensor> sensors = new ArrayList<>();

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
        sensors.add(sensor);
        updateMinMaxes(sensor);
    }

    private void updateMinMaxes(Sensor sensor) {
        int sensorMinX = sensor.getMinX();
        if (sensorMinX < minX) {
            minX = sensorMinX;
        }
        int sensorMaxX = sensor.getMaxX();
        if (sensorMaxX > maxX) {
            maxX = sensorMaxX;
        }
        int sensorMinY = sensor.getMinY();
        if (sensorMinY < minY) {
            minY = sensorMinY;
        }
        int sensorMaxY = sensor.getMaxY();
        if (sensorMaxY > maxY) {
            maxY = sensorMaxY;
        }
    }

    public int getNumForbiddenBeaconPositions(int y) {
        int num = 0;
        for (int x=minX; x<=maxX; x++) {
            boolean signalFound = false;
            for (Sensor sensor: sensors) {
                Content c = sensor.getContentAtPoint(new Point(x, y));
                if (c == Content.BEACON) {
                    signalFound = false;
                    break;
                }
                if (c == Content.SIGNAL) {
                    signalFound = true;
                }
            }
            if (signalFound) {
                num++;
            }
        }
        return num;
    }

    public Point getUndetectedPoint(int minX, int minY, int maxX, int maxY) {
        for (int i=0; i<sensors.size(); i++) {
            Sensor sensor = sensors.get(i);
            Set<Point> perimeter = sensor.getPerimeter();
            for (Point point: perimeter) {
                if (point.x() < minX || point.x() > maxX || point.y() < minY || point.y() > maxY) {
                    // Outside area
                    continue;
                }
                boolean found = false;
                for (int j=0; j<sensors.size(); j++) {
                    if (i == j) {
                        // No need to check the sensor from which this perimeter came
                        continue;
                    }
                    if (sensors.get(j).getContentAtPoint(point) != null) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    // Not in any other sensor
                    return point;
                }
            }
        }
        throw new IllegalStateException("No undetected point found");
    }
}
