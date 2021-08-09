package com.rykerstudios.advent.year2020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay3 extends TestDay<Day3> {

	@Override
	public Day3 createDay() {
		return new Day3();
	}

	final String[] lines = {
			"..##.........##.........##.........##.........##.........##.......",
			"#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..",
			".#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.",
			"..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#",
			".#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.",
			"..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....",
			".#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#",
			".#........#.#........#.#........#.#........#.#........#.#........#",
			"#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...",
			"#...##....##...##....##...##....##...##....##...##....##...##....#",
			".#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#" };

	@Test
	public void testSlope11() {
		final Day3 day = createDay();
		final int[][] forest = day.loadForest(lines);
		Assertions.assertEquals(2, day.countSlope(forest, 1, 1));
	}

	@Test
	public void testSlope31() {
		final Day3 day = createDay();
		final int[][] forest = day.loadForest(lines);
		Assertions.assertEquals(7, day.countSlope(forest, 3, 1));
	}

	@Test
	public void testSlope51() {
		final Day3 day = createDay();
		final int[][] forest = day.loadForest(lines);
		Assertions.assertEquals(3, day.countSlope(forest, 5, 1));
	}

	@Test
	public void testSlope71() {
		final Day3 day = createDay();
		final int[][] forest = day.loadForest(lines);
		Assertions.assertEquals(4, day.countSlope(forest, 7, 1));
	}

	@Test
	public void testSlope12() {
		final Day3 day = createDay();
		final int[][] forest = day.loadForest(lines);
		Assertions.assertEquals(2, day.countSlope(forest, 1, 2));
	}

}
