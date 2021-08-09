package com.rykerstudios.advent.year2017;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay13 extends TestDay<Day13> {

	@Override
	public Day13 createDay() {
		return new Day13();
	}

	@Test
	public void testSample() {
		final String[] lines = new String[] {};
		assertEquals(325, lines.length);
	}

	@Override
	@Test
	public void testPart1() {
		assertEquals(day.getExpectedPart1(), day.part1());
	}

	@Override
	@Test
	public void testPart2() {
		// Tick: 12128
		// not 71,75
		// not 71,74
		// not 42,14
		assertEquals(day.getExpectedPart2(), day.part2());
	}

}
