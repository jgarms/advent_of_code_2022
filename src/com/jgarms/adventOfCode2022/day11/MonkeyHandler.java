package com.jgarms.adventOfCode2022.day11;

import java.util.*;

public class MonkeyHandler {

    final List<Monkey> monkeys = new ArrayList<>();
    final boolean divideBy3;

    public MonkeyHandler(Scanner s, boolean divideBy3) {
        this.divideBy3 = divideBy3;
        while (s.hasNext()) {
            Monkey monkey = Monkey.parseMonkey(s);
            monkeys.add(monkey);
        }
    }

    public void performTurn(Monkey monkey) {
        while (monkey.hasItems()) {
            Item item = monkey.popItemForInspection();
            long newWorryLevel = monkey.operation.performOperation(item.worryLevel);
            newWorryLevel = newWorryLevel / 3;


            item.worryLevel = newWorryLevel;



            final int monkeyToThrowTo;
            if (newWorryLevel % monkey.divisor == 0) {
                monkeyToThrowTo = monkey.trueMonkey;
            } else {
                monkeyToThrowTo = monkey.falseMonkey;
            }
            monkeys.get(monkeyToThrowTo).items.add(item);
        }
    }

    public void performRound() {
        for (Monkey monkey : monkeys) {
            performTurn(monkey);
        }
    }

    public int calculateMonkeyBusiness() {
        // top 2 numInspections, multiplied
        List<Integer> inspections = new ArrayList<>();
        for (Monkey monkey : monkeys) {
            inspections.add(monkey.numInspections);
        }
        inspections.sort(Collections.reverseOrder());
        return inspections.get(0) * inspections.get(1);
    }

}
