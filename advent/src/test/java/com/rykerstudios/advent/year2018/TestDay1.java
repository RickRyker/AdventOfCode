package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rykerstudios.advent.TestDay;

public class TestDay1 extends TestDay<Day1> {

	@Override
	public Day1 createDay() {
		return new Day1();
	}

	public void testFrequencyChanges_when1231_then10() {
		long frequency = 0L;
		frequency += 1;
		frequency += 2;
		frequency += 3;
		frequency += 1;
		assertEquals(10, frequency);
	}

	public void testFrequencyChanges_when111_then3() {
		long frequency = 0L;
		frequency += 1;
		frequency += 1;
		frequency += 1;
		assertEquals(3, frequency);
	}

	public void testFrequencyChanges_when11m2_then0() {
		long frequency = 0L;
		frequency += 1;
		frequency += 1;
		frequency -= 2;
		assertEquals(0, frequency);
	}

	public void testFrequencyChanges_whenMinus1Minus2Minus3_thenMinus6() {
		long frequency = 0L;
		frequency -= 1;
		frequency -= 2;
		frequency -= 3;
		assertEquals(-6, frequency);
	}

}
