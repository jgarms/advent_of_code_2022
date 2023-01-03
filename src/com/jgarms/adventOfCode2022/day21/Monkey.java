package com.jgarms.adventOfCode2022.day21;

import org.jetbrains.annotations.Nullable;

public class Monkey {
    final String name;

    @Nullable
    Long value;
    @Nullable
    Operator operator;
    @Nullable
    final String a, b;

    public Monkey(String input) {
        name = input.substring(0, 4);
        if (Character.isDigit(input.charAt(6))) {
            value = Long.parseLong(input.substring(6));
            operator = null;
            a = null;
            b = null;
        } else {
            value = null;
            operator = Operator.getOperator(input.charAt(11));
            a = input.substring(6, 10);
            b = input.substring(13, 17);
        }
    }

    public long getValue(MonkeyTree tree) {
        if (value == null) {
            Monkey monkeyA = tree.monkeys.get(a);
            Monkey monkeyB = tree.monkeys.get(b);
            assert operator != null;
            return operator.perform(monkeyA.getValue(tree), monkeyB.getValue(tree));
        }
        return value;
    }
}
