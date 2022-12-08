package com.jgarms.adventOfCode2022.day8;

import com.jgarms.adventOfCode2022.Utils;

import java.util.List;

public class Day8 {

    public static void main(String... args) {
        Day8 day8 = new Day8();
        day8.partOne();
        day8.partTwo();
    }

    private void partOne() {
        List<String> input = Utils.getInputAsList(this, "day8.txt");
        Grove grove = new Grove(input);
        int visibleTrees = grove.countVisibleTrees();
        System.out.println("Part one: " + visibleTrees);
    }

    private void partTwo() {
        List<String> input = Utils.getInputAsList(this, "day8.txt");
        Grove grove = new Grove(input);
        int maxScore = grove.getMaxViewScore();
        System.out.println("Part one: " + maxScore);
    }

    public static class Grove {
        final int width, height;
        final int[][] trees;

        public Grove(List<String> input) {
            width = input.get(0).length();
            height = input.size();
            trees = new int[width][height];
            for (int x=0; x<width; x++) {
                for(int y=0; y<height; y++) {
                    String line = input.get(y);
                    char c = line.charAt(x);
                    trees[x][y] = Character.getNumericValue(c);
                }
            }
        }

        public int countVisibleTrees() {
            int numVisible = 0;
            for (int x=0; x<trees.length; x++) {
                for(int y=0; y<trees[0].length; y++) {
                    if (isTreeVisible(x, y)) {
                        //System.out.println("[" + x + "][" + y + "] = " + trees[x][y]);
                        numVisible++;
                    }
                }
            }
            return numVisible;
        }

        public boolean isTreeVisible(int x, int y) {
            int treeHeight = trees[x][y];
            // From the left
            boolean allShorter = true;
            for (int xIndex = 0; xIndex < x; xIndex++) {
                if (trees[xIndex][y] >= treeHeight) {
                    allShorter = false;
                    break;
                }
            }
            if (allShorter) {
                return true;
            }

            // From the right
            allShorter = true;
            for (int xIndex = x + 1; xIndex < width; xIndex++) {
                if (trees[xIndex][y] >= treeHeight) {
                    allShorter = false;
                    break;
                }
            }
            if (allShorter) {
                return true;
            }

            // From the top
            allShorter = true;
            for (int yIndex = 0; yIndex < y; yIndex++) {
                if (trees[x][yIndex] >= treeHeight) {
                    allShorter = false;
                    break;
                }
            }
            if (allShorter) {
                return true;
            }

            // From the bottom
            allShorter = true;
            for (int yIndex = y + 1; yIndex < height; yIndex++) {
                if (trees[x][yIndex] >= treeHeight) {
                    allShorter = false;
                    break;
                }
            }
            return allShorter;
        }

        public int getMaxViewScore() {
            int maxScore = 0;
            for (int x=0; x<trees.length; x++) {
                for(int y=0; y<trees[0].length; y++) {
                    int score = calculateViewScore(x, y);
                    if (score > maxScore) {
                        maxScore = score;
                    }
                }
            }
            return maxScore;
        }

        public int calculateViewScore(int x, int y) {
            int treeHeight = trees[x][y];
            // look left
            int numTreesVisible = 0;
            for (int xIndex = x - 1; xIndex >= 0; xIndex--) {
                numTreesVisible++;
                if (trees[xIndex][y] >= treeHeight) {
                    break;
                }
            }
            int score = numTreesVisible;

            // look right
            numTreesVisible = 0;
            for (int xIndex = x + 1; xIndex < width; xIndex++) {
                numTreesVisible++;
                if (trees[xIndex][y] >= treeHeight) {
                    break;
                }
            }
            score = score * numTreesVisible;

            // look up
            numTreesVisible = 0;
            for (int yIndex = y - 1; yIndex >= 0; yIndex--) {
                numTreesVisible++;
                if (trees[x][yIndex] >= treeHeight) {
                    break;
                }
            }
            score = score * numTreesVisible;

            // look down
            numTreesVisible = 0;
            for (int yIndex = y + 1; yIndex < height; yIndex++) {
                numTreesVisible++;
                if (trees[x][yIndex] >= treeHeight) {
                    break;
                }
            }
            score = score * numTreesVisible;
            return score;
        }
    }
}
