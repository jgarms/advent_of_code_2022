package com.jgarms.adventOfCode2022.day22;

public record Instruction(InstructionType type, int numSpaces) {
    enum InstructionType {
        MOVE, TURN_RIGHT, TURN_LEFT
    }

    public static Instruction getTurnInstruction(char c) {
        if (c == 'L') {
            return new Instruction(InstructionType.TURN_LEFT, 0);
        } else if (c == 'R') {
            return new Instruction(InstructionType.TURN_RIGHT, 0);
        } else {
            throw new IllegalArgumentException("Unknown turn char: " + c);
        }
    }

    public static Instruction getMoveInstruction(String distanceString) {
        int distance = Integer.parseInt(distanceString);
        return new Instruction(InstructionType.MOVE, distance);
    }
}
