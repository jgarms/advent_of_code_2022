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
    }

    /**
     * Dijkstra, baby
     */
    public void calculateShortestPaths(Node startingNode) {
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        startingNode.distanceFromStart = 0;
        unsettledNodes.add(startingNode);

        while (unsettledNodes.size() > 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Node adjacentNode : currentNode.getAdjacentNodes()) {
                if (!settledNodes.contains(adjacentNode)) {
                    adjacentNode.distanceFromStart = currentNode.distanceFromStart + 1;
                    adjacentNode.shortestPathFromStart = new LinkedList<>(currentNode.shortestPathFromStart);
                    adjacentNode.shortestPathFromStart.add(currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
    }

    public void reset() {
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                nodes[x][y].reset();
            }
        }
    }

    public int getShortestDistanceFromElevation(int elevation) {
        int shortestDistance = Integer.MAX_VALUE;
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                Node node = nodes[x][y];
                if (node.elevation == elevation) {
                    reset();
                    calculateShortestPaths(node);
                    int distanceToEnd = end.distanceFromStart;
                    if (distanceToEnd < shortestDistance) {
                        shortestDistance = distanceToEnd;
                    }
                }
            }
        }
        return shortestDistance;
    }

    private static Node getLowestDistanceNode(Set<Node> nodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: nodes) {
            int nodeDistance = node.distanceFromStart;
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
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
