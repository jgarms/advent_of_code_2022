package com.jgarms.adventOfCode2022.day7;

import com.jgarms.adventOfCode2022.Utils;

import java.util.Scanner;

public class Day7 {

    public static void main(String... args) {
        Day7 day7 = new Day7();
        day7.partOne();
        day7.partTwo();
    }

    private void partOne() {
        Scanner scanner = new Scanner(Utils.getInput(this, "day7.txt"));
        CommandProcessor processor = new CommandProcessor(scanner);
        processor.process();
        Day7.MaxSizeVisitor maxSizeVisitor = new Day7.MaxSizeVisitor(100000);
        processor.getRoot().accept(maxSizeVisitor);
        System.out.println("Part one: " + maxSizeVisitor.getTotalSize());
    }

    private void partTwo() {
        Scanner scanner = new Scanner(Utils.getInput(this, "day7.txt"));
        CommandProcessor processor = new CommandProcessor(scanner);
        processor.process();
        int usedSpace = processor.getRoot().getSize();
        int availableSpace = 70000000 - usedSpace;
        int sizeNeeded = 30000000 - availableSpace;
        Day7.MinDirectoryCleaner cleaner = new MinDirectoryCleaner(sizeNeeded);
        processor.getRoot().accept(cleaner);
        System.out.println("Part two: " + cleaner.getSizeOfBestFit());
    }

    public static class MaxSizeVisitor implements Node.NodeVisitor {

        private final int maxSize;
        private int totalSize;

        public MaxSizeVisitor(int maxSize) {
            this.maxSize = maxSize;
        }

        public int getTotalSize() {
            return totalSize;
        }

        @Override
        public void visit(File file) {
            // no-op
        }

        @Override
        public void visit(Directory directory) {
            int size = directory.getSize();
            if (size <= maxSize) {
                totalSize += size;
            }
        }
    }

    public static class MinDirectoryCleaner implements Node.NodeVisitor {

        private final int sizeNeeded;
        private int sizeOfBestFit = Integer.MAX_VALUE;

        public MinDirectoryCleaner(int sizeNeeded) {
            this.sizeNeeded = sizeNeeded;
        }
        @Override
        public void visit(File file) {
            // no-op
        }

        @Override
        public void visit(Directory directory) {
            int size = directory.getSize();
            if (size > sizeNeeded && size < sizeOfBestFit) {
                sizeOfBestFit = size;
            }
        }

        public int getSizeOfBestFit() {
            return sizeOfBestFit;
        }
    }
}
