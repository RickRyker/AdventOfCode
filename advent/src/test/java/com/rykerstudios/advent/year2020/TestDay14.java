package com.rykerstudios.advent.year2020;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;
import com.rykerstudios.advent.year2020.Day14.InitializationData;

@RunWith(JUnitPlatform.class)
public class TestDay14 extends TestDay<Day14> {

	@Override
	public Day14 createDay() {
		return new Day14();
	}

	@Test
	public void testPart1_example() {
		final InitializationData initialization = new InitializationData(false, new String[] {
				"mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
				"mem[8] = 11",
				"mem[7] = 101",
				"mem[8] = 0"
		});
		initialization.execute();
		final long sum = initialization.getSumOfAllValues();
		Assertions.assertEquals(165L, sum);
	}

	@Test
	public void testGetFloatMasks_0X1X() {
		final InitializationData initialization = new InitializationData(true, new String[] {});
		final Set<String> masks = initialization.getFloatMasks("0X1X");
		Assertions.assertTrue(masks.contains("0010"));
		Assertions.assertTrue(masks.contains("0011"));
		Assertions.assertTrue(masks.contains("0110"));
		Assertions.assertTrue(masks.contains("0111"));
		Assertions.assertEquals(4, masks.size());
	}

	@Test
	public void testGetFloatMasks_XX1X() {
		final InitializationData initialization = new InitializationData(true, new String[] {});
		final Set<String> masks = initialization.getFloatMasks("XX1X");
		Assertions.assertTrue(masks.contains("0010"));
		Assertions.assertTrue(masks.contains("0011"));
		Assertions.assertTrue(masks.contains("0110"));
		Assertions.assertTrue(masks.contains("0111"));
		Assertions.assertTrue(masks.contains("1010"));
		Assertions.assertTrue(masks.contains("1011"));
		Assertions.assertTrue(masks.contains("1110"));
		Assertions.assertTrue(masks.contains("1111"));
		Assertions.assertEquals(8, masks.size());
	}

	@Test
	public void testGetFloatMasks_111X() {
		final InitializationData initialization = new InitializationData(true, new String[] {});
		final Set<String> masks = initialization.getFloatMasks("111X");
		Assertions.assertTrue(masks.contains("1110"));
		Assertions.assertTrue(masks.contains("1111"));
		Assertions.assertEquals(2, masks.size());
	}

	@Test
	public void testPart2_example() {
		final InitializationData initialization = new InitializationData(true,
				new String[] {
				"mask = 000000000000000000000000000000X1001X",
				"mem[42] = 100",
				"mask = 00000000000000000000000000000000X0XX",
				"mem[26] = 1"
		});
		initialization.execute();
		final long sum = initialization.getSumOfAllValues();
		Assertions.assertEquals(208L, sum);
	}

}
