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
}
