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
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;
        while (min < max) {
            long candidate = (min + max) / 2;
            human.value = candidate;
            long result = root.getValue(this);
            if (result == 0) {
                return candidate;
            }
            if (result > 0) {
                min = candidate + 1;
            } else {
                max = candidate - 1;
            }
        }

        // Hm, binary search failed. Try normal iteration.
        for (long candidate=1; candidate<Long.MAX_VALUE; candidate++) {
            human.value = candidate;
            if (root.getValue(this) == 0L) {
                return candidate;
            }
            human.value = candidate * -1L;
            if (root.getValue(this) == 0L) {
                return candidate;
            }
        }

        throw new IllegalStateException("Could not find a solution");
    }
}
