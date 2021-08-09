package com.rykerstudios.advent.year2020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;
import com.rykerstudios.advent.year2020.Day16.TicketTranslation;

@RunWith(JUnitPlatform.class)
public class TestDay16 extends TestDay<Day16> {

	@Override
	public Day16 createDay() {
		return new Day16();
	}

	@Test
	public void testErrorRate() {
		final String[] data = new String[] {
			"class: 1-3 or 5-7",
			"row: 6-11 or 33-44",
			"seat: 13-40 or 45-50",
			"",
			"your ticket:",
			"7,1,14",
			"",
			"nearby tickets:",
			"7,3,47",
			"40,4,50",
			"55,2,20",
			"38,6,12"
		};
		final TicketTranslation translation = new TicketTranslation(data);
		Assertions.assertEquals(71, translation.getErrorRate());
	}

}
