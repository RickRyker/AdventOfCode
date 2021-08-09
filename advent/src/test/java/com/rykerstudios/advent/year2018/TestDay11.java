package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay11 extends TestDay<Day11> {

	@Override
	public Day11 createDay() {
		return new Day11();
	}

	@Test
	public void testGetPowerLevel_whenGSN8_fuelCellX3Y5_thenPower4() {
		assertEquals(4, day.getPowerLevel(8, 3, 5));
	}

	@Test
	public void testGetPowerLevel_whenGSN57_fuelCellX122Y79_thenPowerNeg5() {
		assertEquals(-5, day.getPowerLevel(57, 122, 79));
	}

	@Test
	public void testGetPowerLevel_whenGSN39_fuelCellX217Y196_thenPower0() {
		assertEquals(0, day.getPowerLevel(39, 217, 196));
	}

	@Test
	public void testGetPowerLevel_whenGSN71_fuelCellX101Y153_thenPower4() {
		assertEquals(4, day.getPowerLevel(71, 101, 153));
	}

	@Test
	public void testGetGridPower_whenGSN18_fuelCellX33Y45_thenTotal29() {
		assertEquals(29, day.getGridPower(18, 33, 45));
	}

	@Test
	public void testGetGridPower_whenGSN42_fuelCellX21Y61_thenPower30() {
		assertEquals(30, day.getGridPower(42, 21, 61));
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
