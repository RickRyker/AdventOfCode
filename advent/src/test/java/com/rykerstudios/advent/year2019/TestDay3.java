package com.rykerstudios.advent.year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.Day;
import com.rykerstudios.advent.TestDay;

public class TestDay3 extends TestDay<Day3> {

	@Override
	public Day3 createDay() {
		return new Day3();
	}

	@Test
	public void testInitGrid() {
		final Day3 day = createDay();
		final String[] wire = new String[] { "U3", "L5", "D4", "R6" };
		final int[] bounds = day.getBounds(wire);
		final String[][] grid = day.initGrid(bounds);
		final String report = day.gridToString(grid);
		System.out.println(report);
	}

	@Test
	public void testCreateGrid() {
		final Day3 day = createDay();
		final String[] wire = new String[] { "U3", "L5", "D4", "R6" };
		final String[][] grid = day.createGrid(wire);
		final String report = day.gridToString(grid);
		System.out.println(report);
	}

	@Test
	public void testGetBounds() {
		final Day3 day = createDay();
		final int[] bounds = day.getBounds(new String[] { "U3", "D4" });
		System.out.println("[" + bounds[0] + "," + bounds[1] + "," + bounds[2] + "," + bounds[3] + "]");
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
