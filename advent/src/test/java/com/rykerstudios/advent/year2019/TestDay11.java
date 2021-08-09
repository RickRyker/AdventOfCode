package com.rykerstudios.advent.year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.Day;
import com.rykerstudios.advent.TestDay;

public class TestDay11 extends TestDay<Day11> {

	@Override
	public Day11 createDay() {
		return new Day11();
	}

	@Test
	public void testCalc_whenPart1_thenExpected1() {
		final Day<Long> day = createDay();
		assertEquals(day.getExpectedPart1(), day.part1());
	}

	@Test
	public void testCalc_whenPart2_thenExpected2() {
		final Day<Long> day = createDay();
		assertEquals(day.getExpectedPart2(), day.part2());
	}

}
