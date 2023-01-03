package com.jgarms.adventOfCode2022.day21;

import com.jgarms.adventOfCode2022.Utils;

public class Day21 {

    public static void main(String... args) {
        Day21 day21 = new Day21();
        day21.partOne();
    }

    private void partOne() {
        MonkeyTree tree = new MonkeyTree(Utils.getScanner(this, "day21.txt"));
        long rootValue = tree.monkeys.get("root").getValue(tree);
        System.out.println("Part one: " + rootValue);
    }
}
