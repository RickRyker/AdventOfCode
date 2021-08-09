package com.rykerstudios.advent.year2016;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay2 extends TestDay<Day2> {

	@Override
	public Day2 createDay() {
		return new Day2();
	}

}