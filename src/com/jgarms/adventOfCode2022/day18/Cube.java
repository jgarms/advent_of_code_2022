package com.jgarms.adventOfCode2022.day18;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Cube {
    final Set<Voxel> voxels = new HashSet<>();

    public Cube(Scanner input) {
        while (input.hasNext()) {
            String line = input.nextLine();
            String[] coords = line.split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            int z = Integer.parseInt(coords[2]);
            voxels.add(new Voxel(x, y, z));
        }
    }

    public int getSurfaceArea() {
        int numSides = 0;
        for (Voxel voxel: voxels) {
            Set<Voxel> adjacentVoxels = voxel.getAdjacentVoxels();
            adjacentVoxels.removeAll(voxels);
            numSides += adjacentVoxels.size();
        }
        return numSides;
    }
}
