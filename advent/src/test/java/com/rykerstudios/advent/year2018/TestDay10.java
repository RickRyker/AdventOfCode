package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay10 extends TestDay<Day10> {

	@Override
	public Day10 createDay() {
		return new Day10();
	}

	@Test
	public void testSample() {
		final String[] lines = new String[] {
				"position=< 9, 1> velocity=< 0, 2>", "position=< 7, 0> velocity=<-1, 0>",
				"position=< 3, -2> velocity=<-1, 1>", "position=< 6, 10> velocity=<-2, -1>",
				"position=< 2, -4> velocity=< 2, 2>", "position=<-6, 10> velocity=< 2, -2>",
				"position=< 1, 8> velocity=< 1, -1>", "position=< 1, 7> velocity=< 1, 0>",
				"position=<-3, 11> velocity=< 1, -2>", "position=< 7, 6> velocity=<-1, -1>",
				"position=<-2, 3> velocity=< 1, 0>", "position=<-4, 3> velocity=< 2, 0>",
				"position=<10, -3> velocity=<-1, 1>", "position=< 5, 11> velocity=< 1, -2>",
				"position=< 4, 7> velocity=< 0, -1>", "position=< 8, -2> velocity=< 0, 1>",
				"position=<15, 0> velocity=<-2, 0>", "position=< 1, 6> velocity=< 1, 0>",
				"position=< 8, 9> velocity=< 0, -1>", "position=< 3, 3> velocity=<-1, 1>",
				"position=< 0, 5> velocity=< 0, -1>", "position=<-2, 2> velocity=< 2, 0>",
				"position=< 5, -2> velocity=< 1, 2>", "position=< 1, 4> velocity=< 2, 1>",
				"position=<-2, 7> velocity=< 2, -2>", "position=< 3, 6> velocity=<-1, -1>",
				"position=< 5, 0> velocity=< 1, 0>", "position=<-6, 0> velocity=< 2, 0>",
				"position=< 5, 9> velocity=< 1, -2>", "position=<14, 7> velocity=<-2, 0>",
				"position=<-3, 6> velocity=< 2, -1>" };
		day.init(lines);
		assertEquals(31, day.lights.size());
		final List<String> results = day.draw(3);
		for (final String line : results) {
			System.out.println(line);
		}
		assertEquals(8, results.size());
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
