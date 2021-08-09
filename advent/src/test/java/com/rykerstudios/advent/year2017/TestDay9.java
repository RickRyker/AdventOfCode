package com.rykerstudios.advent.year2017;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay9 extends TestDay<Day9> {

	@Override
	public Day9 createDay() {
		return new Day9();
	}

}