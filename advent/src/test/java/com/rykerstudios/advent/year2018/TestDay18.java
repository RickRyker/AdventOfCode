package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay18 extends TestDay<Day18> {

	@Override
	public Day18 createDay() {
		return new Day18();
	}

	@Test
	public void testInit() {
		char[][] area = day.init(new String[] {
				".#.#...|#.",
				".....#|##|",
				".|..|...#.",
				"..|#.....#",
				"#.#|||#|#|",
				"...#.||...",
				".|....|...",
				"||...#|.#|",
				"|.||||..|.",
				"...#.|..|."
		});
		day.logLumberArea(area);
		for (int i = 0; i < 10; i++) {
			area = day.transform(area);
			day.logLumberArea(area);
		}
		final int trees = day.count('|', area);
		final int yards = day.count('#', area);
		assertEquals(37, trees);
		assertEquals(31, yards);
		assertEquals(1147, trees * yards);
	}

	@Test
	public void testSample() {
		assertTrue(true);
	}

	@Override
	@Test
	public void testPart1() {
		assertEquals(day.getExpectedPart1(), day.part1());
	}

	@Override
	@Test
	public void testPart2() {
		assertEquals(day.getExpectedPart2(), day.part2());
	}

}
