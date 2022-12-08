package com.jgarms.adventOfCode2022.day7;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Directory implements Node {
    private final Directory parent;
    private final String name;
    private final Map<String, Node> children = new HashMap<>();

    public static Directory createFilesystem() {
        return new Directory(null, "");
    }
    private Directory(Directory parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public File createFile(String name, int size) {
        File file = new File(this, name, size);
        children.put(name, file);
        return file;
    }

    public Directory createDirectory(String name) {
        Directory directory = new Directory(this, name);
        children.put(name, directory);
        return directory;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (Node child : children.values()) {
            totalSize += child.getSize();
        }
        return totalSize;
    }

    @Override
    public Directory getParent() {
        return parent;
    }

    @Override
    public Collection<Node> getChildren() {
        return children.values();
    }

    public Node getChild(String name) {
        return children.get(name);
    }

    public String toString() {
        return getName();
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
        for (Node child: getChildren()) {
            child.accept(visitor);
        }
    }
}
