package com.rykerstudios.advent.year2017;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay3 extends TestDay<Day3> {

	@Override
	public Day3 createDay() {
		return new Day3();
	}

}