package com.jgarms.adventOfCode2022.day11;

import java.util.*;

public class MonkeyHandler {

    final List<Monkey> monkeys = new ArrayList<>();
    final boolean divideBy3;
    final long lcm;

    public MonkeyHandler(Scanner s, boolean divideBy3) {
        this.divideBy3 = divideBy3;
        List<Long> divisors = new ArrayList<>();
        while (s.hasNext()) {
            Monkey monkey = Monkey.parseMonkey(s);
            monkeys.add(monkey);
            divisors.add((long)monkey.divisor);
        }
        lcm = lcm(divisors);
    }


    private static long gcd(long a, long b)
    {
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }
    private static long lcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }

    private static long lcm(List<Long> input)
    {
        long result = input.get(0);
        for(int i = 1; i < input.size(); i++) {
            result = lcm(result, input.get(i));
        }
        return result;
    }

    public void performTurn(Monkey monkey) {
        while (monkey.hasItems()) {
            Item item = monkey.popItemForInspection();
            long newWorryLevel = monkey.operation.performOperation(item.worryLevel);
            if (divideBy3) {
                newWorryLevel = newWorryLevel / 3;
            }
            newWorryLevel = newWorryLevel % lcm;

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

    public long calculateMonkeyBusiness() {
        // top 2 numInspections, multiplied
        List<Integer> inspections = new ArrayList<>();
        for (Monkey monkey : monkeys) {
            inspections.add(monkey.numInspections);
        }
        inspections.sort(Collections.reverseOrder());
        return (long)inspections.get(0) * (long)inspections.get(1);
    }

}
