package com.jgarms.adventOfCode2022.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Crates {

    // List of the stacks, zero-indexed. NB: Convert all external access to 1-based indices
    private final List<Stack<Character>> crates = new ArrayList<>();

    public Crates(Stack<String> input) {
        String firstLine = input.peek();
        int numStacks = (firstLine.length() + 2) / 4;
        for (int i=0; i<numStacks; i++) {
            crates.add(new Stack<>());
        }
        while(!input.isEmpty()) {
            final String line = input.pop();
            int index = 0;
            int stackNum = 0;
            while (stackNum < crates.size()) {
                index++;
                char c = line.charAt(index);
                if (Character.isLetter(c)) {
                    crates.get(stackNum).push(c);
                }
                index += 3;
                stackNum++;
            }
        }
    }

    public int getNumCrates() {
        return crates.size();
    }

    public String peek() {
        StringBuilder sb = new StringBuilder();
        for (Stack<Character> crate : crates) {
            sb.append(crate.peek());
        }
        return sb.toString();
    }

    public void applyMove9000(Move move) {
        for (int i=0; i<move.numCrates; i++) {
            // crates are 0-indexed, but moves are 1-indexed
            Character c = crates.get(move.sourceCrate - 1).pop();
            crates.get(move.destinationCrate - 1).push(c);
        }
    }

    public void applyMove9001(Move move) {
        // Need to pop everything before we push. Use a stack
        // in order to keep the order.
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<move.numCrates; i++) {
            // crates are 0-indexed, but moves are 1-indexed
            stack.push(crates.get(move.sourceCrate - 1).pop());
        }
        while (!stack.isEmpty()) {
            crates.get(move.destinationCrate - 1).push(stack.pop());
        }
    }
}
