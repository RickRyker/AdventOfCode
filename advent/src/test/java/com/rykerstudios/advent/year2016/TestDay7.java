package com.rykerstudios.advent.year2016;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay7 extends TestDay<Day7> {

	@Override
	public Day7 createDay() {
		return new Day7();
	}

}