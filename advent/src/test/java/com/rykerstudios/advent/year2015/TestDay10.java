package com.rykerstudios.advent.year2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay10 extends TestDay<Day10> {

	@Override
	public Day10 createDay() {
		return new Day10();
	}

	@Test
	public void testLookAndSay_when1_then11() {
		assertEquals("11", day.lookAndSay("1"));
	}

	@Test
	public void testLookAndSay_when11_then21() {
		assertEquals("21", day.lookAndSay("11"));
	}

	@Test
	public void testLookAndSay_when21_then1211() {
		assertEquals("1211", day.lookAndSay("21"));
	}

	@Test
	public void testLookAndSay_when1211_then111221() {
		assertEquals("111221", day.lookAndSay("1211"));
	}

	@Test
	public void testLookAndSay_when111221_then312211() {
		assertEquals("312211", day.lookAndSay("111221"));
	}

}