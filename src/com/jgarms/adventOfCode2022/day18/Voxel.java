package com.jgarms.adventOfCode2022.day18;

import java.util.HashSet;
import java.util.Set;

public record Voxel(int x, int y, int z) {

    public Set<Voxel> getAdjacentVoxels() {
        Set<Voxel> set = new HashSet<>();
        set.add(new Voxel(x-1, y, z));
        set.add(new Voxel(x+1, y, z));
        set.add(new Voxel(x, y-1, z));
        set.add(new Voxel(x, y+1, z));
        set.add(new Voxel(x, y, z-1));
        set.add(new Voxel(x, y, z+1));
        return set;
    }
}
