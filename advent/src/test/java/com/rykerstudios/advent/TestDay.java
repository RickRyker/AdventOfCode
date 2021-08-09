package com.rykerstudios.advent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@SuppressWarnings("rawtypes")
@RunWith(JUnitPlatform.class)
public class TestDay<DAY extends Day> {

	protected DAY day;

	protected DAY createDay() {
		return null;
	}

	@Test
	public void testDayCreated() {
		if (day == null) {
			day = createDay();
		}
		if (day == null) {
			fail("No day to test.");
		}
	}

	@Test
	public void testPart1() {
		if (day != null) {
			assertEquals(day.getExpectedPart1(), day.part1(), "Part1 failed.");
		} else {
			fail("No day to test.");
		}
	}

	@Test
	public void testPart2() {
		if (day != null) {
			assertEquals(day.getExpectedPart2(), day.part2(), "Part2 failed.");
		} else {
			fail("No day to test.");
		}
	}

	@BeforeAll
	static void initAll() {
	}

	@BeforeEach
	void init() {
		if (day == null) {
			day = createDay();
		}
	}

	@Test
	void alwaysTrue() {
		assertTrue(true);
	}

	@Test
	@Disabled("for demonstration purposes")
	void skippedTest() {
		fail("This test should not have run.");
	}

	@AfterEach
	void tearDown() {
		if (day != null) {
			day = null;
		}
	}

	@AfterAll
	static void tearDownAll() {
	}

}
