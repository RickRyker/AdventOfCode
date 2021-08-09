package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay12 extends TestDay<Day12> {

	@Override
	public Day12 createDay() {
		return new Day12();
	}

	@Test
	public void testSample() {
		final String[] lines = new String[] {
				"initial state: #..#.#..##......###...###", 
				"...## => #", 
				"..#.. => #",
				".#... => #", 
				".#.#. => #", 
				".#.## => #", 
				".##.. => #", 
				".#### => #", 
				"#.#.# => #", 
				"#.### => #",
				"##.#. => #", 
				"##.## => #", 
				"###.. => #", 
				"###.# => #", 
				"####. => #"};

		day.init(lines, 20);
		assertEquals(11, day.getPotCount(0));
		assertEquals(7, day.getPotCount(1));
		assertEquals(325, day.getSumPotNumbers(20));
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
