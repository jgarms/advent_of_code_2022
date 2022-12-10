package com.jgarms.adventOfCode2022.day10;

public class Add extends Instruction {

    int cyclesRemaining;
    final int numToAdd;

    public Add(int numToAdd) {
        cyclesRemaining = 2;
        this.numToAdd = numToAdd;
    }
    @Override
    public boolean execute(CPU cpu) {
        cyclesRemaining--;
        if (cyclesRemaining > 0) {
            return false;
        }
        cpu.x = cpu.x + numToAdd;
        return true;
    }

    @Override
    public String toString() {
        return "addx " + numToAdd + ". cyclesRemaining=" + cyclesRemaining;
    }
}
