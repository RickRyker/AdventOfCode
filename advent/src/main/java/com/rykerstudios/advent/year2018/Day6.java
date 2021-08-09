package com.rykerstudios.advent.year2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rykerstudios.advent.Day;

public class Day6 extends Day<Integer> {

	List<int[]> coords;

	int maxX;
	int maxY;
	int[][] grid;
	public Day6() {
		super(3989, 49715);
	}

	protected void fillGridWithClosest() {
		int pt = 0;
		for (final int[] coord:coords) {
			pt++;
			final int x = coord[0];
			final int y = coord[1];
			grid[x][y] = pt;
		}
		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				grid[x][y] = findClosest(x, y);
			}
		}
	}

	protected void fillGridWithTotalDistances() {
		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				int totalDistance = 0;
				for (int[] coord : coords) {
					totalDistance += getManhattanDistance(x, y, coord[0], coord[1]);
				}
				if (totalDistance < 10000) {
					grid[x][y] = totalDistance;
				}
			}
		}
	}

	private int findClosest(final int x, final int y) {
		int pt = 0;
		int closest = 0;
		int minDist = Integer.MAX_VALUE;
		for (int[] coord : coords) {
			pt++;
			final int distance = getManhattanDistance(x, y, coord[0], coord[1]);
			if (minDist == distance) {
				closest = 0;
			}
			if (minDist > distance) {
				minDist = distance;
				closest = pt;
			}
		}
		return closest;
	}

	protected Map<Integer, Integer> getAreaSizes() {
		final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				final int area = grid[x][y];
				if (area > 0) {
					if (map.get(area) == null) {
						map.put(area, 1);
					} else {
						map.put(area, map.get(area) + 1);
					}
				}
			}
		}
		for (int x = 0; x < maxX; x++) {
			map.remove(grid[x][0]);
			map.remove(grid[x][maxY - 1]);
		}
		for (int y = 0; y < maxY; y++) {
			map.remove(grid[0][y]);
			map.remove(grid[maxX - 1][y]);
		}
		return map;
	}

	protected List<int[]> getCoordinates() {
		final List<int[]> coords = new ArrayList<int[]>();
		for (final String line : getLines()) {
			System.out.println(line);
			final String[] a = line.split(",");
			final int[] coord = new int[2];
			coord[0] = Integer.parseInt(a[0].trim());
			coord[1] = Integer.parseInt(a[1].trim());
			coords.add(coord);
		}
		return coords;
	}

	private int getManhattanDistance(final int x1, final int y1, final int x2, final int y2) {
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}

	protected int[] getMaxCoords(final List<int[]> coordList) {
		int maxX = 0;
		int maxY = 0;
		for (final int[] coords : coordList) {
			if (maxX < coords[0]) {
				maxX = coords[0];
			}
			if (maxY < coords[1]) {
				maxY = coords[1];
			}
		}
		final int[] maxCoord = new int[] { maxX, maxY };
		return maxCoord;
	}

	protected void init(final List<int[]> list) {
		coords = list;
		final int[] maxCoords = getMaxCoords(coords);
		maxX = maxCoords[0] + 1;
		maxY = maxCoords[1] + 1;
		grid = new int[maxX][maxY];
	}

	//
	// --- Day 6: Chronal Coordinates ---
	//
	// The device on your wrist beeps several times, and once again you feel like
	// you're falling.
	//
	// "Situation critical," the device announces.
	// "Destination indeterminate. Chronal interference detected. Please specify new
	// target coordinates."
	//
	// The device then produces a list of coordinates (your puzzle input). Are they
	// places it thinks are safe or dangerous? It recommends you check manual page
	// 729. The Elves did not give you a manual.
	//
	// If they're dangerous, maybe you can minimize the danger by finding the
	// coordinate that gives the largest distance from the other points.
	//
	// Using only the Manhattan distance, determine the area around each coordinate
	// by counting the number of integer X,Y locations that are closest to that
	// coordinate (and aren't tied in distance to any other coordinate).
	//
	// Your goal is to find the size of the largest area that isn't infinite. For
	// example, consider the following list of coordinates:
	//
	// 1, 1
	// 1, 6
	// 8, 3
	// 3, 4
	// 5, 5
	// 8, 9

	// If we name these coordinates A through F, we can draw them on a grid, putting
	// 0,0 at the top left:

	// ..........
	// .A........
	// ..........
	// ........C.
	// ...D......
	// .....E....
	// .B........
	// ..........
	// ..........
	// ........F.

	// This view is partial - the actual grid extends infinitely in all directions.
	// Using the Manhattan distance, each location's closest coordinate can be
	// determined, shown here in lowercase:

	// aaaaa.cccc
	// aAaaa.cccc
	// aaaddecccc
	// aadddeccCc
	// ..dDdeeccc
	// bb.deEeecc
	// bBb.eeee..
	// bbb.eeefff
	// bbb.eeffff
	// bbb.ffffFf

	// Locations shown as . are equally far from two or more coordinates, and so
	// they don't count as being closest to any.
	//
	// In this example, the areas of coordinates A, B, C, and F are infinite - while
	// not shown here, their areas extend forever outside the visible grid. However,
	// the areas of coordinates D and E are finite: D is closest to 9 locations, and
	// E is closest to 17 (both including the coordinate's location itself).
	// Therefore, in this example, the size of the largest area is 17.

	// What is the size of the largest area that isn't infinite?

	@Override
	public Integer part1() {
		init(getCoordinates());
		fillGridWithClosest();
		final List<Integer> sizes = new ArrayList<Integer>(getAreaSizes().values());
		Collections.sort(sizes);
		Collections.reverse(sizes);
		System.out.println(sizes);
		System.out.println(sizes.get(0));
		return sizes.get(0);
	}

	// --- Part Two ---
	//
	// On the other hand, if the coordinates are safe, maybe the best you can do is
	// try to find a region near as many coordinates as possible.
	//
	// For example, suppose you want the sum of the Manhattan distance to all of the
	// coordinates to be less than 32. For each location, add up the distances to
	// all of the given coordinates; if the total of those distances is less than
	// 32, that location is within the desired region. Using the same coordinates as
	// above, the resulting region looks like this:
	//
	// ..........
	// .A........
	// ..........
	// ...###..C.
	// ..#D###...
	// ..###E#...
	// .B.###....
	// ..........
	// ..........
	// ........F.
	//
	// In particular, consider the highlighted location 4,3 located at the top
	// middle of the region. Its calculation is as follows, where abs() is the
	// absolute value function:
	//
	// Distance to coordinate A: abs(4-1) + abs(3-1) = 5
	// Distance to coordinate B: abs(4-1) + abs(3-6) = 6
	// Distance to coordinate C: abs(4-8) + abs(3-3) = 4
	// Distance to coordinate D: abs(4-3) + abs(3-4) = 2
	// Distance to coordinate E: abs(4-5) + abs(3-5) = 3
	// Distance to coordinate F: abs(4-8) + abs(3-9) = 10
	// Total distance: 5 + 6 + 4 + 2 + 3 + 10 = 30
	//
	// Because the total distance to all coordinates (30) is less than 32, the
	// location is within the region.
	//
	// This region, which also includes coordinates D and E, has a total size of 16.
	//
	// Your actual region will need to be much larger than this example, though,
	// instead including all locations with a total distance of less than 10000.
	//
	// What is the size of the region containing all locations which have a total
	// distance to all given coordinates of less than 10000?

	@Override
	public Integer part2() {
		init(getCoordinates());
		fillGridWithTotalDistances();
		int count = 0;
		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				if (grid[x][y] > 0 && grid[x][y] < 10000) {
					count++;
				}
			}
		}
		return count; // 49715 / 125658
	}

}
