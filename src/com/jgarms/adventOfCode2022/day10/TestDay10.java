package com.jgarms.adventOfCode2022.day10;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDay10 {

    @Test
    public void testShort() {
        // Test parsing
        CPU cpu = new CPU(new Scanner(SHORT_TEST_INPUT));
        assertEquals(3, cpu.instructions.size());
        Iterator<Instruction> iterator = cpu.instructions.iterator();
        Instruction noop = iterator.next();
        assertTrue(noop instanceof Noop);
        Add add = (Add)iterator.next();
        assertEquals(3, add.numToAdd);
        add = (Add)iterator.next();
        assertEquals(-5, add.numToAdd);

        // test execution
        cpu.execute();
        assertEquals(-1, cpu.x);
        assertEquals(5, cpu.cycle);
    }

    @Test
    public void testLong() {
        CPU cpu = new CPU(new Scanner(TEST_INPUT));
        cpu.execute();
        assertEquals(13140, cpu.signalStrengthSum);
    }

    public static final String SHORT_TEST_INPUT = """
            noop
            addx 3
            addx -5""";
    public static final String TEST_INPUT = """
            addx 15
            addx -11
            addx 6
            addx -3
            addx 5
            addx -1
            addx -8
            addx 13
            addx 4
            noop
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx -35
            addx 1
            addx 24
            addx -19
            addx 1
            addx 16
            addx -11
            noop
            noop
            addx 21
            addx -15
            noop
            noop
            addx -3
            addx 9
            addx 1
            addx -3
            addx 8
            addx 1
            addx 5
            noop
            noop
            noop
            noop
            noop
            addx -36
            noop
            addx 1
            addx 7
            noop
            noop
            noop
            addx 2
            addx 6
            noop
            noop
            noop
            noop
            noop
            addx 1
            noop
            noop
            addx 7
            addx 1
            noop
            addx -13
            addx 13
            addx 7
            noop
            addx 1
            addx -33
            noop
            noop
            noop
            addx 2
            noop
            noop
            noop
            addx 8
            noop
            addx -1
            addx 2
            addx 1
            noop
            addx 17
            addx -9
            addx 1
            addx 1
            addx -3
            addx 11
            noop
            noop
            addx 1
            noop
            addx 1
            noop
            noop
            addx -13
            addx -19
            addx 1
            addx 3
            addx 26
            addx -30
            addx 12
            addx -1
            addx 3
            addx 1
            noop
            noop
            noop
            addx -9
            addx 18
            addx 1
            addx 2
            noop
            noop
            addx 9
            noop
            noop
            noop
            addx -1
            addx 2
            addx -37
            addx 1
            addx 3
            noop
            addx 15
            addx -21
            addx 22
            addx -6
            addx 1
            noop
            addx 2
            addx 1
            noop
            addx -10
            noop
            noop
            addx 20
            addx 1
            addx 2
            addx 2
            addx -6
            addx -11
            noop
            noop
            noop""";
}
