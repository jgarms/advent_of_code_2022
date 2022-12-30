package com.jgarms.adventOfCode2022.day19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class State implements Cloneable {
    final Blueprint blueprint;
    int minutesRemaining;
    int oreRobots;
    int clayRobots;
    int obsidianRobots;
    int geodeRobots;

    int ore;
    int clay;
    int obsidian;
    int geodes;

    public State(Blueprint blueprint, int minutes) {
        this.blueprint = blueprint;
        this.oreRobots = 1;
        this.minutesRemaining = minutes;
    }

    public List<State> getNextStates() {
        if (minutesRemaining == 0) {
            return Collections.emptyList();
        }
        // If the max so far is more than we could reach if we built geode robots on every turn, skip
        if (blueprint.maxSoFar >= geodes + geodeRobots * minutesRemaining + (minutesRemaining * (minutesRemaining+1))/2) {
            return Collections.emptyList();
        }
        List<State> states = new ArrayList<>();
        // cover each option: build each type of robot if possible or just keep mining
        if (ore >= blueprint.geodeRobotOreCost && obsidian >= blueprint.geodeRobotObsidianCost) {
            State state = this.tick();
            state.ore -= blueprint.geodeRobotOreCost;
            state.obsidian -= blueprint.geodeRobotObsidianCost;
            state.geodeRobots++;
            states.add(state);
            // If we can build a geode robot, we should always do so
            return states;
        }
        if (ore >= blueprint.obsidianRobotOreCost && clay >= blueprint.obsidianRobotClayCost) {
            State state = this.tick();
            state.ore -= blueprint.obsidianRobotOreCost;
            state.clay -= blueprint.obsidianRobotClayCost;
            state.obsidianRobots++;
            states.add(state);
        }
        if (ore >= blueprint.clayRobotOreCost) {
            State state = this.tick();
            state.ore -= blueprint.clayRobotOreCost;
            state.clayRobots++;
            states.add(state);
        }
        if (ore >= blueprint.oreRobotOreCost) {
            State state = this.tick();
            state.ore -= blueprint.oreRobotOreCost;
            state.oreRobots++;
            states.add(state);
        }
        // Just mine, no new robots
        states.add(this.tick());

        return states;
    }

    public State tick() {
        try {
            State copy = (State)super.clone();
            copy.minutesRemaining = copy.minutesRemaining - 1;
            copy.ore = copy.ore + copy.oreRobots;
            copy.clay = copy.clay + copy.clayRobots;
            copy.obsidian = copy.obsidian + copy.obsidianRobots;
            copy.geodes = copy.geodes + copy.geodeRobots;
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getMaxGeodes() {
        int max = geodes;
        for (State state: getNextStates()) {
            if (!blueprint.seen.contains(state)) {
                blueprint.seen.add(state);
                max = Math.max(max, state.getMaxGeodes());
                blueprint.maxSoFar = Math.max(blueprint.maxSoFar, max);
            }
        }
        return max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (minutesRemaining != state.minutesRemaining) return false;
        if (oreRobots != state.oreRobots) return false;
        if (clayRobots != state.clayRobots) return false;
        if (obsidianRobots != state.obsidianRobots) return false;
        if (geodeRobots != state.geodeRobots) return false;
        if (ore != state.ore) return false;
        if (clay != state.clay) return false;
        if (obsidian != state.obsidian) return false;
        if (geodes != state.geodes) return false;
        if (!blueprint.equals(state.blueprint)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = blueprint.hashCode();
        result = 31 * result + minutesRemaining;
        result = 31 * result + oreRobots;
        result = 31 * result + clayRobots;
        result = 31 * result + obsidianRobots;
        result = 31 * result + geodeRobots;
        result = 31 * result + ore;
        result = 31 * result + clay;
        result = 31 * result + obsidian;
        result = 31 * result + geodes;
        return result;
    }
}
