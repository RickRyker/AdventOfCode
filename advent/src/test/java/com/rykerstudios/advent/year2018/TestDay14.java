package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay14 extends TestDay<Day14> {

	@Override
	public Day14 createDay() {
		return new Day14();
	}

	@Test
	public void testMakeRecipes_when18_thenLastTen_9251071085() {
		day.makeRecipes("37", 18);
		assertEquals("9251071085", day.lastTenScores);
	}

	@Test
	public void testMakeRecipes_when5_thenLastTen_0124515891() {
		day.makeRecipes("37", 5);
		assertEquals("0124515891", day.lastTenScores);
	}

	@Test
	public void testMakeRecipes_when9_thenLastTen_5158916779() {
		day.makeRecipes("37", 9);
		assertEquals("5158916779", day.lastTenScores);
	}

	@Test
	public void testMakeRecipes_when2018_thenLastTen_5941429882() {
		day.makeRecipes("37", 2018);
		assertEquals("5941429882", day.lastTenScores);
	}

	@Test
	public void testMakeRecipes_when01245_then5() {
		day.makeRecipes("37", "01245");
		assertEquals(5, day.scoreboard.indexOf("01245"));
	}

	@Test
	public void testMakeRecipes_when51589_then9() {
		day.makeRecipes("37", "51589");
		assertEquals(9, day.scoreboard.indexOf("51589"));
	}

	@Test
	public void testMakeRecipes_when59414_then2018() {
		day.makeRecipes("37", "59414");
		assertEquals(2018, day.scoreboard.indexOf("59414"));
	}

	@Test
	public void testMakeRecipes_when92510_then18() {
		day.makeRecipes("37", "92510");
		assertEquals(18, day.scoreboard.indexOf("92510"));
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
