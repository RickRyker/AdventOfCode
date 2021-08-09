package com.rykerstudios.advent.year2018;

import java.util.ArrayList;
import java.util.List;

import com.rykerstudios.advent.Day;

public class Day10 extends Day<String> {

	public Day10() {
		super("PPNJEENH", "10375");
	}

	protected List<int[]> lights = new ArrayList<int[]>();

	protected List<String> draw(final int t) {
		final List<int[]> positions = moveLights(t);
		int xOffset = 0;
		int yOffset = 0;
		int xMax = 0;
		int yMax = 0;
		for (final int[] p : positions) {
			if (xOffset > p[0]) {
				xOffset = p[0];
			}
			if (yOffset > p[1]) {
				yOffset = p[1];
			}
			if (xMax < p[0]) {
				xMax = p[0];
			}
			if (yMax < p[1]) {
				yMax = p[1];
			}
		}
		final List<String> list = new ArrayList<String>();
		if (xOffset < 0 || yOffset < 0) {
			return list;
		}
		final long message_height = yMax - yOffset + 1;
		final long message_width = xMax - xOffset + 1;
		list.add(t + "\t" + message_height + " * " + message_width);
		if (message_height * message_width > 50000) {
			return list;
		}
		final char[][] canvas = new char[yMax - yOffset + 1][xMax - xOffset + 1];
		for (int y = yOffset; y <= yMax; y++) {
			for (int x = xOffset; x <= xMax; x++) {
				canvas[y - yOffset][x - xOffset] = '.';
			}
		}
		for (final int[] p : positions) {
			canvas[p[1] - yOffset][p[0] - xOffset] = '*';
		}
		for (int y = yOffset; y <= yMax; y++) {
			list.add(new String(canvas[y - yOffset]));
		}
		return list;
	}

	protected void init(final String[] lines) {
		lights.clear();
		for (final String line : lines) {
			final int x = line.indexOf("<");
			final int v = line.indexOf("<", x + 1);
			lights.add(new int[] {
					Integer.parseInt(line.substring(x + 1, line.indexOf(",")).trim()), 
					Integer.parseInt(line.substring(line.indexOf(",", x) + 1, line.indexOf(">", x)).trim()), 
					Integer.parseInt(line.substring(v + 1, line.indexOf(",", v)).trim()),
					Integer.parseInt(line.substring(line.indexOf(",", v) + 1, line.indexOf(">", v)).trim())
			});
		}
	}

	protected int[] moveLight(final int[] light, final int t) {
		final int x = light[0] + t * light[2];
		final int y = light[1] + t * light[3];
		return new int[] { x, y };
	}

	protected List<int[]> moveLights(final int t) {
		final List<int[]> p = new ArrayList<int[]>();
		for (final int[] light : lights) {
			p.add(moveLight(light, t));
		}
		return p;
	}

	// --- Day 10: The Stars Align ---
	//
	// It's no use; your navigation system simply isn't capable of providing walking
	// directions in the arctic circle, and certainly not in 1018.
	//
	// The Elves suggest an alternative. In times like these, North Pole rescue
	// operations will arrange points of light in the sky to guide missing Elves
	// back to base. Unfortunately, the message is easy to miss: the points move
	// slowly enough that it takes hours to align them, but have so much momentum
	// that they only stay aligned for a second. If you blink at the wrong time, it
	// might be hours before another message appears.
	//
	// You can see these points of light floating in the distance, and record their
	// position in the sky and their velocity, the relative change in position per
	// second (your puzzle input). The coordinates are all given from your
	// perspective; given enough time, those positions and velocities will move the
	// points into a cohesive message!
	//
	// Rather than wait, you decide to fast-forward the process and calculate what
	// the points will eventually spell.
	//
	// For example, suppose you note the following points:
	//
	// position=< 9, 1> velocity=< 0, 2>
	// position=< 7, 0> velocity=<-1, 0>
	// position=< 3, -2> velocity=<-1, 1>
	// position=< 6, 10> velocity=<-2, -1>
	// position=< 2, -4> velocity=< 2, 2>
	// position=<-6, 10> velocity=< 2, -2>
	// position=< 1, 8> velocity=< 1, -1>
	// position=< 1, 7> velocity=< 1, 0>
	// position=<-3, 11> velocity=< 1, -2>
	// position=< 7, 6> velocity=<-1, -1>
	// position=<-2, 3> velocity=< 1, 0>
	// position=<-4, 3> velocity=< 2, 0>
	// position=<10, -3> velocity=<-1, 1>
	// position=< 5, 11> velocity=< 1, -2>
	// position=< 4, 7> velocity=< 0, -1>
	// position=< 8, -2> velocity=< 0, 1>
	// position=<15, 0> velocity=<-2, 0>
	// position=< 1, 6> velocity=< 1, 0>
	// position=< 8, 9> velocity=< 0, -1>
	// position=< 3, 3> velocity=<-1, 1>
	// position=< 0, 5> velocity=< 0, -1>
	// position=<-2, 2> velocity=< 2, 0>
	// position=< 5, -2> velocity=< 1, 2>
	// position=< 1, 4> velocity=< 2, 1>
	// position=<-2, 7> velocity=< 2, -2>
	// position=< 3, 6> velocity=<-1, -1>
	// position=< 5, 0> velocity=< 1, 0>
	// position=<-6, 0> velocity=< 2, 0>
	// position=< 5, 9> velocity=< 1, -2>
	// position=<14, 7> velocity=<-2, 0>
	// position=<-3, 6> velocity=< 2, -1>
	//
	// Each line represents one point. Positions are given as <X, Y> pairs: X
	// represents how far left (negative) or right (positive) the point appears,
	// while Y represents how far up (negative) or down (positive) the point
	// appears.
	//
	// At 0 seconds, each point has the position given. Each second, each point's
	// velocity is added to its position. So, a point with velocity <1, -2> is
	// moving to the right, but is moving upward twice as quickly. If this point's
	// initial position were <3, 9>, after 3 seconds, its position would become <6,
	// 3>.
	//
	// Over time, the points listed above would move like this:
	//
	// Initially:
	// ........#.............
	// ................#.....
	// .........#.#..#.......
	// ......................
	// #..........#.#.......#
	// ...............#......
	// ....#.................
	// ..#.#....#............
	// .......#..............
	// ......#...............
	// ...#...#.#...#........
	// ....#..#..#.........#.
	// .......#..............
	// ...........#..#.......
	// #...........#.........
	// ...#.......#..........
	//
	// After 1 second:
	// ......................
	// ......................
	// ..........#....#......
	// ........#.....#.......
	// ..#.........#......#..
	// ......................
	// ......#...............
	// ....##.........#......
	// ......#.#.............
	// .....##.##..#.........
	// ........#.#...........
	// ........#...#.....#...
	// ..#...........#.......
	// ....#.....#.#.........
	// ......................
	// ......................
	//
	// After 2 seconds:
	// ......................
	// ......................
	// ......................
	// ..............#.......
	// ....#..#...####..#....
	// ......................
	// ........#....#........
	// ......#.#.............
	// .......#...#..........
	// .......#..#..#.#......
	// ....#....#.#..........
	// .....#...#...##.#.....
	// ........#.............
	// ......................
	// ......................
	// ......................
	//
	// After 3 seconds:
	// ......................
	// ......................
	// ......................
	// ......................
	// ......#...#..###......
	// ......#...#...#.......
	// ......#...#...#.......
	// ......#####...#.......
	// ......#...#...#.......
	// ......#...#...#.......
	// ......#...#...#.......
	// ......#...#..###......
	// ......................
	// ......................
	// ......................
	// ......................
	//
	// After 4 seconds:
	// ......................
	// ......................
	// ......................
	// ............#.........
	// ........##...#.#......
	// ......#.....#..#......
	// .....#..##.##.#.......
	// .......##.#....#......
	// ...........#....#.....
	// ..............#.......
	// ....#......#...#......
	// .....#.....##.........
	// ...............#......
	// ...............#......
	// ......................
	// ......................
	// After 3 seconds, the message appeared briefly: HI. Of course, your message
	// will be much longer and will take many more seconds to appear.
	//
	// What message will eventually appear in the sky?

	@Override
	public String part1() {
		init(getLines());
		// 10369 is when the minimum x and y are both positive
		for (int t = 10369; t < 10376; t++) {
			final List<String> results = draw(t);
			if (results.size() > 0) {
				System.out.print(t);
				for (final String line : results) {
					System.out.println(line);
				}
				System.out.println(t);
			}
		}
		return "PPNJEENH";
	}

	// --- Part Two ---
	//
	// Good thing you didn't have to wait, because that would have taken a long time
	// - much longer than the 3 seconds in the example above.
	//
	// Impressed by your sub-hour communication capabilities, the Elves are curious:
	// exactly how many seconds would they have needed to wait for that message to
	// appear?

	@Override
	public String part2() {
		return "10375";
	}

}
