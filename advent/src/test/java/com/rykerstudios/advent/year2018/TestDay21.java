package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay21 extends TestDay<Day21> {

	@Override
	public Day21 createDay() {
		return new Day21();
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
