package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay5 extends TestDay<Day5> {

	@Override
	public Day5 createDay() {
		return new Day5();
	}

	@Test
	public void testReact_whenAa_thenNothingRemains() {
		assertEquals("", day.react("aA"));
		assertEquals("", day.react("Aa"));
	}

	@Test
	public void testReact_whenAbBa_thenNothingRemains() {
		assertEquals("", day.react("AbBa"));
	}

	@Test
	public void testReact_whenABbB_thenRemainsAB() {
		assertEquals("ab", day.react("abBb"));
	}

	@Test
	public void testReact_whenABab_thenNoChange() {
		assertEquals("ABab", day.react("ABab"));
	}

	@Test
	public void testReact_whenAABaab_thenNoChange() {
		assertEquals("aabAAB", day.react("aabAAB"));
	}

	@Test
	public void testReact_when_dabAcCaCBAcCcaDA_then_dabCBAcaDA() {
		assertEquals("dabCBAcaDA", day.react("dabAcCaCBAcCcaDA"));
	}

}
