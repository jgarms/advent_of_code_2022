package com.jgarms.adventOfCode2022.day7;

import java.util.Collection;

public interface Node {

    String getName();
    int getSize();
    Directory getParent();
    Collection<Node> getChildren();

    void accept(NodeVisitor visitor);

    interface NodeVisitor {
        void visit(File file);

        void visit(Directory directory);
    }
}
