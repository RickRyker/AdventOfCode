package com.rykerstudios.advent.year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.Day;
import com.rykerstudios.advent.TestDay;

public class TestDay8 extends TestDay<Day8> {

	@Override
	public Day8 createDay() {
		return new Day8();
	}

	@Test
	public void testCalc_whenPart1_thenExpected1() {
		final Day<Integer> day = createDay();
		assertEquals(day.getExpectedPart1(), day.part1());
	}

	@Test
	public void testCalc_whenPart2_thenExpected2() {
		final Day<Integer> day = createDay();
		assertEquals(day.getExpectedPart2(), day.part2());
	}

}
