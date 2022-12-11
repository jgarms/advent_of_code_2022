package com.jgarms.adventOfCode2022.day11;

public enum OperationType {
    ADD() {
        @Override
        public int performOperation(int old, Integer amount) {
            return old + amount;
        }
    }, MULTIPLY {
        @Override
        public int performOperation(int old, Integer amount) {
            return old * amount;
        }
    }, SQUARE {
        @Override
        public int performOperation(int old, Integer amount) {
            return old * old;
        }
    };

    public abstract int performOperation(int old, Integer amount);
}
