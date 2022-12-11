package com.jgarms.adventOfCode2022.day11;

import com.jgarms.adventOfCode2022.Utils;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestDay11 {

    @Test
    public void testParseOperations() {
        Operation operation = Operation.parse("  Operation: new = old * 5");
        assertEquals(5, operation.amount);
        assertEquals(OperationType.MULTIPLY, operation.type);

        operation = Operation.parse("  Operation: new = old + 7");
        assertEquals(7, operation.amount);
        assertEquals(OperationType.ADD, operation.type);

        operation = Operation.parse("  Operation: new = old * old");
        assertNull(operation.amount);
        assertEquals(OperationType.SQUARE, operation.type);
    }

    @Test
    public void testParseMonkey() {
        String monkeyString = """
                Monkey 1:
                  Starting items: 54, 65, 75, 74
                  Operation: new = old + 6
                  Test: divisible by 19
                    If true: throw to monkey 2
                    If false: throw to monkey 0
                """;
        Monkey monkey = Monkey.parseMonkey(new Scanner(monkeyString));
        assertEquals(1, monkey.monkeyNumber);
        assertEquals(4, monkey.items.size());
        assertEquals(54, Objects.requireNonNull(monkey.items.peek()).worryLevel);
        assertEquals(OperationType.ADD, monkey.operation.type);
        assertEquals(19, monkey.divisor);
        assertEquals(2, monkey.trueMonkey);
        assertEquals(0, monkey.falseMonkey);
    }

    @Test
    public void testParseMonkeys() { // https://ase.tufts.edu/nohomersclub/nhc-sign.jpg
        MonkeyHandler handler = new MonkeyHandler(Utils.getScanner(this, "day11.txt"), true);
        assertEquals(8, handler.monkeys.size());
    }

    @Test
    public void testTurn() {
        MonkeyHandler handler = new MonkeyHandler(new Scanner(SHORT_INPUT), true);

        Monkey monkey0 = handler.monkeys.get(0);
        assertEquals(2, monkey0.items.size());

        Monkey monkey3 = handler.monkeys.get(3);
        assertEquals(1, monkey3.items.size());

        handler.performTurn(monkey0);
        assertEquals(0, monkey0.items.size());
        assertEquals(3, monkey3.items.size());
    }

    @Test
    public void testRound() {
        MonkeyHandler handler = new MonkeyHandler(new Scanner(SHORT_INPUT), true);
        handler.performRound();

        assertEquals(4, handler.monkeys.get(0).items.size());
        assertEquals(6, handler.monkeys.get(1).items.size());
        assertEquals(0, handler.monkeys.get(2).items.size());
        assertEquals(0, handler.monkeys.get(3).items.size());
    }

    @Test
    public void testRounds() {
        MonkeyHandler handler = new MonkeyHandler(new Scanner(SHORT_INPUT), true);
        for (int i=0; i<20; i++) {
            handler.performRound();
        }
        assertEquals(5, handler.monkeys.get(0).items.size());
        assertEquals(5, handler.monkeys.get(1).items.size());
        assertEquals(0, handler.monkeys.get(2).items.size());
        assertEquals(0, handler.monkeys.get(3).items.size());

        assertEquals(101, handler.monkeys.get(0).numInspections);
        assertEquals(95, handler.monkeys.get(1).numInspections);
        assertEquals(7, handler.monkeys.get(2).numInspections);
        assertEquals(105, handler.monkeys.get(3).numInspections);

        assertEquals(10605, handler.calculateMonkeyBusiness());
    }

    public static final String SHORT_INPUT = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3
                        
            Monkey 1:
              Starting items: 54, 65, 75, 74
              Operation: new = old + 6
              Test: divisible by 19
                If true: throw to monkey 2
                If false: throw to monkey 0
                        
            Monkey 2:
              Starting items: 79, 60, 97
              Operation: new = old * old
              Test: divisible by 13
                If true: throw to monkey 1
                If false: throw to monkey 3
                        
            Monkey 3:
              Starting items: 74
              Operation: new = old + 3
              Test: divisible by 17
                If true: throw to monkey 0
                If false: throw to monkey 1""";
}
