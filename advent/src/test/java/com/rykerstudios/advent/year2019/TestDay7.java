package com.rykerstudios.advent.year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.Day;
import com.rykerstudios.advent.TestDay;

public class TestDay7 extends TestDay<Day7> {

	@Override
	public Day7 createDay() {
		return new Day7();
	}

	@Test
	public void testAmplifier_whenPhases43210_thenExpect43210() {
		final Day7 day = createDay();
		final int[] memory = new int[] { 3, 15, 3, 16, 1002, 16, 10, 16, 
				1, 16, 15, 15, 
				4, 15, 99, 0, 0 };
		day.init(memory);
		final int[] phases = new int[] { 4, 3, 2, 1, 0 };
		final int output = day.runAmplifiers(phases);
		assertEquals(43210, output);
	}

	@Test
	public void testAmplifier_whenPhases01234_thenExpect54321() {
		final Day7 day = createDay();
		final int[] memory = new int[] { 3, 23, 3, 24, 1002, 24, 10, 24, 
				1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 
				4, 23, 99, 0, 0 };
		day.init(memory);
		final int[] phases = new int[] { 0, 1, 2, 3, 4 };
		final int output = day.runAmplifiers(phases);
		assertEquals(54321, output);
	}

	@Test
	public void testAmplifier_whenPhases10432_thenExpect65210() {
		final Day7 day = createDay();
		final int[] memory = new int[] { 3, 31, 3, 32, 1002, 32, 10, 32,
				1001, 31, -2, 31, 1007, 31, 0, 33, 1002, 33, 7, 33,
				1, 33, 31, 31, 1, 32, 31, 31, 
				4, 31, 99, 0, 0, 0 };
		day.init(memory);
		final int[] phases = new int[] { 1, 0, 4, 3, 2 };
		final int output = day.runAmplifiers(phases);
		assertEquals(65210, output);
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
