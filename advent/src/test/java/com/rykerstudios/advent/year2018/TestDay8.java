package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay8 extends TestDay<Day8> {

	@Override
	public Day8 createDay() {
		return new Day8();
	}

	@Test
	public void testSample() {
		final String content = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
		final Day8.Node root = day.parse(content, 0);
		// System.out.println("root=" + root);
		assertEquals(138, root.getMetadataSum());
		assertEquals(66, root.getValue());
	}

}
