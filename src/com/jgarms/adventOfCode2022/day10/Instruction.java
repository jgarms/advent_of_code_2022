package com.jgarms.adventOfCode2022.day10;

public abstract class Instruction {
    /**
     * Execute this instruction
     * @return true if the instruction is complete; false if it needs to continue to execute.
     */
    public abstract boolean execute(CPU cpu);
}
