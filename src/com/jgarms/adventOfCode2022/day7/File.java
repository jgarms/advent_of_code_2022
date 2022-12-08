package com.jgarms.adventOfCode2022.day7;

import java.util.Collections;
import java.util.List;

public class File implements Node {
    private final Directory parent;
    private final String name;
    private final int size;

    public File(Directory parent, String name, int size) {
        this.parent = parent;
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Directory getParent() {
        return parent;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        return getName();
    }
}
