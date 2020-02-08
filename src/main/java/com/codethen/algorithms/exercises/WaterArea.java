package com.codethen.algorithms.exercises;

import java.util.*;

public class WaterArea {

    public static void main(String... args) {

        System.out.println(waterArea(new int[]{
                //  0, 8, 0, 0, 5, 0, 0, 10, 0, 0, 1, 1, 0, 3, 2, 3
                2, 1, 2
        }));
    }

    static Collection<List<Integer>> zones;
    static int[] heights;

    public static int waterArea(int[] _heights) {

        heights = _heights;
        /** A set avoids repeated zones when we gather RTL */
        zones = new HashSet<>();

        gatherZones(0, heights.length, 1); // Gather zones LTR
        System.out.println("Zones LTR: " + zones);

        gatherZones(heights.length - 1, -1, -1); // Gather zones RTL
        System.out.println("Zones RTL: " + zones);

        return countWaterInZones(zones);
    }

    /** Calculates accumulated water - TODO: there may be repeated zones */
    private static int countWaterInZones(Collection<List<Integer>> zones) {

        int total = 0;

        for (List<Integer> zone : zones) {
            total += countWaterInZone(zone.get(0), zone.get(1));
        }

        return total;
    }

    private static int countWaterInZone(int start, int end) {

        int total = 0;
        int maxHeight = Math.min(heights[start], heights[end]);
        for (int i = start+1; i < end; i++) {
            total += maxHeight - heights[i];
        }

        return total;
    }

    static void gatherZones(int start, int end, int inc) {

        int index = start;
        while (true) {
            int startWall = findStartWall(index, end, inc);
            if (startWall < 0) return;
            int endWall = findEndWall(startWall, end, inc);
            if (endWall < 0) return;
            final List<Integer> zone = Arrays.asList(startWall, endWall);
            Collections.sort(zone);
            zones.add(zone);
            index = endWall;
        }
    }

    /** Looks for a start wall (i.e. when the next block is smaller) */
    static int findStartWall(int start, int end, int inc) {

        int height = heights[start];

        for (int i = start; i != end; i += inc) {
            if (heights[i] > height) {
                height = heights[i];
            } else if (heights[i] < height) {
                return i - inc;
            }
        }

        return -1; // not found
    }

    /** Looks for an end wall (i.e. when it's equal or taller to the start wall) */
    static int findEndWall(int start, int end, int inc) {

        for (int i = start + inc; i != end; i += inc) {
            if (heights[i] >= heights[start]) {
                return i;
            }
        }

        return -1; // not found
    }
}

