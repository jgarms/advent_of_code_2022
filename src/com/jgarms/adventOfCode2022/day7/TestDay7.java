package com.jgarms.adventOfCode2022.day7;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay7 {

    private static final String TEST_INPUT = """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k""";

    @Test
    void testSimpleFilesystem() {
        /*
         * /
         * /a
         * /a/b (file) - 100
         * /c
         */
        Directory root = Directory.createFilesystem();
        Directory a = root.createDirectory("a");
        File b = a.createFile("b", 100);
        Directory c = root.createDirectory("c");
        assertEquals(100, b.getSize());
        assertEquals(100, a.getSize());
        assertEquals(100, root.getSize());
        assertEquals(0, c.getSize());
    }

    @Test
    void testSampleInput() {
        CommandProcessor commandProcessor = new CommandProcessor(new Scanner(TEST_INPUT));
        commandProcessor.process();
        Day7.MaxSizeVisitor maxSizeVisitor = new Day7.MaxSizeVisitor(100000);
        commandProcessor.getRoot().accept(maxSizeVisitor);
        int maxSize = maxSizeVisitor.getTotalSize();
        assertEquals(95437, maxSize);

        Day7.MinDirectoryCleaner cleaner = new Day7.MinDirectoryCleaner(8381165);
        commandProcessor.getRoot().accept(cleaner);
        int bestFit = cleaner.getSizeOfBestFit();
        assertEquals(24933642, bestFit);
    }
}
