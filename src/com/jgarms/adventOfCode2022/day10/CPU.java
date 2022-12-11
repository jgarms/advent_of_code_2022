package com.jgarms.adventOfCode2022.day10;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CPU {

    public int x = 1;

    public int cycle = 0;
    public int crtPos = 0;

    public int signalStrengthSum = 0;
    public StringBuilder crt = new StringBuilder();

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

        handleSignalStrength();
        handlePixel();

        // execute
        Instruction instruction = instructions.peek();
        if (instruction == null) {
            throw new IllegalStateException("attempt to execute an empty instruction queue");
        }
        boolean finished = instruction.execute(this);
        if (finished) {
            instructions.remove();
        }
    }

    private void handleSignalStrength() {
        if (isSignalStrengthCycle()) {
            int signalStrength = cycle * x;
            signalStrengthSum += signalStrength;
        }
    }

    private void handlePixel() {
        if (x-1 <= crtPos && crtPos <= x+1) {
            crt.append("#");
        } else {
            crt.append(".");
        }
        crtPos++;
        if (crtPos == 40) {
            crt.append("\n");
            crtPos = 0;
        }
    }

    public boolean isSignalStrengthCycle() {
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
