package com.rykerstudios.advent.year2020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay5 extends TestDay<Day5> {

	@Override
	public Day5 createDay() {
		return new Day5();
	}

	@Test
	public void testBoardingPass_FBFBBFFRLR() {
		final BoardingPass pass = new BoardingPass("FBFBBFFRLR");
		Assertions.assertEquals(44, pass.getRow());
		Assertions.assertEquals(5, pass.getCol());
		Assertions.assertEquals(357, pass.getSeatId());
	}

	@Test
	public void testBoardingPass_BFFFBBFRRR() {
		final BoardingPass pass = new BoardingPass("BFFFBBFRRR");
		Assertions.assertEquals(70, pass.getRow());
		Assertions.assertEquals(7, pass.getCol());
		Assertions.assertEquals(567, pass.getSeatId());
	}

	@Test
	public void testBoardingPass_FFFBBBFRRR() {
		final BoardingPass pass = new BoardingPass("FFFBBBFRRR");
		Assertions.assertEquals(14, pass.getRow());
		Assertions.assertEquals(7, pass.getCol());
		Assertions.assertEquals(119, pass.getSeatId());
	}

	@Test
	public void testBoardingPass_BBFFBBFRLL() {
		final BoardingPass pass = new BoardingPass("BBFFBBFRLL");
		Assertions.assertEquals(102, pass.getRow());
		Assertions.assertEquals(4, pass.getCol());
		Assertions.assertEquals(820, pass.getSeatId());
	}

}
