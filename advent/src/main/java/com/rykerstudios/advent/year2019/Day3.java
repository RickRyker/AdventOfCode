package com.rykerstudios.advent.year2019;

import com.rykerstudios.advent.Day;

public class Day3 extends Day<Integer> {

	public Day3() {
		super(2180, 0);
	}

	// --- Day 3: Crossed Wires ---

	// The gravity assist was successful, and you're well on your way to the
	// Venus refuelling station. During the rush back on Earth, the fuel
	// management system wasn't completely installed, so that's next on the
	// priority list.

	// Opening the front panel reveals a jumble of wires. Specifically, two
	// wires are connected to a central port and extend outward on a grid.
	// You trace the path each wire takes as it leaves the central port, one
	// wire per line of text (your puzzle input).

	// The wires twist and turn, but the two wires occasionally cross paths.
	// To fix the circuit, you need to find the intersection point closest to
	// the central port. Because the wires are on a grid, use the Manhattan
	// distance for this measurement. While the wires do technically cross
	// right at the central port where they both start, this point does not
	// count, nor does a wire count as crossing with itself.

	// For example, if the first wire's path is R8,U5,L5,D3, then starting from
	// the central port (o), it goes right 8, up 5, left 5, and finally down 3:

	// ...........
	// ...........
	// ...........
	// ....+----+.
	// ....|....|.
	// ....|....|.
	// ....|....|.
	// .........|.
	// .o-------+.
	// ...........

	// Then, if the second wire's path is U7,R6,D4,L4, it goes up 7, right 6,
	// down 4, and left 4:

	// ...........
	// .+-----+...
	// .|.....|...
	// .|..+--X-+.
	// .|..|..|.|.
	// .|.-X--+.|.
	// .|..|....|.
	// .|.......|.
	// .o-------+.
	// ...........

	// These wires cross at two locations (marked X), but the lower-left one
	// is closer to the central port: its distance is 3 + 3 = 6.

	// Here are a few more examples:

	// R75,D30,R83,U83,L12,D49,R71,U7,L72
	// U62,R66,U55,R34,D71,R55,D58,R83 = distance 159

	// R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
	// U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 = distance 135

	// What is the Manhattan distance from the central port to the closest
	// intersection?

	@Override
	public Integer part1() {
		final String[] wires = getLines();
		final String[] wire1 = wires[0].split(",");
		final String[] wire2 = wires[1].split(",");
		final int[] bounds1 = getBounds(wire1);
		final int[] bounds2 = getBounds(wire2);
		final int[] bounds = getMaxBounds(bounds1, bounds2);
		final int[][] signalGrid1 = initSignalGrid(bounds);
		final int[][] signalGrid2 = initSignalGrid(bounds);
		fillSignalGrid(signalGrid1, bounds, wire1);
		fillSignalGrid(signalGrid2, bounds, wire2);

		// compare the grids
		int originX = 0 - bounds[1];
		int originY = 0 - bounds[2];
		int minDistance = Integer.MAX_VALUE;
		for (int y = 0; y < signalGrid1.length; y++) {
			for (int x = 0; x < signalGrid1[0].length; x++) {
				if (signalGrid1[y][x] > 0 && signalGrid2[y][x] > 0) {
					final int distance = Math.abs(originY - y) + Math.abs(originX - x);
					if (minDistance > distance) {
						minDistance = distance;
					}
					System.out.println("Intersected @ (up,right):" + (originY - y) + "," + (originX - x) + " distance "
							+ distance);
				}
			}
		}
		return minDistance;
	}

	protected String arrayToString(final int[] bounds) {
		final StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < bounds.length; i++) {
			sb.append(bounds[i]).append(",");
		}
		sb.setLength(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}

	protected <T> String gridToString(final T[][] grid) {
		final StringBuilder sb = new StringBuilder();
		for (int y = grid.length; y > 0; y--) {
			for (int x = 0; x < grid[y - 1].length; x++) {
				sb.append(grid[y - 1][x]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	protected String[][] createGrid(final String[] wire) {
		final int[] bounds = getBounds(wire);
		System.out.println(arrayToString(bounds));
		final String[][] grid = initGrid(bounds);
		return fillGrid(grid, bounds, wire);
	}

	protected String[][] fillGrid(final String[][] grid, final int[] bounds, final String[] wire) {
		int x = 0 - bounds[1];
		int y = 0 - bounds[2];
		int dx = 0;
		int dy = 0;
		String t = "o";
		for (final String s : wire) {
			final String dir = s.substring(0, 1);
			final int len = Integer.parseInt(s.substring(1));
			if ("U".equals(dir)) {
				dx = 0;
				dy = 1;
				t = "|";
			}
			if ("D".equals(dir)) {
				dx = 0;
				dy = -1;
				t = "|";
			}
			if ("R".equals(dir)) {
				dx = 1;
				dy = 0;
				t = "-";
			}
			if ("L".equals(dir)) {
				dx = -1;
				dy = 0;
				t = "-";
			}
			for (int i = 0; i < len; i++) {
				x += dx;
				y += dy;
				grid[y][x] = t;
			}
			grid[y][x] = "+";
		}
		return grid;
	}

	protected int[][] fillSignalGrid(final int[][] grid, final int[] bounds, final String[] wire) {
		int x = 0 - bounds[1];
		int y = 0 - bounds[2];
		int dx = 0;
		int dy = 0;
		int signal = 0;
		for (final String s : wire) {
			final String dir = s.substring(0, 1);
			final int len = Integer.parseInt(s.substring(1));
			if ("U".equals(dir)) {
				dx = 0;
				dy = 1;
			}
			if ("D".equals(dir)) {
				dx = 0;
				dy = -1;
			}
			if ("R".equals(dir)) {
				dx = 1;
				dy = 0;
			}
			if ("L".equals(dir)) {
				dx = -1;
				dy = 0;
			}
			for (int i = 0; i < len; i++) {
				x += dx;
				y += dy;
				signal += 1;
				grid[y][x] = signal;
			}
		}
		return grid;
	}

	protected int[] getMaxBounds(final int[] bounds1, final int[] bounds2) {
		final int[] bounds = { 0, 0, 0, 0 };
		bounds[0] = Math.max(bounds1[0], bounds2[0]);
		bounds[1] = Math.min(bounds1[1], bounds2[1]);
		bounds[2] = Math.min(bounds1[2], bounds2[2]);
		bounds[3] = Math.max(bounds1[3], bounds2[3]);
		return bounds;
	}

	protected int[] getBounds(final String[] wire) {
		int minL = 0;
		int maxR = 0;
		int maxU = 0;
		int minD = 0;
		int lr = 0;
		int ud = 0;
		for (String s : wire) {
			final String dir = s.substring(0, 1);
			final int len = Integer.parseInt(s.substring(1));
			if ("U".equals(dir)) {
				ud += len;
				if (maxU < ud) {
					maxU = ud;
				}
			}
			if ("D".equals(dir)) {
				ud -= len;
				if (minD > ud) {
					minD = ud;
				}
			}
			if ("R".equals(dir)) {
				lr += len;
				if (maxR < lr) {
					maxR = lr;
				}
			}
			if ("L".equals(dir)) {
				lr -= len;
				if (minL > lr) {
					minL = lr;
				}
			}
		}
		return new int[] { maxU, minL, minD, maxR };
	}

	protected String[][] initGrid(final int[] bounds) {
		final int height = bounds[0] - bounds[2] + 1;
		final int width = bounds[3] - bounds[1] + 1;
		final String[][] grid = new String[height][width];
		for (int y = 0; y < height; y++) {
			grid[y] = new String[width];
			for (int x = 0; x < width; x++) {
				grid[y][x] = ".";
			}
		}
		final int originY = 0 - bounds[2];
		final int originX = 0 - bounds[1];
		grid[originY][originX] = "o";
		return grid;
	}

	protected int[][] initSignalGrid(final int[] bounds) {
		final int height = bounds[0] - bounds[2] + 1;
		final int width = bounds[3] - bounds[1] + 1;
		final int[][] grid = new int[height][width];
		for (int y = 0; y < height; y++) {
			grid[y] = new int[width];
			for (int x = 0; x < width; x++) {
				grid[y][x] = 0;
			}
		}
		return grid;
	}

	// --- Part Two ---

	// It turns out that this circuit is very timing-sensitive; you actually
	// need to minimize the signal delay.

	// To do this, calculate the number of steps each wire takes to reach each
	// intersection; choose the intersection where the sum of both wires' steps
	// is lowest. If a wire visits a position on the grid multiple times, use
	// the steps value from the first time it visits that position when
	// calculating the total value of a specific intersection.

	// The number of steps a wire takes is the total number of grid squares the
	// wire has entered to get to that location, including the intersection
	// being considered. Again consider the example from above:

	// ...........
	// .+-----+...
	// .|.....|...
	// .|..+--X-+.
	// .|..|..|.|.
	// .|.-X--+.|.
	// .|..|....|.
	// .|.......|.
	// .o-------+.
	// ...........

	// In the above example, the intersection closest to the central port is reached
	// after 8+5+5+2 = 20 steps by the first wire and 7+6+4+3 = 20 steps by the
	// second wire for a total of 20+20 = 40 steps.

	// However, the top-right intersection is better: the first wire takes only
	// 8+5+2 = 15 and the second wire takes only 7+6+2 = 15, a total of 15+15 = 30
	// steps.

	// Here are the best steps for the extra examples from above:

	// R75,D30,R83,U83,L12,D49,R71,U7,L72
	// U62,R66,U55,R34,D71,R55,D58,R83 = 610 steps

	// R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
	// U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 = 410 steps

	// What is the fewest combined steps the wires must take to reach an
	// intersection?

	@Override
	public Integer part2() {
		final String[] wires = getLines();
		final String[] wire1 = wires[0].split(",");
		final String[] wire2 = wires[1].split(",");
		final int[] bounds1 = getBounds(wire1);
		final int[] bounds2 = getBounds(wire2);
		final int[] bounds = getMaxBounds(bounds1, bounds2);
		final int[][] signalGrid1 = initSignalGrid(bounds);
		final int[][] signalGrid2 = initSignalGrid(bounds);
		fillSignalGrid(signalGrid1, bounds, wire1);
		fillSignalGrid(signalGrid2, bounds, wire2);

		// compare the grids
		int originX = 0 - bounds[1];
		int originY = 0 - bounds[2];
		int minSignal = Integer.MAX_VALUE;
		for (int y = 0; y < signalGrid1.length; y++) {
			for (int x = 0; x < signalGrid1[0].length; x++) {
				if (signalGrid1[y][x] > 0 && signalGrid2[y][x] > 0) {
					final int signal = signalGrid1[y][x] + signalGrid2[y][x];
					if (minSignal > signal) {
						minSignal = signal;
					}
					System.out.println(
							"Intersected @ (up,right):" + (originY - y) + "," + (originX - x) + " signal " + signal);
				}
			}
		}
		return minSignal;
	}

}
