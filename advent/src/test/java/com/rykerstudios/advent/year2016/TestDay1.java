package com.rykerstudios.advent.year2016;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay1 extends TestDay<Day1> {

	@Override
	public Day1 createDay() {
		return new Day1();
	}

}
