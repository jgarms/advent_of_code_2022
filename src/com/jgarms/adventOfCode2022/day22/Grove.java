package com.jgarms.adventOfCode2022.day22;

import java.util.*;

public class Grove {
    final Map<Position, Content> contents = new HashMap<>();
    final List<Instruction> instructions = new ArrayList<>();

    Position position;
    Orientation orientation = Orientation.RIGHT;

    public Grove(Scanner input) {
        int y=0;
        while (input.hasNext()) {
            String line = input.nextLine();
            if (line.isEmpty()) {
                // next line is instructions, then we're done
                parseInstructions(input.nextLine());
                break;
            }
            for (int x=0; x<line.length(); x++) {
                Content content = Content.getContent(line.charAt(x));
                if (content != null) {
                    Position position = new Position(x, y);
                    contents.put(position, content);
                    // Our starting position is the top left
                    if (this.position == null) {
                        this.position = position;
                    }
                }
            }
            y++;
        }
    }

    void parseInstructions(String input) {
        StringBuilder numberBuffer = new StringBuilder();
        for (char c:input.toCharArray()) {
            if (Character.isDigit(c)) {
                numberBuffer.append(c);
            } else {
                instructions.add(Instruction.getMoveInstruction(numberBuffer.toString()));
                numberBuffer.setLength(0);
                instructions.add(Instruction.getTurnInstruction(c));
            }
        }
        if (numberBuffer.length() > 0) {
            instructions.add(Instruction.getMoveInstruction(numberBuffer.toString()));
        }
    }

    int execute() {
        for (Instruction instruction: instructions) {
            execute(instruction);
        }
        int row = position.y() + 1;
        int column = position.x() + 1;
        return (1000 * row) + (4 * column) + orientation.value;
    }

    void execute(Instruction instruction) {
        switch(instruction.type()) {
            case TURN_LEFT -> orientation = orientation.turnLeft();
            case TURN_RIGHT -> orientation = orientation.turnRight();
            case MOVE -> move(instruction.numSpaces());
        }
    }

    void move(int numSpaces) {
        for (int i=0; i<numSpaces; i++) {
            Position next = orientation.getNextPosition(position);
            Content content = contents.get(next);
            if (content == Content.FORCE_FIELD) {
                break;
            } else if (content == Content.OPEN) {
                position = next;
                continue;
            }
            // We're wrapping around
            Orientation reverse = orientation.turnLeft().turnLeft();
            next = reverse.getNextPosition(position);
            while (contents.get(next) != null) {
                next = reverse.getNextPosition(next);
            }
            // We're on a blank space, so move one back
            position = orientation.getNextPosition(next);
        }
    }
}
