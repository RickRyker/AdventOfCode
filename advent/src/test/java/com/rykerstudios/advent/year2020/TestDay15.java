package com.rykerstudios.advent.year2020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;
import com.rykerstudios.advent.year2020.Day15.NumberSpokenGame;

@RunWith(JUnitPlatform.class)
public class TestDay15 extends TestDay<Day15> {

	@Override
	public Day15 createDay() {
		return new Day15();
	}

	@Test
	public void testNumberSpoken_0_3_6() {
		final NumberSpokenGame game = new NumberSpokenGame("0,3,6");
		final long number = game.play(2020);
		Assertions.assertEquals(436, number);
	}

	@Test
	public void testNumberSpoken_1_3_2() {
		final NumberSpokenGame game = new NumberSpokenGame("1,3,2");
		final long number = game.play(2020);
		Assertions.assertEquals(1, number);
	}

	@Test
	public void testNumberSpoken_2_1_3() {
		final NumberSpokenGame game = new NumberSpokenGame("2,1,3");
		final long number = game.play(2020);
		Assertions.assertEquals(10, number);
	}

	@Test
	public void testNumberSpoken_1_2_3() {
		final NumberSpokenGame game = new NumberSpokenGame("1,2,3");
		final long number = game.play(2020);
		Assertions.assertEquals(27, number);
	}

	@Test
	public void testNumberSpoken_2_3_1() {
		final NumberSpokenGame game = new NumberSpokenGame("2,3,1");
		final long number = game.play(2020);
		Assertions.assertEquals(78, number);
	}

	@Test
	public void testNumberSpoken_3_2_1() {
		final NumberSpokenGame game = new NumberSpokenGame("3,2,1");
		final long number = game.play(2020);
		Assertions.assertEquals(438, number);
	}

	@Test
	public void testNumberSpoken_3_1_2() {
		final NumberSpokenGame game = new NumberSpokenGame("3,1,2");
		final long number = game.play(2020);
		Assertions.assertEquals(1836, number);
	}

	@Test
	public void testNumberSpoken_8_13_1_0_18_9() {
		final NumberSpokenGame game = new NumberSpokenGame("8,13,1,0,18,9");
		final long number = game.play(2020);
		Assertions.assertEquals(755, number);
	}

	@Test
	public void testNumberSpoken_0_3_6_part2() {
		final NumberSpokenGame game = new NumberSpokenGame("0,3,6");
		final long number = game.play(30000000);
		Assertions.assertEquals(175594, number);
	}

	@Test
	public void testNumberSpoken_8_13_1_0_18_9_part2() {
		final NumberSpokenGame game = new NumberSpokenGame("8,13,1,0,18,9");
		final long number = game.play(30000000);
		Assertions.assertEquals(0, number);
	}

}
