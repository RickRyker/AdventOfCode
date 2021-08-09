package com.rykerstudios.advent.year2020;

import com.rykerstudios.advent.Day;

public class Day3 extends Day<Long> {

	public Day3() {
		super(274L, 6050183040L);
	}

	/**
	 * --- Day 3: Toboggan Trajectory ---
	 * 
	 * With the toboggan login problems resolved, you set off toward the airport.
	 * While travel by toboggan might be easy, it's certainly not safe: there's very
	 * minimal steering and the area is covered in trees. You'll need to see which
	 * angles will take you near the fewest trees.
	 * 
	 * Due to the local geology, trees in this area only grow on exact integer
	 * coordinates in a grid. You make a map (your puzzle input) of the open squares
	 * (.) and trees (#) you can see. For example:
	 * 
	 * ..##....... #...#...#.. .#....#..#. ..#.#...#.# .#...##..#. ..#.##.....
	 * .#.#.#....# .#........# #.##...#... #...##....# .#..#...#.# These aren't the
	 * only trees, though; due to something you read about once involving arboreal
	 * genetics and biome stability, the same pattern repeats to the right many
	 * times:
	 * 
	 * ..##.........##.........##.........##.........##.........##....... --->
	 * #...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
	 * .#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
	 * ..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
	 * .#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
	 * ..#.##.......#.##.......#.##.......#.##.......#.##.......#.##..... --->
	 * .#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
	 * .#........#.#........#.#........#.#........#.#........#.#........#
	 * #.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...
	 * #...##....##...##....##...##....##...##....##...##....##...##....#
	 * .#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.# --->
	 * 
	 * You start on the open square (.) in the top-left corner and need to reach the
	 * bottom (below the bottom-most row on your map).
	 * 
	 * The toboggan can only follow a few specific slopes (you opted for a cheaper
	 * model that prefers rational numbers); start by counting all the trees you
	 * would encounter for the slope right 3, down 1:
	 * 
	 * From your starting position at the top-left, check the position that is right
	 * 3 and down 1. Then, check the position that is right 3 and down 1 from there,
	 * and so on until you go past the bottom of the map.
	 * 
	 * The locations you'd check in the above example are marked here with O where
	 * there was an open square and X where there was a tree:
	 * 
	 * ..##.........##.........##.........##.........##.........##....... ---> <br/>
	 * #..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#.. <br/>
	 * .#....X..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#. <br/>
	 * ..#.#...#O#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.# <br/>
	 * .#...##..#..X...##..#..#...##..#..#...##..#..#...##..#..#...##..#. <br/>
	 * ..#.##.......#.X#.......#.##.......#.##.......#.##.......#.##..... ---> <br/>
	 * .#.#.#....#.#.#.#.O..#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....# <br/>
	 * .#........#.#........X.#........#.#........#.#........#.#........# <br/>
	 * #.##...#...#.##...#...#.X#...#...#.##...#...#.##...#...#.##...#... <br/>
	 * #...##....##...##....##...#X....##...##....##...##....##...##....# <br/>
	 * .#..#...#.#.#..#...#.#.#..#...X.#.#..#...#.#.#..#...#.#.#..#...#.# ---> <br/>
	 *
	 * In this example, traversing the map using this slope would cause you to
	 * encounter 7 trees.
	 * 
	 * Starting at the top-left corner of your map and following a slope of right 3
	 * and down 1, how many trees would you encounter?
	 */
	@Override
	public Long part1() {
		final int[][] forest = loadForest(getLines());
		return (long) countSlope(forest, 3, 1);
	}

	public int countSlope(final int[][] forest, final int right, final int down) {
		int count = 0;
		int width = forest[0].length;
		int col = 0;
		int bottom = forest.length;
		for (int row = 0; row < bottom; row += down) {
			if (forest[row][col] == 1) {
				count++;
			}
			col = (col + right) % width;
		}
		return count;
	}

	public int[][] loadForest(final String[] lines) {
		final int rows = lines.length;
		final int cols = lines[0].length();
		final int[][] forest = new int[rows][cols];
		int row = 0;
		for (final String line : lines) {
			for (int col = 0; col < cols; col++) {
				final char cell = line.charAt(col);
				if (cell == '#') {
					forest[row][col] = 1;
				}
			}
			row++;
		}
		return forest;
	}

	/**
	 * --- Part Two ---
	 * 
	 * Time to check the rest of the slopes - you need to minimize the probability
	 * of a sudden arboreal stop, after all.
	 * 
	 * Determine the number of trees you would encounter if, for each of the
	 * following slopes, you start at the top-left corner and traverse the map all
	 * the way to the bottom:
	 * 
	 * Right 1, down 1. <br/>
	 * Right 3, down 1. (This is the slope you already checked.) <br/>
	 * Right 5, down 1. <br/>
	 * Right 7, down 1. <br/>
	 * Right 1, down 2. <br/>
	 * 
	 * In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s)
	 * respectively; multiplied together, these produce the answer 336.
	 * 
	 * What do you get if you multiply together the number of trees encountered on
	 * each of the listed slopes?
	 */
	@Override
	public Long part2() {
		final int[][] forest = loadForest(getLines());
		final long count11 = countSlope(forest, 1, 1); // 90
		final long count31 = countSlope(forest, 3, 1); // 274
		final long count51 = countSlope(forest, 5, 1); // 82
		final long count71 = countSlope(forest, 7, 1); // 68
		final long count12 = countSlope(forest, 1, 2); // 44
		return count11 * count31 * count51 * count71 * count12;
		// incorrect answer 1755215744 if multiple integers into an integer (overflow).
	}

}
