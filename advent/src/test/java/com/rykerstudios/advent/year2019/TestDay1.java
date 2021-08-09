package com.rykerstudios.advent.year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.Day;
import com.rykerstudios.advent.TestDay;

public class TestDay1 extends TestDay<Day1> {

	@Override
	public Day1 createDay() {
		return new Day1();
	}

	@Test
	public void testFuelNeeded_when12_then2() {
		assertEquals(2, createDay().calcFuel(12));
	}

	@Test
	public void testFuelNeeded_when14_then2() {
		assertEquals(2, createDay().calcFuel(14));
	}

	@Test
	public void testFuelNeeded_when1969_then654() {
		assertEquals(654, createDay().calcFuel(1969));
	}

	@Test
	public void testFuelNeeded_when100756_then33583() {
		assertEquals(33583, createDay().calcFuel(100756));
	}

	@Test
	public void testFuelNeeded_whenTest1_thenExpected1() {
		Day<Integer> day = createDay();
		assertEquals(day.getExpectedPart1(), day.part1());
	}

	@Test
	public void testFuelNeeded_when14WithFuel_then2() {
		assertEquals(2, createDay().calcFuelWithFuel(14));
	}

	@Test
	public void testFuelNeeded_when1969WithFuel_then966() {
		assertEquals(966, createDay().calcFuelWithFuel(1969));
	}

	@Test
	public void testFuelNeeded_when100756WithFuel_then50346() {
		assertEquals(50346, createDay().calcFuelWithFuel(100756));
	}

	@Test
	public void testFuelNeeded_whenTest2_thenExpected2() {
		Day<Integer> day = createDay();
		assertEquals(day.getExpectedPart2(), day.part2());
	}

}
