package com.jgarms.adventOfCode2022.day21;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MonkeyTree {
    Map<String, Monkey> monkeys = new HashMap<>();

    public MonkeyTree(Scanner scanner) {
        while (scanner.hasNext()) {
            Monkey monkey = new Monkey(scanner.nextLine());
            monkeys.put(monkey.name, monkey);
        }
    }

    public long getRootValue() {
        return monkeys.get("root").getValue(this);
    }

    public long searchForRootEquality() {
        Monkey root = monkeys.get("root");
        root.operator = Operator.EQUALS;
        Monkey human = monkeys.get("humn");
        human.value = 0L;
        if (root.getValue(this) == 0) {
            return 0;
        }
        long left = Long.MIN_VALUE;
        long right = Long.MAX_VALUE;
        while (left < right) {
            long candidate = (left + right) / 2;
            human.value = candidate;
            long result = root.getValue(this);
            if (result == 0) {
                return candidate;
            }
            if (result > 0) {
                left = candidate + 1;
            } else {
                right = candidate - 1;
            }
        }

        return 0L;
    }
}
