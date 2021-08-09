package com.rykerstudios.advent.year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.Day;
import com.rykerstudios.advent.TestDay;

public class TestDay2 extends TestDay<Day2> {

	@Override
	public Day2 createDay() {
		return new Day2();
	}

	@Test
	public void testCalc_whenPart1_thenExpected1() {
		Day<Integer> day = createDay();
		assertEquals(day.getExpectedPart1(), day.part1());
	}

	@Test
	public void testCalc_whenPart2_thenExpected2() {
		Day<Integer> day = createDay();
		assertEquals(day.getExpectedPart2(), day.part2());
	}

}
