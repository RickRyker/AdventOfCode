package com.rykerstudios.advent.year2017;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay25 extends TestDay<Day25> {

	@Override
	public Day25 createDay() {
		return new Day25();
	}

	@Test
	public void testSample() {
		final String[] lines = new String[] {};
		assertEquals(6, lines.length);
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
