package com.rykerstudios.advent.year2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay8 extends TestDay<Day8> {

	@Override
	public Day8 createDay() {
		return new Day8();
	}

	@Test
	public void testDecode_whenQuotedEscapedDoubleQuote() {
		assertEquals(3, "\"\"\"".length());
		assertEquals(1, "\"".length());
		assertEquals(1, day.decode("\"\"\"").length());
		assertEquals("\"", day.decode("\"\"\""));
	}

	@Test
	public void testDecode_whenQuotedEscapedBackslash() {
		assertEquals(3, "\"\\\"".length());
		assertEquals(1, "\\".length());
		assertEquals(1, day.decode("\"\\\"").length());
		assertEquals("\\", day.decode("\"\\\""));
	}

	@Test
	public void testDecode_whenEscapedCharacter() {
		assertEquals(4, "\\x32".length());
		assertEquals(1, "?".length());
		assertEquals(1, day.decode("\\x32").length());
		assertEquals("?", day.decode("\\x32"));
	}

	@Test
	public void testDecode_whenQuotedString() {
		assertEquals(3, "\"a\"".length());
		assertEquals(1, "a".length());
		assertEquals(1, day.decode("\"a\"").length());
		assertEquals("a", day.decode("\"a\""));
	}

	@Test
	public void testDecode_whenEmptyString() {
		assertEquals(2, "\"\"".length());
		assertEquals(0, "".length());
		assertEquals(0, day.decode("\"\"").length());
		assertEquals("", day.decode("\"\""));
	}

	@Test
	public void testDecode_whenQuotedABC() {
		assertEquals(5, "\"abc\"".length());
		assertEquals(3, "abc".length());
		assertEquals(3, day.decode("\"abc\"").length());
		assertEquals("abc", day.decode("\"abc\""));
	}

	@Test
	public void test3() {
		assertEquals(6, "\"\\x27\"".length());
		assertEquals(1, "'".length());
		assertEquals(1, day.decode("\"\\x27\"").length());
		assertEquals("?", day.decode("\"\\x27\""));
	}

}