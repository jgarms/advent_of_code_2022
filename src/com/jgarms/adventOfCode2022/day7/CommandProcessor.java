package com.jgarms.adventOfCode2022.day7;

import java.util.Scanner;

public class CommandProcessor {

    private final Scanner logScanner;

    private final Directory root;

    private Directory currentDirectory;

    public CommandProcessor(Scanner logScanner) {
        this.logScanner = logScanner;
        root = Directory.createFilesystem();
        currentDirectory = root;
    }

    public void process() {
        while (logScanner.hasNext()) {
            processLine(logScanner.nextLine());
        }
    }

    public Directory getRoot() {
        return root;
    }

    private void processLine(String line) {
        if ("$ ls".equals(line)) {
            // no-op
        } else if ("$ cd /".equals(line)) {
            currentDirectory = root;
        } else if ("$ cd ..".equals(line)) {
            currentDirectory = currentDirectory.getParent();
        } else if (line.startsWith("$ cd ")) {
            // moving into a new directory
            String dirName = line.substring(5);
            currentDirectory = (Directory)currentDirectory.getChild(dirName);
        } else if (line.startsWith("dir ")) {
            // directory creation
            String dirName = line.substring(4);
            currentDirectory.createDirectory(dirName);
        } else {
            // file listing like "512 xyz"
            String[] sizeAndName = line.split(" ");
            int size = Integer.parseInt(sizeAndName[0]);
            String filename = sizeAndName[1];
            currentDirectory.createFile(filename, size);
        }
    }

}
