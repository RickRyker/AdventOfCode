package com.rykerstudios.advent.year2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;
import com.rykerstudios.advent.year2020.Day18.ShuntingYard;

public class TestDay18 extends TestDay<Day18> {

	@Override
	public Day18 createDay() {
		return new Day18();
	}

	@Override
	@Test
	public void testPart1() {
		assertEquals(day.getExpectedPart1(), day.part1());
	}

	@Override
	@Disabled
	@Test
	public void testPart2() {
		assertEquals(day.getExpectedPart2(), day.part2());
	}

	@Test
	public void testEvaluate_simpleAddition() {
		Assertions.assertEquals(3, ShuntingYard.calculate("1 + 2"));
	}

	@Test
	public void testEvaluate_simpleMultiplication() {
		Assertions.assertEquals(6, ShuntingYard.calculate("2 * 3"));
	}

	@Test
	public void testEvaluate_compoundAddition() {
		Assertions.assertEquals(6, ShuntingYard.calculate("1 + 2 + 3"));
	}

	@Test
	public void testEvaluate_compoundWeirdOperationOrder() {
		Assertions.assertEquals(9, ShuntingYard.calculate("1 + 2 * 3"));
	}

	@Test
	public void testEvaluate_compoundWithParentheses() {
		Assertions.assertEquals(9, ShuntingYard.calculate("(1 + 2) * 3"));
	}

	@Test
	public void testEvaluate_example1() {
		Assertions.assertEquals(71, ShuntingYard.calculate("1 + 2 * 3 + 4 * 5 + 6"));
	}

	@Test
	public void testEvaluate_example2() {
		Assertions.assertEquals(51, ShuntingYard.calculate("1 + (2 * 3) + (4 * (5 + 6))"));
	}

	@Test
	public void testEvaluate_example3() {
		Assertions.assertEquals(26, ShuntingYard.calculate("2 * 3 + (4 * 5)"));
	}

	@Test
	public void testEvaluate_example4() {
		Assertions.assertEquals(437, ShuntingYard.calculate("5 + (8 * 3 + 9 + 3 * 4 * 3)"));
	}

	@Test
	public void testEvaluate_example5() {
		Assertions.assertEquals(12240, ShuntingYard.calculate("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"));
	}

	@Test
	public void testEvaluate_example6() {
		Assertions.assertEquals(13632, ShuntingYard.calculate("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"));
	}

	@Test
	public void testEvaluate_input377() {
		Assertions.assertEquals(121695164, ShuntingYard.calculate(
				"4 + (4 + 5 * (5 * 6 * 5 + 2 * 4) + (2 * 7 * 3 * 8 * 8 + 5) * 5 + 8) * 5 + (9 + (5 + 2 + 7 + 6 * 5 * 6) * 9) * (2 * (8 * 3 + 9)) + 8"));
	}

	@Test
	public void testShuntingYard_example1() {
		Assertions.assertEquals(71, ShuntingYard.calculate("1 + 2 * 3 + 4 * 5 + 6"));
	}

}
