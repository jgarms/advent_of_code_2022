package com.jgarms.adventOfCode2022.day21;

import com.jgarms.adventOfCode2022.Utils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay21 {

    @Test
    public void testParsing() {
        Monkey monkey = new Monkey("dbpl: 5");
        assertEquals("dbpl", monkey.name);
        assertEquals(5, monkey.value);

        monkey = new Monkey("ptdq: humn - dvpt");
        assertEquals("ptdq", monkey.name);
        assertEquals(Operator.SUBTRACT, monkey.operator);
        assertEquals("humn", monkey.a);
        assertEquals("dvpt", monkey.b);

        MonkeyTree tree = new MonkeyTree(new Scanner(SAMPLE_INPUT));
        assertEquals(15, tree.monkeys.size());

        tree = new MonkeyTree(Utils.getScanner(this, "day21.txt"));
        assertEquals(2295, tree.monkeys.size());
    }

    @Test
    public void testGetValues() {
        MonkeyTree tree = new MonkeyTree(new Scanner(SAMPLE_INPUT));
        long value = tree.monkeys.get("root").getValue(tree);
        assertEquals(152, value);

        tree = new MonkeyTree(Utils.getScanner(this, "day21.txt"));
        value = tree.monkeys.get("root").getValue(tree);
        assertEquals(22382838633806L, value);
    }

    public static final String SAMPLE_INPUT = """
            root: pppw + sjmn
            dbpl: 5
            cczh: sllz + lgvd
            zczc: 2
            ptdq: humn - dvpt
            dvpt: 3
            lfqf: 4
            humn: 5
            ljgn: 2
            sjmn: drzm * dbpl
            sllz: 4
            pppw: cczh / lfqf
            lgvd: ljgn * ptdq
            drzm: hmdt - zczc
            hmdt: 32""";
}
