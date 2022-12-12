package com.jgarms.adventOfCode2022.day12;

import com.jgarms.adventOfCode2022.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay12 {

    @Test
    public void testGridParsing() {
        Grid grid = new Grid(Utils.getStringAsList(SAMPLE_INPUT));
        assertEquals(5, grid.height);
        assertEquals(8, grid.width);
        assertEquals(new Node(0, 0, 0, grid), grid.start);
        assertEquals(5, grid.end.x);
        assertEquals(2, grid.end.y);
        assertEquals(17, grid.nodes[3][1].elevation);
    }

    @Test
    public void testAdjacentNodes() {
        Grid grid = new Grid(Utils.getStringAsList(SAMPLE_INPUT));
        Node start = grid.start;
        List<Node> adjacentNodes = start.getAdjacentNodes();
        assertEquals(2, adjacentNodes.size());
        Node end = grid.end;
        assertEquals(4, end.getAdjacentNodes().size());
        Node mid = grid.nodes[2][1];
        assertEquals(3, mid.getAdjacentNodes().size());
    }

    @Test
    public void testElevation() {
        assertEquals(0, Grid.getElevation('S'));
        assertEquals(0, Grid.getElevation('a'));
        assertEquals(25, Grid.getElevation('E'));
        assertEquals(25, Grid.getElevation('z'));
        assertEquals(17, Grid.getElevation('r'));
    }

    @Test
    public void testDijkstra() {
        Grid grid = new Grid(Utils.getStringAsList(SAMPLE_INPUT));
        grid.calculateShortestPaths(grid.start);
        assertEquals(31, grid.end.distanceFromStart);
    }

    @Test
    public void testPartOne() {
        Grid grid = new Grid(Utils.getInputAsList(this, "day12.txt"));
        grid.calculateShortestPaths(grid.start);
        assertEquals(447, grid.end.distanceFromStart);
    }

    public static final String SAMPLE_INPUT = """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi""";
}
