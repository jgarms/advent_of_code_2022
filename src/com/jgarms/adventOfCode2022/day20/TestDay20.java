package com.jgarms.adventOfCode2022.day20;

import com.jgarms.adventOfCode2022.Utils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay20 {

    @Test
    public void testParsing() {
        Data data = new Data(new Scanner(SAMPLE_INPUT));
        assertEquals("1, 2, -3, 3, -2, 0, 4", data.toString());
        assertEquals(7, data.size);
        assertEquals(7, data.originalNodes.length);
        assertEquals(1, data.head.value);
        Node tail = data.head.left;
        assertEquals(4, tail.value);
        assertEquals(data.head, tail.right);

        data = new Data(Utils.getScanner(this, "day20.txt"));
        assertEquals(5000, data.size);
        assertEquals(-624, data.head.value);
    }

    @Test
    public void testMixing() {
        Data data = new Data(new Scanner(SAMPLE_INPUT));
        data.mix();
        assertEquals("1, 2, -3, 4, 0, 3, -2", data.toString());
        assertEquals(3, data.getGroveCoordinates());

        data = new Data(Utils.getScanner(this, "day20.txt"));
        data.mix();
        System.out.println(data.getGroveCoordinates());
    }

    public static final String SAMPLE_INPUT = """
            1
            2
            -3
            3
            -2
            0
            4""";
}
