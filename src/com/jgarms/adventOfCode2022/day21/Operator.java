package com.jgarms.adventOfCode2022.day21;

public enum Operator {

    ADD('+') {
        @Override
        long perform(long a, long b) {
            return a + b;
        }
    }, SUBTRACT('-') {
        @Override
        long perform(long a, long b) {
            return a - b;
        }
    }, MULTIPLY('*') {
        @Override
        long perform(long a, long b) {
            return a * b;
        }
    }, DIVIDE('/') {
        @Override
        long perform(long a, long b) {
            return a / b;
        }
    };

    final char opcode;
    Operator(char opcode) {
        this.opcode = opcode;
    }

    public static Operator getOperator(char c) {
        return switch(c) {
            case '+'->ADD;
            case '-'->SUBTRACT;
            case '*'->MULTIPLY;
            case '/'->DIVIDE;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }

    abstract long perform(long a, long b);
}
