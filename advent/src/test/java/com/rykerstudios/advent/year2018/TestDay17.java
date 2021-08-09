package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay17 extends TestDay<Day17> {

	@Override
	public Day17 createDay() {
		return new Day17();
	}

	@Test
	public void testInit() {
		day.init(new String[] {
				"x=495, y=2..7",
				"y=7, x=495..501", 
				"x=501, y=3..7",
				"x=498, y=2..4",
				"x=506, y=1..2",
				"x=498, y=10..13",
				"x=504, y=10..13",
				"y=13, x=498..504"
		});
		assertEquals(8, day.getScans().size());
	}

	@Test
	public void testSample() {
		day.init(new String[] {
				"x=495, y=2..7",
				"y=7, x=495..501", 
				"x=501, y=3..7",
				"x=498, y=2..4",
				"x=506, y=1..2",
				"x=498, y=10..13",
				"x=504, y=10..13",
				"y=13, x=498..504"
		});
		final List<char[]> map = day.createSandClayMap(day.getScans());
		day.logWaterMap(map);
		day.fillWithWater(map);
		assertEquals(57, day.countWater(map));
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
