package com.rykerstudios.advent.year2018;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.rykerstudios.advent.Day;
import com.rykerstudios.advent.Point;

public class Day17 extends Day<Integer> {

	protected int minX = Integer.MAX_VALUE;
	protected int maxX = Integer.MIN_VALUE;
	protected int minY = Integer.MAX_VALUE;
	protected int maxY = Integer.MIN_VALUE;

	private final List<int[]> scans = new ArrayList<int[]>();
	private final Stack<Point> sources = new Stack<Point>();
	private final List<char[]> waterMap = new ArrayList<char[]>();

	public Day17() {
		super(40892, 0);
	}

	protected int countWater(final List<char[]> waterMap) {
		int result = 0;
		for (int y = minY; y <= maxY; y++) {
			final char[] sand = waterMap.get(y);
			for (int x = 0; x < sand.length; x++) {
				final char sq = sand[x];
				if (sq == '~' || sq == '|') {
					result++;
				}
			}
		}
		return result;
	}

	protected List<char[]> createSandClayMap(final List<int[]> scans) {
		final List<char[]> map = new ArrayList<char[]>();
		int mapWidth = maxX - minX + 1;
		// Add the sand
		for (int y = 0; y <= maxY; y++) {
			final char[] sand = new char[mapWidth];
			for (int x = 0; x <= maxX - minX; x++) {
				sand[x] = '.';
			}
			map.add(sand);
		}
		// Add the clay
		for (final int[] scan : scans) {
			for (int y = scan[1]; y <= scan[3]; y++) {
				final char[] sand = map.get(y);
				for (int x = scan[0]; x <= scan[2]; x++) {
					sand[x - minX] = '#'; // clay
				}
			}
		}
		// Add the spring
		sources.add(createSource(500, 0, minX, map));
		fill('+', 500, 0, minX, map); // change image to spring
		return map;
	}

	private Point createSource(final int x, final int y, final int minX, final List<char[]> map) {
		final Point source = new Point(x, y);
		fill('|', x, y, minX, map);
		return source;
	}

	private void fill(final char c, final int x, final int y, final int minX, final List<char[]> map) {
		final char[] sand = map.get(y);
		sand[x - minX] = c;
	}

	private void fill(final char c, final int x, final int y, final List<char[]> map) {
		final char[] sand = map.get(y);
		sand[x - minX] = c;
	}

	protected void fillWithWater(final int minX, final int minY, final int maxX, final int maxY,
			final Stack<Point> sources, final List<char[]> map) {
		boolean changed = true;
		while (!sources.isEmpty() && changed) {
			changed = false;
			// logWaterMap(map);
			final Set<Point> toAdd = new HashSet<Point>();
			final Set<Point> toRemove = new HashSet<Point>();
			for (final Point source : sources) {
				final int x = source.x;
				int y = source.y;
				if (y + 1 >= map.size()) {
					continue;
				}
				while (y < maxY) {
					final char[] sand = map.get(y + 1);
					if (sand != null && sand[x - minX] == '.') {
						fill('|', x, y, map);
						changed = true;
					} else if (sand[x - minX] != '|') {
						break;
					}
					y++;
				}
				if (y >= maxY) {
					continue;
				}
				final char[] sandBelow = map.get(y + 1);
				if (sandBelow == null || x < minX || x - minX >= sandBelow.length) {
					continue;
				}
				for (final char cs : sandBelow) {
					if (cs == '+') {
						log.warn("Not possible!");
						break;
					}
				}
				final char sq = sandBelow[x - minX];
				if (sq == '|') {
					// flows into another source
					if (y > 0) {
						// toRemove.add(source);
					}
					continue;
				} else if (sq == '.') {
					toAdd.add(createSource(x, y + 1, minX, map));
					fill('|', x, y + 1, minX, map);
					changed = true;
				} else if (sq == '#' || sq == '~') {
					// flowing sideways
					final char[] sandLevel = map.get(y);
					// flow to the left
					int wallLeft = -1;
					for (int x2 = x; x2 >= minX; x2--) {
						if (sandBelow[x2 - minX] == '|') {
							// below is flowing water to fall through
							// cannot flow on level anymore
							break;
						}
						if (sandBelow[x2 - minX] == '.') {
							// below is sand to fall through
							toAdd.add(createSource(x2, y, minX, map));
							fill('|', x2, y, map);
							changed = true;
							// cannot flow on level anymore
							break;
						}
						final char sq2 = sandLevel[x2 - minX];
						if (sq2 == '.') {
							fill('|', x2, y, map);
							changed = true;
							continue;
						} else if (sq2 == '#') {
							wallLeft = x2;
							break;
						} else if (sq2 == '|') {
							// nothing; keep going
							continue;
						} else if (sq2 == '~') {
							// should not be possible to hit a ~ on level
							wallLeft = x2;
							break;
						} else {
							// should not be any other symbol
						}
					}
					// flow to the right
					int wallRight = -1;
					for (int x2 = x; x2 - minX < sandLevel.length; x2++) {
						if (sandBelow[x2 - minX] == '|') {
							// below is flowing water to fall through
							// cannot flow on level anymore
							break;
						}
						if (sandBelow[x2 - minX] == '.') {
							// below is sand to fall through
							toAdd.add(createSource(x2, y, minX, map));
							fill('|', x2, y, map);
							changed = true;
							// cannot flow on level anymore
							break;
						}
						final char sq2 = sandLevel[x2 - minX];
						if (sq2 == '.') {
							fill('|', x2, y, map);
							changed = true;
							continue;
						} else if (sq2 == '#') {
							wallRight = x2;
							break;
						} else if (sq2 == '|') {
							// nothing; keep going
							continue;
						} else if (sq2 == '~') {
							// should not be possible to hit a ~ on level
							wallRight = x2;
							break;
						} else {
							// should not be any other symbol
						}
					}
					// flowing water is contained so replace with still water
					if (wallLeft > -1 && wallRight > -1) {
						for (int wr = wallLeft + 1; wr < wallRight; wr++) {
							fill('~', wr, y, minX, map);
							changed = true;
							toRemove.add(new Point(wr, y));
						}
					}
					toRemove.add(new Point(x, y));
				}
				if (!changed) {
					// toRemove.add(source);
				}
			}
			sources.removeAll(toRemove);
			sources.addAll(toAdd);
		}
		logWaterMap(map);
	}

	protected void fillWithWater(final List<char[]> map) {
		fillWithWater(minX, minY, maxX, maxY, sources, map);
	}

	protected List<int[]> getScans() {
		return scans;
	}

	protected List<char[]> getWaterMap() {
		return waterMap;
	}

	protected void init(final String[] lines) {
		parseGroundScans(lines);
	}

	protected void logWaterMap(final List<char[]> list) {
		final StringBuilder sb = new StringBuilder();
		sb.append("Water map:");
		int y = 0;
		for (final char[] sand : list) {
			sb.append("\n").append(y).append("\t");
			for (int x = 0; x < sand.length; x++) {
				final char sq = sand[x];
				sb.append(sq);
			}
			y++;
		}
		log.info(sb.toString());
	}

	private void parseGroundScans(final String[] lines) {
		for (final String line : lines) {
			if (line.indexOf("x") < line.indexOf("y")) {
				// x=495, y=2..7
				final int x1 = Integer.valueOf(line.substring(line.indexOf("x=") + 2, line.indexOf(",")));
				final int x2 = x1;
				final int y1 = Integer.valueOf(line.substring(line.indexOf("y=") + 2, line.indexOf("..")));
				final int y2 = Integer.valueOf(line.substring(line.indexOf("..") + 2));
				scans.add(new int[] { x1, y1, x2, y2 });
				updateMinMax(x1, y1, x2, y2);
			} else {
				// y=7, x=495..501
				final int y1 = Integer.valueOf(line.substring(line.indexOf("y=") + 2, line.indexOf(",")));
				final int y2 = y1;
				final int x1 = Integer.valueOf(line.substring(line.indexOf("x=") + 2, line.indexOf("..")));
				final int x2 = Integer.valueOf(line.substring(line.indexOf("..") + 2));
				scans.add(new int[] { x1, y1, x2, y2 });
				updateMinMax(x1, y1, x2, y2);
			}
		}
	}

	// --- Day 17: Reservoir Research ---
	//
	// You arrive in the year 18. If it weren't for the coat you got in 1018, you
	// would be very cold: the North Pole base hasn't even been constructed.
	//
	// Rather, it hasn't been constructed yet. The Elves are making a little
	// progress, but there's not a lot of liquid water in this climate, so they're
	// getting very dehydrated. Maybe there's more underground?
	//
	// You scan a two-dimensional vertical slice of the ground nearby and discover
	// that it is mostly sand with veins of clay. The scan only provides data with a
	// granularity of square meters, but it should be good enough to determine how
	// much water is trapped there. In the scan, x represents the distance to the
	// right, and y represents the distance down. There is also a spring of water
	// near the surface at x=500, y=0. The scan identifies which square meters are
	// clay (your puzzle input).
	//
	// For example, suppose your scan shows the following veins of clay:
	//
	// x=495, y=2..7
	// y=7, x=495..501
	// x=501, y=3..7
	// x=498, y=2..4
	// x=506, y=1..2
	// x=498, y=10..13
	// x=504, y=10..13
	// y=13, x=498..504
	//
	// Rendering clay as #, sand as ., and the water spring as +, and with x
	// increasing to the right and y increasing downward, this becomes:
	//
	// .. 44444455555555
	// .. 99999900000000
	// .. 45678901234567
	// 00 ......+.......
	// 01 ............#.
	// 02 .#..#.......#.
	// 03 .#..#..#......
	// 04 .#..#..#......
	// 05 .#.....#......
	// 06 .#.....#......
	// 07 .#######......
	// 08 ..............
	// 09 ..............
	// 10 ....#.....#...
	// 11 ....#.....#...
	// 12 ....#.....#...
	// 13 ....#######...
	//
	// The spring of water will produce water forever. Water can move through sand,
	// but is blocked by clay. Water always moves down when possible, and spreads to
	// the left and right otherwise, filling space that has clay on both sides and
	// falling out otherwise.
	//
	// For example, if five squares of water are created, they will flow downward
	// until they reach the clay and settle there. Water that has come to rest is
	// shown here as ~, while sand through which water has passed (but which is now
	// dry again) is shown as |:
	//
	// ......+.......
	// ......|.....#.
	// .#..#.|.....#.
	// .#..#.|#......
	// .#..#.|#......
	// .#....|#......
	// .#~~~~~#......
	// .#######......
	// ..............
	// ..............
	// ....#.....#...
	// ....#.....#...
	// ....#.....#...
	// ....#######...
	// Two squares of water can't occupy the same location. If another five squares
	// of water are created, they will settle on the first five, filling the clay
	// reservoir a little more:
	//
	// ......+.......
	// ......|.....#.
	// .#..#.|.....#.
	// .#..#.|#......
	// .#..#.|#......
	// .#~~~~~#......
	// .#~~~~~#......
	// .#######......
	// ..............
	// ..............
	// ....#.....#...
	// ....#.....#...
	// ....#.....#...
	// ....#######...
	// Water pressure does not apply in this scenario. If another four squares of
	// water are created, they will stay on the right side of the barrier, and no
	// water will reach the left side:
	//
	// ......+.......
	// ......|.....#.
	// .#..#.|.....#.
	// .#..#~~#......
	// .#..#~~#......
	// .#~~~~~#......
	// .#~~~~~#......
	// .#######......
	// ..............
	// ..............
	// ....#.....#...
	// ....#.....#...
	// ....#.....#...
	// ....#######...
	// At this point, the top reservoir overflows. While water can reach the tiles
	// above the surface of the water, it cannot settle there, and so the next five
	// squares of water settle like this:
	//
	// ......+.......
	// ......|.....#.
	// .#..#||||...#.
	// .#..#~~#|.....
	// .#..#~~#|.....
	// .#~~~~~#|.....
	// .#~~~~~#|.....
	// .#######|.....
	// ........|.....
	// ........|.....
	// ....#...|.#...
	// ....#...|.#...
	// ....#~~~~~#...
	// ....#######...
	// Note especially the leftmost |: the new squares of water can reach this tile,
	// but cannot stop there. Instead, eventually, they all fall to the right and
	// settle in the reservoir below.
	//
	// After 10 more squares of water, the bottom reservoir is also full:
	//
	// ......+.......
	// ......|.....#.
	// .#..#||||...#.
	// .#..#~~#|.....
	// .#..#~~#|.....
	// .#~~~~~#|.....
	// .#~~~~~#|.....
	// .#######|.....
	// ........|.....
	// ........|.....
	// ....#~~~~~#...
	// ....#~~~~~#...
	// ....#~~~~~#...
	// ....#######...
	// Finally, while there is nowhere left for the water to settle, it can reach a
	// few more tiles before overflowing beyond the bottom of the scanned data:
	//
	// ......+....... (line not counted: above minimum y value)
	// ......|.....#.
	// .#..#||||...#.
	// .#..#~~#|.....
	// .#..#~~#|.....
	// .#~~~~~#|.....
	// .#~~~~~#|.....
	// .#######|.....
	// ........|.....
	// ...|||||||||..
	// ...|#~~~~~#|..
	// ...|#~~~~~#|..
	// ...|#~~~~~#|..
	// ...|#######|..
	// ...|.......|.. (line not counted: below maximum y value)
	// ...|.......|.. (line not counted: below maximum y value)
	// ...|.......|.. (line not counted: below maximum y value)
	// How many tiles can be reached by the water? To prevent counting forever,
	// ignore tiles with a y coordinate smaller than the smallest y coordinate in
	// your scan data or larger than the largest one. Any x coordinate is valid. In
	// this example, the lowest y coordinate given is 1, and the highest is 13,
	// causing the water spring (in row 0) and the water falling off the bottom of
	// the render (in rows 14 through infinity) to be ignored.
	//
	// So, in the example above, counting both water at rest (~) and other sand
	// tiles the water can hypothetically reach (|), the total number of tiles the
	// water can reach is 57.
	//
	// How many tiles can the water reach within the range of y values in your scan?

	@Override
	public Integer part1() {
		init(getLines());
		waterMap.addAll(createSandClayMap(scans));
		fillWithWater(waterMap);
		final int water = countWater(waterMap);
		log.info("water count: " +water);
		return water;
	}

	// --- Part Two ---

	@Override
	public Integer part2() {
		init(getLines());
		waterMap.addAll(createSandClayMap(scans));
		fillWithWater(waterMap);
		return -1;
	}

	private void updateMinMax(final int x1, final int y1, final int x2, final int y2) {
		if (minX > x1) {
			minX = x1;
		}
		if (maxX < x1) {
			maxX = x1;
		}
		if (minY > y1) {
			minY = y1;
		}
		if (maxY < y1) {
			maxY = y1;
		}
		if (minX > x2) {
			minX = x2;
		}
		if (maxX < x2) {
			maxX = x2;
		}
		if (minY > y2) {
			minY = y2;
		}
		if (maxY < y2) {
			maxY = y2;
		}
	}

}
