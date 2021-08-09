package com.rykerstudios.advent.year2018;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rykerstudios.advent.Day;

public class Day18 extends Day<Integer> {

	private static final Logger log = LoggerFactory.getLogger(Day18.class);

	public Day18() {
		super(623583, 107912);
	}

	// --- Day 18: Settlers of The North Pole ---
	//
	// On the outskirts of the North Pole base construction project, many Elves are
	// collecting lumber.
	//
	// The lumber collection area is 50 acres by 50 acres; each acre can be either
	// open ground (.), trees (|), or a lumberyard (#). You take a scan of the area
	// (your puzzle input).
	//
	// Strange magic is at work here: each minute, the landscape looks entirely
	// different. In exactly one minute, an open acre can fill with trees, a wooded
	// acre can be converted to a lumberyard, or a lumberyard can be cleared to open
	// ground (the lumber having been sent to other projects).
	//
	// The change to each acre is based entirely on the contents of that acre as
	// well as the number of open, wooded, or lumberyard acres adjacent to it at the
	// start of each minute. Here, "adjacent" means any of the eight acres
	// surrounding that acre. (Acres on the edges of the lumber collection area
	// might have fewer than eight adjacent acres; the missing acres aren't
	// counted.)
	//
	// In particular:
	//
	// An open acre will become filled with trees if three or more adjacent acres
	// contained trees. Otherwise, nothing happens.
	// An acre filled with trees will become a lumberyard if three or more adjacent
	// acres were lumberyards. Otherwise, nothing happens.
	// An acre containing a lumberyard will remain a lumberyard if it was adjacent
	// to at least one other lumberyard and at least one acre containing trees.
	// Otherwise, it becomes open.
	// These changes happen across all acres simultaneously, each of them using the
	// state of all acres at the beginning of the minute and changing to their new
	// form by the end of that same minute. Changes that happen during the minute
	// don't affect each other.
	//
	// For example, suppose the lumber collection area is instead only 10 by 10
	// acres with this initial configuration:
	//
	// Initial state:
	// .#.#...|#.
	// .....#|##|
	// .|..|...#.
	// ..|#.....#
	// #.#|||#|#|
	// ...#.||...
	// .|....|...
	// ||...#|.#|
	// |.||||..|.
	// ...#.|..|.
	//
	// After 1 minute:
	// .......##.
	// ......|###
	// .|..|...#.
	// ..|#||...#
	// ..##||.|#|
	// ...#||||..
	// ||...|||..
	// |||||.||.|
	// ||||||||||
	// ....||..|.
	//
	// After 2 minutes:
	// .......#..
	// ......|#..
	// .|.|||....
	// ..##|||..#
	// ..###|||#|
	// ...#|||||.
	// |||||||||.
	// ||||||||||
	// ||||||||||
	// .|||||||||
	//
	// After 3 minutes:
	// .......#..
	// ....|||#..
	// .|.||||...
	// ..###|||.#
	// ...##|||#|
	// .||##|||||
	// ||||||||||
	// ||||||||||
	// ||||||||||
	// ||||||||||
	//
	// After 4 minutes:
	// .....|.#..
	// ...||||#..
	// .|.#||||..
	// ..###||||#
	// ...###||#|
	// |||##|||||
	// ||||||||||
	// ||||||||||
	// ||||||||||
	// ||||||||||
	//
	// After 5 minutes:
	// ....|||#..
	// ...||||#..
	// .|.##||||.
	// ..####|||#
	// .|.###||#|
	// |||###||||
	// ||||||||||
	// ||||||||||
	// ||||||||||
	// ||||||||||
	//
	// After 6 minutes:
	// ...||||#..
	// ...||||#..
	// .|.###|||.
	// ..#.##|||#
	// |||#.##|#|
	// |||###||||
	// ||||#|||||
	// ||||||||||
	// ||||||||||
	// ||||||||||
	//
	// After 7 minutes:
	// ...||||#..
	// ..||#|##..
	// .|.####||.
	// ||#..##||#
	// ||##.##|#|
	// |||####|||
	// |||###||||
	// ||||||||||
	// ||||||||||
	// ||||||||||
	//
	// After 8 minutes:
	// ..||||##..
	// ..|#####..
	// |||#####|.
	// ||#...##|#
	// ||##..###|
	// ||##.###||
	// |||####|||
	// ||||#|||||
	// ||||||||||
	// ||||||||||
	//
	// After 9 minutes:
	// ..||###...
	// .||#####..
	// ||##...##.
	// ||#....###
	// |##....##|
	// ||##..###|
	// ||######||
	// |||###||||
	// ||||||||||
	// ||||||||||
	//
	// After 10 minutes:
	// .||##.....
	// ||###.....
	// ||##......
	// |##.....##
	// |##.....##
	// |##....##|
	// ||##.####|
	// ||#####|||
	// ||||#|||||
	// ||||||||||
	//
	// After 10 minutes, there are 37 wooded acres and 31 lumberyards. Multiplying
	// the number of wooded acres by the number of lumberyards gives the total
	// resource value after ten minutes: 37 * 31 = 1147.
	//
	// What will the total resource value of the lumber collection area be after 10
	// minutes?

	@Override
	public Integer part1() {
		char[][] area = init(getLines());
		logLumberArea(area);
		for (int i = 0; i < 10; i++) {
			area = transform(area);
			logLumberArea(area);
		}
		final int trees = count('|', area);
		final int yards = count('#', area);
		return trees * yards;
	}

	// --- Part Two ---
	//
	// This important natural resource will need to last for at least thousands of
	// years. Are the Elves collecting this lumber sustainably?
	//
	// What will the total resource value of the lumber collection area be after
	// 1000000000 minutes?

	@Override
	public Integer part2() {
		char[][] area = init(getLines());
		char[][] prev = area;
		logLumberArea(area);
		for (int i = 0; i < 1000000000; i++) {
			prev = area;
			area = transform(area);
			if (Arrays.equals(area, prev)) {
				break;
			}
			final int trees = count('|', area);
			final int yards = count('#', area);
			log.info("Minute:" + i + "; TreesYards:" + trees + yards + " = " + (trees * yards));
			// after minute 580 there is a 35 minute cycle
			if (i == 580) {
				final int cycle = 35;
				while (i < 1000000000 - cycle) {
					if (i + cycle < 1000000000) {
						i += cycle;
					}
				}
			}
		}
		final int trees = count('|', area);
		final int yards = count('#', area);
		return trees * yards;
	}

	public char[][] init(final String[] lines) {
		final char[][] area = new char[lines.length][lines.length];
		int y = 0;
		for (final String line : lines) {
			area[y] = line.toCharArray();
			y++;
		}
		return area;
	}

	public void logLumberArea(final char[][] area) {
		final StringBuilder sb = new StringBuilder();
		sb.append("Lumber Collection Area:");
		for (int y = 0; y < area.length; y++) {
			sb.append("\n\t").append(area[y]);
		}
		log.info(sb.toString());
	}

	protected int count(final char c, final char[][] area) {
		int count = 0;
		for (int y = 0; y < area.length; y++) {
			for (int x = 0; x < area[y].length; x++) {
				if (area[y][x] == c) {
					count++;
				}
			}
		}
		return count;
	}

	protected int countAdjacent(final char c, final int px, final int py, final char[][] area) {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) {
					// skip the center
					continue;
				}
				final int x = px + i - 1;
				final int y = py + j - 1;
				if (y < 0 || x < 0 || y >= area.length || x >= area.length) {
					continue;
				}
				if (area[y][x] == c) {
					count++;
				}
			}
		}
		return count;
	}

	protected char[][] transform(final char[][] area) {
		final char[][] area2 = new char[area.length][area.length];
		for (int y = 0; y < area.length; y++) {
			for (int x = 0; x < area.length; x++) {
				final char acre = area[y][x];
				// final int empty = countAdjacent('.', x, y, area);
				final int trees = countAdjacent('|', x, y, area);
				final int yards = countAdjacent('#', x, y, area);
				if (acre == '.' && trees >= 3) {
					// if an . is surrounded by at least 3 | it spawns a |
					area2[y][x] = '|';
				} else if (acre == '|' && yards >= 3) {
					// if an | is surrounded by at least 3 # it spawns a |
					area2[y][x] = '#';
				} else if (acre == '#' && (trees == 0 || yards == 0)) {
					// if an | has at least 1 | and 1 # it remains a # otherwise open
					area2[y][x] = '.';
				} else {
					area2[y][x] = acre;
				}
			}
		}
		return area2;
	}

}
