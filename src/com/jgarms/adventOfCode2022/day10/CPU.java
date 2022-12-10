package com.jgarms.adventOfCode2022.day10;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CPU {

    public int x = 1;

    public int cycle = 0;

    public int signalStrengthSum = 0;

    final Queue<Instruction> instructions = new LinkedList<>();

    public CPU(Scanner scanner) {
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            if ("noop".equals(line)) {
                instructions.add(new Noop());
            } else if (line.startsWith("addx ")) {
                int numToAdd = Integer.parseInt(line.substring(5));
                instructions.add(new Add(numToAdd));
            } else {
                throw new IllegalArgumentException("Cannot parse instruction: " + line);
            }
        }
    }

    public void executeOneCycle() {
        cycle++;
        if (isSignalStrengthCycle(cycle)) {
            int signalStrength = cycle * x;
            System.out.println("Start: Cycle=" + cycle + ". X=" + x + ". SS= " + signalStrength);
            signalStrengthSum += signalStrength;
        }
        if (instructions.size() == 0) {
            throw new IllegalStateException("Attempt to execute with an empty queue.");
        }
        Instruction instruction = instructions.peek();
        boolean finished = instruction.execute(this);
        if (finished) {
            instructions.remove();
        }
    }

    public static boolean isSignalStrengthCycle(int cycle) {
        if (cycle == 20) {
            return true;
        }
        return (cycle - 20) % 40 == 0;
    }

    public void execute() {
        while (instructions.size() > 0) {
            executeOneCycle();
        }
    }

}
