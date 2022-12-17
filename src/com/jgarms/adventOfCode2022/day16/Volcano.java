package com.jgarms.adventOfCode2022.day16;

import java.util.*;

public class Volcano {
    final Map<String, Valve> valves = new HashMap<>();
    final Map<String, Map<String, Integer>> sourceToDestDistance = new HashMap<>();

    public Volcano(Scanner input) {
        while (input.hasNext()) {
            Valve valve = new Valve(input.nextLine());
            valves.put(valve.name, valve);
        }
        computeDistances();
    }

    private void computeDistances() {
        // Floyd-Warshall
        // Populate our matrix with known distances: 0 for self, 1 for children, MAX for unknown
        for (Valve sourceValve: valves.values()) {
            Map<String, Integer> destToDistance = new HashMap<>();
            sourceToDestDistance.put(sourceValve.name, destToDistance);
            for (String destName: valves.keySet()) {
                int distance;
                if (sourceValve.name.equals(destName)) {
                    distance = 0;
                }
                else if (sourceValve.children.contains(destName)) {
                    distance = 1;
                } else {
                    distance = Integer.MAX_VALUE;
                }
                destToDistance.put(destName, distance);
            }
        }

        // n^3 iteration, compute all distances
        for (String k: valves.keySet()) {
            for (String i: valves.keySet()) {
                for (String j: valves.keySet()) {
                    int costFromIToJ = sourceToDestDistance.get(i).get(j);
                    int costFromIToK = sourceToDestDistance.get(i).get(k);
                    int costFromKToJ = sourceToDestDistance.get(k).get(j);
                    int costViaK = add(costFromIToK, costFromKToJ);
                    if (costViaK < costFromIToJ) {
                        sourceToDestDistance.get(i).put(j, costViaK);
                    }
                }
            }
        }
    }

    public int add(int a, int b) {
        if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return a + b;
    }

    public void calculateOpenOrderings(String pos, List<String> openValves, int timeRemaining, List<List<String>> allOrderings) {
        for (Valve valve: valves.values()) {
            String valveName = valve.name;
            if (valve.flowRate > 0 && !openValves.contains(valveName)) {
                int distance = sourceToDestDistance.get(pos).get(valveName);
                if (timeRemaining > distance) {
                    List<String> openValvesCopy = new ArrayList<>(openValves);
                    openValvesCopy.add(valveName);
                    calculateOpenOrderings(valveName, openValvesCopy, timeRemaining - distance - 1, allOrderings);
                }
            }
        }
        allOrderings.add(openValves);
    }

    public int getPressureReleased(List<String> openOrdering, int time) {
        int sum = 0;
        String pos = "AA";
        for (String valveName: openOrdering) {
            int distance = sourceToDestDistance.get(pos).get(valveName);
            time -= (distance + 1);
            int volume = valves.get(valveName).flowRate * time;
            sum += volume;
            pos = valveName;
        }
        return sum;
    }

    public int getMaxPressureReleased(int time) {
        List<List<String>> orderings = new ArrayList<>();
        calculateOpenOrderings("AA", new ArrayList<>(), time, orderings);
        int max = 0;
        for (List<String> ordering: orderings) {
            int pressure = getPressureReleased(ordering, time);
            if (pressure > max) {
                max = pressure;
            }
        }
        return max;
    }

    @SuppressWarnings("unused")
    public static String getOrderingString(List<String> ordering) {
        StringBuilder sb = new StringBuilder();
        for (String s: ordering) {
            sb.append(s);
        }
        return sb.toString();
    }

    record OrderingAndScore(List<String> ordering, int score) {
        public boolean overlaps(OrderingAndScore o) {
            for (String order:ordering()) {
                if (o.ordering().contains(order)) {
                    return true;
                }
            }
            return false;
        }
    }

    public int getMaxPressureReleasedWithElephant(int time) {
        List<List<String>> orderings = new ArrayList<>();
        calculateOpenOrderings("AA", new ArrayList<>(), time, orderings);

        // score all the orderings
        List<OrderingAndScore> scores = new ArrayList<>();
        for (List<String> ordering: orderings) {
            int score= getPressureReleased(ordering, time);
            scores.add(new OrderingAndScore(ordering, score));
        }

        int maxScore = 0;
        for (int humanIndex = 0; humanIndex<scores.size(); humanIndex++) {
            OrderingAndScore humanScore = scores.get(humanIndex);
            for (int elephantIndex = humanIndex + 1; elephantIndex <scores.size(); elephantIndex++) {
                OrderingAndScore elephantScore = scores.get(elephantIndex);
                int combinedScore = humanScore.score() + elephantScore.score();
                if (combinedScore > maxScore && !humanScore.overlaps(elephantScore)) {
                    maxScore = combinedScore;
                }
            }
        }
        return maxScore;
    }

}
