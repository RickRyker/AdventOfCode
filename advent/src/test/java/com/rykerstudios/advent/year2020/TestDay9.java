package com.rykerstudios.advent.year2020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay9 extends TestDay<Day9> {

	@Override
	public Day9 createDay() {
		return new Day9();
	}

	@Test
	public void testFindAnomaly() {
		final XmasCipher cipher = new XmasCipher(5, new String[] {
				"35", "20", "15", "25", "47", "40", "62",
				"55", "65", "95", "102", "117", "150", "182",
				"127", "219", "299", "277", "309", "576" });
		final long anomaly = cipher.findAnomaly();
		Assertions.assertEquals(127, anomaly);
	}

	@Test
	public void testFindWeakness() {
		final XmasCipher cipher = new XmasCipher(5, new String[] {
				"35", "20", "15", "25", "47", "40", "62",
				"55", "65", "95", "102", "117", "150", "182",
				"127", "219", "299", "277", "309", "576" });
		final long anomaly = cipher.findAnomaly();
		final long weakness = cipher.findWeakness(anomaly);
		Assertions.assertEquals(62, weakness);
	}

}
