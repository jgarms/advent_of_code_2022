package com.jgarms.adventOfCode2022.day11;

public enum OperationType {
    ADD() {
        @Override
        public long performOperation(long old, Long amount) {
            return old + amount;
        }
    }, MULTIPLY {
        @Override
        public long performOperation(long old, Long amount) {
            return old * amount;
        }
    }, SQUARE {
        @Override
        public long performOperation(long old, Long amount) {
            return old * old;
        }
    };

    public abstract long performOperation(long old, Long amount);
}
