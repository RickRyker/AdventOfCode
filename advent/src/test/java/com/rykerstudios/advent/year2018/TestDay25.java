package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay25 extends TestDay<Day25> {

	@Override
	public Day25 createDay() {
		return new Day25();
	}

	@Test
	public void testDistance2345() {
		assertEquals(14, Day25.distance(new int[] { 0, 0, 0, 0 }, new int[] { 5, 2, 4, 3 }));
	}

	@Test
	public void testSample1() {
		day.init(new String[] {
				"0,0,0,0",
				"3,0,0,0",
				"0,3,0,0",
				"0,0,3,0",
				"0,0,0,3",
				"0,0,0,6",
				"9,0,0,0",
				"12,0,0,0" });
		assertEquals(8, day.stars.size());
		day.distribute(day.stars, day.constellations);
		assertEquals(2, day.constellations.size());
	}

	@Test
	public void testSample2() {
		day.init(new String[] {
				"-1,2,2,0",
				"0,0,2,-2",
				"0,0,0,-2",
				"-1,2,0,0",
				"-2,-2,-2,2",
				"3,0,2,-1",
				"-1,3,2,2",
				"-1,0,-1,0",
				"0,2,1,-2",
				"3,0,0,0" });
		assertEquals(10, day.stars.size());
		day.distribute(day.stars, day.constellations);
		assertEquals(4, day.constellations.size());
	}

	@Test
	public void testSample3() {
		day.init(new String[] {
				"1,-1,0,1",
				"2,0,-1,0",
				"3,2,-1,0",
				"0,0,3,1",
				"0,0,-1,-1",
				"2,3,-2,0",
				"-2,2,0,0",
				"2,-2,0,-1",
				"1,-1,0,-1",
				"3,2,0,2" });
		assertEquals(10, day.stars.size());
		day.distribute(day.stars, day.constellations);
		assertEquals(3, day.constellations.size());
	}

	@Test
	public void testSample4() {
		day.init(new String[] {
				"1,-1,-1,-2",
				"-2,-2,0,1",
				"0,2,1,3",
				"-2,3,-2,1",
				"0,2,3,-2",
				"-1,-1,1,-2",
				"0,-2,-1,0",
				"-2,2,3,-1",
				"1,2,2,0",
				"-1,-2,0,-2" });
		assertEquals(10, day.stars.size());
		day.distribute(day.stars, day.constellations);
		assertEquals(8, day.constellations.size());
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
