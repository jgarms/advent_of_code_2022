package com.jgarms.adventOfCode2022.day18;

import java.util.*;

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

    public int getExposedSurfaceArea() {
        // Bounding box 1 larger than our min and max values
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, minZ = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE, maxZ = Integer.MIN_VALUE;
        for (Voxel voxel: voxels) {
            minX = Math.min(voxel.x(), minX);
            minY = Math.min(voxel.y(), minY);
            minZ = Math.min(voxel.z(), minZ);

            maxX = Math.max(voxel.x(), maxX);
            maxY = Math.max(voxel.y(), maxY);
            maxZ = Math.max(voxel.z(), maxZ);
        }
        minX--;
        minY--;
        minZ--;
        maxX++;
        maxY++;
        maxZ++;

        // Flood-fill using BFS of all reachable voxels, starting at the min corner
        Queue<Voxel> q = new LinkedList<>();
        q.add(new Voxel(minX, minY, minZ));
        int numSides = 0;
        Set<Voxel> seen = new HashSet<>();
        while (!q.isEmpty()) {
            Voxel voxel = q.poll();
            if (    voxel.x() < minX || voxel.x() > maxX ||
                    voxel.y() < minY || voxel.y() > maxY ||
                    voxel.z() < minZ || voxel.z() > maxZ) {

                // Off the edge; ignore
                continue;
            }

            if (seen.contains(voxel)) {
                // Already seen; ignore
                continue;
            }
            seen.add(voxel);

            for (Voxel adjacentVoxel: voxel.getAdjacentVoxels()) {
                if (voxels.contains(adjacentVoxel)) {
                    // This is an edge
                    numSides++;
                } else {
                    // This is more empty space. Search its neighbors
                    q.add(adjacentVoxel);
                }
            }
        }
        return numSides;
    }
}
