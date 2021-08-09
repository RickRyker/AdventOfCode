package com.rykerstudios.advent.year2015;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay5 extends TestDay<Day5> {

	@Override
	public Day5 createDay() {
		return new Day5();
	}

	@Test
	public void testGetVowelCount() {
		assertEquals(3, day.getVowelCount("ugknbfddgicrmopn"), "Vowel count");
	}

	@Test
	public void testHasDoubleLetter() {
		assertTrue(day.hasDoubledLetter("bb"), "Double Letters!");
	}

	@Test
	public void testHasDoubleLetter_whenNotContiguous() {
		assertFalse(day.hasDoubledLetter("bcb"), "Double Letters!");
	}

	@Test
	public void testHasForbiddenLetters_whenAB() {
		assertTrue(day.hasForbiddenLetters("ab"), "No Forbidden Letters!");
	}

	@Test
	public void testHasForbiddenLetters_whenCD() {
		assertTrue(day.hasForbiddenLetters("cd"), "No Forbidden Letters!");
	}

	@Test
	public void testHasForbiddenLetters_whenPQ() {
		assertTrue(day.hasForbiddenLetters("pq"), "No Forbidden Letters!");
	}

	@Test
	public void testHasForbiddenLetters_whenXY() {
		assertTrue(day.hasForbiddenLetters("xy"), "No Forbidden Letters!");
	}

	@Test
	public void testIsNiceWhenValid_thenNice() {
		assertTrue(day.isNice("ugknbfddgicrmopn"), "Not Nice!");
	}

	@Test
	public void testIsNiceWhenTripleVowel_thenNice() {
		assertTrue(day.isNice("aaa"), "Not Nice!");
	}

	@Test
	public void testIsNiceWhenOneVowel_thenNaughty() {
		assertFalse(day.isNice("dvszwmarrgswjxmb"), "Not Naughty!");
	}

	@Test
	public void testIsNiceWhenNoDoubledLetter_thenNaughty() {
		assertFalse(day.isNice("jchzalrnumimnmhp"), "Not Naughty!");
	}

	@Test
	public void testIsNiceWhenForbidden_thenNaughty() {
		assertFalse(day.isNice("haegwjzuvuyypxyu"), "Not Naughty!");
	}

	@Test
	public void testIsNice2WhenPairsOverlap_thenNaughty() {
		assertFalse(day.isNice2("aaa"), "No overlaps!");
	}

	@Test
	public void testIsNice2WhenPairsHaveNoOverlap_thenNice() {
		assertTrue(day.isNice2("xyxy"), "Overlaps!");
		assertTrue(day.isNice2("aabaa"), "Overlaps!");
	}

	@Test
	public void testIsNice2_thenNice() {
		assertTrue(day.isNice2("qjhvhtzxzqqjkmpb"), "Not Nice!");
		assertTrue(day.isNice2("xxyxx"), "Not Nice!");
		assertTrue(day.isNice2("aaaa"), "Not Nice!");
		assertTrue(day.isNice2("aaaaa"), "Not Nice!");
	}

	@Test
	public void testIsNice2_thenNaughty() {
		assertFalse(day.isNice2("uurcxstgmygtbstg"), "Not Naughty!");
		assertFalse(day.isNice2("ieodomkazucvgmuy"), "Not Naughty!");
	}

	@Override
	@Test
	public void testPart2() {
		assertEquals(day.getExpectedPart2(), day.part2(), "Part2 failed.");
	}

}