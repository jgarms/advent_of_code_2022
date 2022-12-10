package com.jgarms.adventOfCode2022.day10;

public class Noop extends Instruction {
    @Override
    public boolean execute(CPU cpu) {
        return true;
    }

    @Override
    public String toString() {
        return "noop";
    }
}
