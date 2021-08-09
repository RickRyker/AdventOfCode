package com.rykerstudios.advent.year2015;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay4 extends TestDay<Day4> {

	@Override
	public Day4 createDay() {
		return new Day4();
	}

	@Test
	public void testGetDigest_whenHashing_thenVerifying() throws NoSuchAlgorithmException {
		final String password = "ILoveJava";
		final String hash = day.getDigestAsString(password);
		final String expected = "35454B055CC325EA1AF2126E27707052";
		assertEquals(expected, hash);
	}

	@Test
	public void testGetDigest_abcdef_then_609043() {
		final String key = "abcdef";
		final Long n = 609043L;
		final String text = key + Long.toString(n);
		final String hash = day.getDigestAsString(text);
		System.out.println(hash);
		assertTrue(hash.equals("000001DBBFA3A5C83A2D506429C7B00E"));
	}

	@Test
	public void testGetDigest_pqrstuv_then_1048970() {
		final String key = "pqrstuv";
		final Long n = 1048970L;
		final String text = key + Long.toString(n);
		final String hash = day.getDigestAsString(text);
		System.out.println(hash);
		assertTrue(hash.equals("000006136EF2FF3B291C85725F17325C"));
	}

	@Override
	@Test
	public void testPart2() {
		assertEquals(day.getExpectedPart2(), day.part2(), "Part2 failed.");
	}

}