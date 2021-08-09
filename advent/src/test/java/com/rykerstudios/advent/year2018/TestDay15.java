package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay15 extends TestDay<Day15> {

	@Override
	public Day15 createDay() {
		return new Day15();
	}

	@Test
	public void testCombatSample1() {
		day.init(new String[] {
			"#######",
			"#.G.E.#",
			"#E.G.E#",
			"#.G.E.#",
			"#######"
		});
		assertTrue(day.combatRound());
		assertEquals(1, day.getRounds());
	}

	@Test
	public void testCombatSample2() {
		day.init(new String[] {
			"#######", 
			"#E..G.#", 
			"#...#.#", 
			"#.G.#G#", 
			"#######"
		});
		assertTrue(day.combatRound());
		assertTrue(day.combatRound());
		day.logSpaces();
		assertEquals(2, day.getRounds());
	}

	@Test
	public void testCombatSample3() {
		day.init(new String[] {
			"#########", 
			"#G..G..G#", 
			"#.......#", 
			"#.......#", 
			"#G..E..G#", 
			"#.......#", 
			"#.......#", 
			"#G..G..G#", 
			"#########"
		});
		assertEquals(25452, day.combat());
	}

	@Test
	public void testCombatSample4() {
		day.init(new String[] {
			"#######",
			"#.G...#",
			"#...EG#",
			"#.#.#G#",
			"#..G#E#",
			"#.....#",
			"#######"
		});
		assertEquals(47 * 590, day.combat());
		assertEquals(47, day.getRounds());
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
