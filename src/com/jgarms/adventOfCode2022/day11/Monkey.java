package com.jgarms.adventOfCode2022.day11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Monkey {
    final int monkeyNumber;
    final Queue<Item> items;
    final Operation operation;
    final int divisor;
    final int trueMonkey;
    final int falseMonkey;

    int numInspections = 0;

    public Monkey(int monkeyNumber, Queue<Item> items, Operation operation, int divisor, int trueMonkey, int falseMonkey) {
        this.monkeyNumber = monkeyNumber;
        this.items = items;
        this.operation = operation;
        this.divisor = divisor;
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
    }

    public Item popItemForInspection() {
        numInspections++;
        return items.remove();
    }

    public boolean hasItems() {
        return items.size() > 0;
    }

    public static Monkey parseMonkey(Scanner s) {
        String monkeyNumberLine = s.nextLine(); // "Monkey 2:"
        int monkeyNumber = Character.getNumericValue(monkeyNumberLine.charAt(7));

        String startingItemsLine = s.nextLine(); // "  Starting items: 79, 60, 97"
        String itemsString = startingItemsLine.substring(18);
        String[] itemsStringArray = itemsString.split(", ");
        Queue<Item> items = new LinkedList<>();
        for (String itemString : itemsStringArray) {
            items.add(new Item(Integer.parseInt(itemString)));
        }

        Operation operation = Operation.parse(s.nextLine()); // "  Operation: new = old * old"

        String divisibleLine = s.nextLine(); // "  Test: divisible by 19"
        int divisor = Integer.parseInt(divisibleLine.substring(21));

        String trueMonkeyLine = s.nextLine(); // "    If true: throw to monkey 1"
        int trueMonkey = Integer.parseInt(trueMonkeyLine.substring(29));

        String falseMonkeyLine = s.nextLine(); // "    If false: throw to monkey 3"
        int falseMonkey = Integer.parseInt(falseMonkeyLine.substring(30));

        // consume the blank line if available
        if (s.hasNext()) {
            s.nextLine();
        }
        return new Monkey(monkeyNumber, items, operation, divisor, trueMonkey, falseMonkey);
    }
}
