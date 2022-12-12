package com.jgarms.adventOfCode2022.day12;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Node implements Comparable<Node> {
    public final int x, y, elevation;

    public final Grid grid;

    int distanceFromStart = Integer.MAX_VALUE;
    List<Node> shortestPathFromStart = new LinkedList<>();

    public Node(int x, int y, int elevation, Grid grid) {
        this.x = x;
        this.y = y;
        this.elevation = elevation;
        this.grid = grid;
    }

    /**
     * Gets all accessible adjacent nodes. These will all be distance of 1.
     */
    public List<Node> getAdjacentNodes() {
        List<Node> nodes = new ArrayList<>();

        addIfReachable(nodes, x-1, y);
        addIfReachable(nodes,x+1, y);
        addIfReachable(nodes, x, y-1);
        addIfReachable(nodes, x, y+1);

        return nodes;
    }

    private void addIfReachable(List<Node> nodes, int x, int y) {
        // Don't add ourselves
        if (this.x == x && this.y == y) {
            throw new IllegalArgumentException("Attempt to add self to node list");
        }
        // Don't walk off the grid
        if (x < 0 || x >= grid.width || y < 0 || y >= grid.height) {
            return;
        }
        Node candidate = grid.nodes[x][y];
        if (candidate.elevation <= elevation + 1) {
            nodes.add(candidate);
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", elevation=" + elevation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(@NotNull Node o) {
        return Integer.compare(distanceFromStart, o.distanceFromStart);
    }
}
