package com.jgarms.adventOfCode2022.day13;

import java.util.Stack;

public class Parser {

    public static ListPacket parse(String line) {
        if (!line.startsWith("[")) {
            throw new IllegalArgumentException("Packet data must begin with '['");
        }
        Stack<ListPacket> stack = new Stack<>();
        ListPacket outerList = new ListPacket();
        stack.push(outerList);
        // Already processed the first char
        for (int index=1; index<line.length(); index++) {
            char c = line.charAt(index);
            switch (c) {
                case '[' -> {
                    ListPacket list = new ListPacket();
                    stack.peek().addList(list);
                    stack.push(list);
                }
                case ',' -> stack.peek().completeCurrentDigits();
                case ']' -> {
                    ListPacket currentList = stack.pop();
                    currentList.completeCurrentDigits();
                }
                default -> {
                    assert Character.isDigit(c);
                    stack.peek().addDigitChar(c);
                }
            }
        }
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Incomplete list. Missing closing ']'.");
        }
        return outerList;
    }
}
