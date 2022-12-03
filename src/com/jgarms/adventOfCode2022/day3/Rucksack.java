package com.jgarms.adventOfCode2022.day3;

import java.util.HashSet;
import java.util.Set;

public class Rucksack {
    private final char[] firstCompartment;
    private final char[] secondCompartment;

    public Rucksack(final String input) {
        final int size = input.length() / 2; // each compartment holds half
        firstCompartment = new char[size];
        input.getChars(0, size, firstCompartment, 0);
        secondCompartment = new char[size];
        input.getChars(size, input.length(), secondCompartment, 0);
    }

    public char getSharedItemFromCompartments() {
        Set<Character> firstSet = createSetFromArray(firstCompartment);
        firstSet.retainAll(createSetFromArray(secondCompartment));
        assert firstSet.size() == 1;
        return firstSet.iterator().next();
    }

    public char getSharedBadgeItem(Rucksack rucksack2, Rucksack rucksack3) {
        Set<Character> items = createSet();
        items.retainAll(rucksack2.createSet());
        items.retainAll(rucksack3.createSet());
        assert items.size() == 1;
        return items.iterator().next();
    }

    private Set<Character> createSet() {
        Set<Character> set = createSetFromArray(firstCompartment);
        for (char c: secondCompartment) {
            set.add(c);
        }
        return set;
    }

    private static Set<Character> createSetFromArray(char[] array) {
        Set<Character> set = new HashSet<>();
        for (char c: array) {
            set.add(c);
        }
        return set;
    }



    public static void main(String... args) {
        // test code
        // day 1
        System.out.println("day 1");
        Rucksack rucksack = new Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp");
        System.out.println("first: " + String.valueOf(rucksack.firstCompartment));
        System.out.println("second: " + String.valueOf(rucksack.secondCompartment));
        System.out.println("shared char: " + rucksack.getSharedItemFromCompartments());

        // day 2
        System.out.println("\nday2");
        Rucksack rucksack1 = new Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp");
        Rucksack rucksack2 = new Rucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");
        Rucksack rucksack3 = new Rucksack("PmmdzqPrVvPwwTWBwg");
        System.out.println("shared char: " + rucksack1.getSharedBadgeItem(rucksack2, rucksack3));
    }
}
