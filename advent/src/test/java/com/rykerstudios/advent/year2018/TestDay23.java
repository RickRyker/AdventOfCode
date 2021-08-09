package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay23 extends TestDay<Day23> {

	@Override
	public Day23 createDay() {
		return new Day23();
	}

	@Test
	public void testSample() {
		final String[] lines = new String[] {
			"pos=<0,0,0>, r=4",
			"pos=<1,0,0>, r=1",
			"pos=<4,0,0>, r=3",
			"pos=<0,2,0>, r=1",
			"pos=<0,5,0>, r=3",
			"pos=<0,0,3>, r=1",
			"pos=<1,1,1>, r=1",
			"pos=<1,1,2>, r=1",
			"pos=<1,3,1>, r=1"};
		assertEquals(9, lines.length);
		final List<int[]> nanobots = day.init(lines);
		assertEquals(9, nanobots.size());
		final int[] strongest = day.findStrongest(nanobots);
		assertEquals(4, strongest[3]);
		final List<int[]> inRange = day.inRange(strongest, nanobots);
		assertEquals(7, inRange.size());
	}

	@Test
	public void testSample2() {
		final String[] lines = new String[] {
				"pos=<10,12,12>, r=2",
				"pos=<12,14,12>, r=2",
				"pos=<16,12,12>, r=4",
				"pos=<14,14,14>, r=6",
				"pos=<50,50,50>, r=200",
				"pos=<10,10,10>, r=5"};
		final List<int[]> nanobots = day.init(lines);
		assertEquals(6, nanobots.size());
		final int[] origin = new int[] { 0, 0, 0 };
		int shortestDistanceToOrigin = Integer.MAX_VALUE;
		int mostNanobotsInRange = 0;
		int[] selected = null;
		// DID IT WRONG; my coordinate could be +/- any x|y|z in the input
		for (final int[] center : nanobots) {
			final int distanceToOrigin = day.distance(origin, center);
			final List<int[]> inRange = day.inRange(center, nanobots);
			final int count = inRange.size();
			if ((mostNanobotsInRange == count && distanceToOrigin < shortestDistanceToOrigin)
					|| mostNanobotsInRange < count) {
				mostNanobotsInRange = count;
				selected = center;
				shortestDistanceToOrigin = distanceToOrigin;
			}
		}
		assertEquals(12, selected[0]);
		assertEquals(12, selected[1]);
		assertEquals(12, selected[2]);
		final List<int[]> selectedInRange = day.inRange(selected, nanobots);
		assertEquals(5, selectedInRange.size());
		for (final int[] nanobot : selectedInRange) {
			final int distance = day.distance(origin, nanobot);
			if (shortestDistanceToOrigin < distance) {
				shortestDistanceToOrigin = distance;
			}
		}
		assertEquals(36, shortestDistanceToOrigin);
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
