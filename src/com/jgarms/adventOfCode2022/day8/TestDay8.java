package com.jgarms.adventOfCode2022.day8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TestDay8 {

    @Test
    void testGroveCreation() {
        Day8.Grove grove = new Day8.Grove(getInputAsList());
        assertEquals(5, grove.width);
        assertEquals(5, grove.height);
        assertEquals(3, grove.trees[0][0]);
        assertEquals(4, grove.trees[3][3]);
    }

    @Test
    void testTreeVisibility() {
        Day8.Grove grove = new Day8.Grove(getInputAsList());
        assertTrue(grove.isTreeVisible(0, 0));
        assertTrue(grove.isTreeVisible(1, 1));
        assertFalse(grove.isTreeVisible(3, 1));
    }

    @Test
    void testGroveVisibility() {
        Day8.Grove grove = new Day8.Grove(getInputAsList());
        assertEquals(21, grove.countVisibleTrees());
    }

    @Test
    void testViewScore() {
        Day8.Grove grove = new Day8.Grove(getInputAsList());
        assertEquals(0, grove.calculateViewScore(0, 0));
        assertEquals(4, grove.calculateViewScore(2, 1));
        assertEquals(8, grove.calculateViewScore(2, 3));
        assertEquals(8, grove.getMaxViewScore());
    }

    private static List<String> getInputAsList() {
        List<String> list = new ArrayList<>();
        Scanner s = new Scanner(TEST_INPUT);
        while (s.hasNext()) {
            list.add(s.nextLine());
        }
        return list;
    }

    private static final String TEST_INPUT = """
            30373
            25512
            65332
            33549
            35390""";
}
