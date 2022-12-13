package com.jgarms.adventOfCode2022.day13;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListPacket extends PacketData implements Comparable<ListPacket> {
    private final List<PacketData> data = new ArrayList<>();

    StringBuilder pendingDigits = new StringBuilder();

    public ListPacket() {}

    /**
     * Creates a list with a single IntPacket of 'dataValue'
     */
    public ListPacket(IntPacket intPacket) {
        data.add(intPacket);
    }

    public void addList(ListPacket list) {
        data.add(list);
    }

    public void addDigitChar(char digit) {
        assert Character.isDigit(digit);
        pendingDigits.append(digit);
    }

    public void completeCurrentDigits() {
        if (!pendingDigits.isEmpty()) {
            int number = Integer.parseInt(pendingDigits.toString());
            IntPacket intPacket = new IntPacket(number);
            data.add(intPacket);
            pendingDigits.setLength(0);
        }
    }
    public int size() {
        return data.size();
    }

    public PacketData get(int index) {
        return data.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean first = true;
        for (PacketData datum: data) {
            if (first) {
                first = false;
            } else {
                sb.append(",");
            }
            sb.append(datum);
        }
        sb.append("]");
        return sb.toString();
    }

    public static int compare(ListPacket left, ListPacket right) {
        for (int i=0; i<left.size(); i++) {
            int compare = compare(left, right, i);
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(left.size(), right.size());
    }

    public static int compare(ListPacket left, ListPacket right, int index) {
        assert index < left.size();
        if (index >= right.size()) {
            // out of right side inputs
            return 1;
        }
        PacketData leftItem = left.get(index);
        PacketData rightItem = right.get(index);
        boolean leftIsInt = leftItem instanceof IntPacket;
        boolean rightIsInt = rightItem instanceof IntPacket;
        if (leftIsInt && rightIsInt) {
            int leftInt = ((IntPacket)leftItem).value;
            int rightInt = ((IntPacket)rightItem).value;
            return Integer.compare(leftInt, rightInt);
        }
        if (!leftIsInt && !rightIsInt) {
            // Both are lists
            return compare((ListPacket)leftItem, (ListPacket)rightItem);
        }
        // One is an int and one is a list
        if (rightIsInt) {
            return compare((ListPacket)leftItem, new ListPacket((IntPacket)rightItem));
        } else {
            return compare(new ListPacket((IntPacket)leftItem), (ListPacket)rightItem);
        }
    }

    @Override
    public int compareTo(@NotNull ListPacket o) {
        return compare(this, o);
    }
}
