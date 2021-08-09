package com.rykerstudios.advent.year2019;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.Day;
import com.rykerstudios.advent.TestDay;

public class TestDay4 extends TestDay<Day4> {

	@Override
	public Day4 createDay() {
		return new Day4();
	}

	@Test
	public void testCheck_when111111_thenTrue() {
		final Day4 day = createDay();
		assertTrue(day.check("111111"));
	}

	@Test
	public void testCheck_when111123_thenTrue() {
		final Day4 day = createDay();
		assertTrue(day.check("111123"));
	}

	@Test
	public void testCheck_when123789_thenFalse() {
		final Day4 day = createDay();
		assertFalse(day.check("123789"));
	}

	@Test
	public void testCheck_when135679_thenFalse() {
		final Day4 day = createDay();
		assertFalse(day.check("135679"));
	}

	@Test
	public void testCheck_when223450_thenFalse() {
		final Day4 day = createDay();
		assertFalse(day.check("223450"));
	}

	@Test
	public void testCheck2_when112233_thenTrue() {
		assertTrue(createDay().check2("112233"));
	}

	@Test
	public void testCheck2_when123444_thenFalse() {
		assertFalse(createDay().check2("123444"));
	}

	@Test
	public void testCheck2_when111122_thenTrue() {
		assertTrue(createDay().check2("111122"));
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
