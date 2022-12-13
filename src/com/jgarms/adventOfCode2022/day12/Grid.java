package com.jgarms.adventOfCode2022.day12;

import java.util.*;

public class Grid {
    final int width, height;
    final Node[][] nodes;

    Node start, end;

    public Grid(List<String> input) {
        width = input.get(0).length();
        height = input.size();
        nodes = new Node[width][height];
        for (int y = 0; y< height; y++) {
            for (int x = 0; x< width; x++) {
                char c = input.get(y).charAt(x);
                int height = getElevation(c);
                Node node = new Node(x, y, height, this);
                if (c == 'S') {
                    start = node;
                } else if (c == 'E') {
                    end = node;
                }
                nodes[x][y] = node;
            }
        }
        if (start == null) {
            throw new IllegalArgumentException("No start node found");
        }
        if (end == null) {
            throw new IllegalArgumentException("No end node found");
        }
        calculateShortestPaths();
    }

    /**
     * Dijkstra, baby
     */
    public void calculateShortestPaths() {
        // from the start first
        Set<Node> settledNodes = new HashSet<>();
        NavigableSet<Node> unsettledNodes = new TreeSet<>();

        start.distanceFromStart = 0;
        unsettledNodes.add(start);

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.pollFirst();
            assert currentNode != null;
            for (Node adjacentNode : currentNode.getAdjacentNodes(false)) {
                if (!settledNodes.contains(adjacentNode)) {
                    unsettledNodes.remove(adjacentNode);
                    adjacentNode.distanceFromStart = currentNode.distanceFromStart + 1;
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }

        // Now reverse it
        settledNodes.clear();
        //noinspection ConstantValue
        assert unsettledNodes.size() == 0;

        end.distanceFromEnd = 0;
        unsettledNodes.add(end);

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.pollFirst();
            assert currentNode != null;
            for (Node adjacentNode : currentNode.getAdjacentNodes(true)) {
                if (!settledNodes.contains(adjacentNode)) {
                    unsettledNodes.remove(adjacentNode);
                    adjacentNode.distanceFromEnd = currentNode.distanceFromEnd + 1;
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
    }

    public int getShortestDistanceFromElevation(int elevation) {
        int shortestDistance = Integer.MAX_VALUE;
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                Node node = nodes[x][y];
                if (node.elevation == elevation) {
                    int distanceFromEnd = node.distanceFromEnd;
                    if (distanceFromEnd < shortestDistance) {
                        shortestDistance = distanceFromEnd;
                    }
                }
            }
        }
        return shortestDistance;
    }

    static int getElevation(char c) {
        if (c == 'S') {
            return 0;
        } else if (c == 'E') {
            return 25;
        }
        if (c < 'a' || c > 'z') {
            throw new IllegalArgumentException("Height cannot be computed for '" + c + "'");
        }
        return c - 'a';
    }


}
