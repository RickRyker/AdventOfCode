package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay20 extends TestDay<Day20> {

	@Override
	public Day20 createDay() {
		return new Day20();
	}

	@Test
	public void testSample1() {
		final String regex = "^WNE$";
		final Set<String> paths = day.expand(regex);
		assertEquals(1, paths.size());
		final int doors = day.buildMap(regex);
		assertEquals(3, doors);
	}

	@Test
	public void testSample2() {
		final String regex = "^ENWWW(NEEE|SSE(EE|N))$";
		final Set<String> paths = day.expand(regex);
		assertEquals(4, paths.size());
		final int doors = day.buildMap(regex);
		assertEquals(10, doors);
	}

	@Test
	public void testSample3() {
		final String regex = "^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$";
		final Set<String> paths = day.expand(regex);
		System.out.println(paths.toString());
		assertEquals(8, paths.size());
		final int doors = day.buildMap(regex);
		assertEquals(18, doors);
	}

	@Test
	public void testSample4() {
		final String regex = "^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$";
		final Set<String> paths = day.expand(regex);
		assertEquals(4, paths.size());
		final int doors = day.buildMap(regex);
		assertEquals(23, doors);
	}

	@Test
	public void testSample5() {
		final String regex = "^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$";
		final Set<String> paths = day.expand(regex);
		assertEquals(4, paths.size());
		final int doors = day.buildMap(regex);
		assertEquals(31, doors);
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
