package com.jgarms.adventOfCode2022.day13;

public class IntPacket extends PacketData {
    final int value;

    public IntPacket(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
