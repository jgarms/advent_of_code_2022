package com.jgarms.adventOfCode2022.day11;

public class Operation {
    final OperationType type;
    final Long amount;

    public Operation(OperationType type, Long amount) {
        this.type = type;
        this.amount = amount;
    }

    public long performOperation(long old) {
        return type.performOperation(old, amount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("new = old ");
        if (type == OperationType.ADD) {
            sb.append("+ ");
        } else {
            sb.append("* ");
        }
        if (type == OperationType.SQUARE) {
            sb.append("old");
        } else {
            sb.append(amount);
        }
        return sb.toString();
    }

    public static Operation parse(String operationString) {
        // ex: '  Operation: new = old * old
        if (operationString.endsWith("* old")) {
            return new Operation(OperationType.SQUARE, null);
        }
        // ex: '  Operation: new = old * 19'
        long amount = Long.parseLong(operationString.substring(25));
        char operationChar = operationString.charAt(23); // "*"
        if ('*' == operationChar) {
            return new Operation(OperationType.MULTIPLY, amount);
        } else {
            if ('+' != operationChar) {
                throw new IllegalArgumentException("Received unknown operation: " + operationChar);
            }
            return new Operation(OperationType.ADD, amount);
        }
    }
}
