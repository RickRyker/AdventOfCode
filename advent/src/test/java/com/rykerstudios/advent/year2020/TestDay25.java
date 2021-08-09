package com.rykerstudios.advent.year2020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;
import com.rykerstudios.advent.year2020.Day25.ComboBreaker;

@RunWith(JUnitPlatform.class)
public class TestDay25 extends TestDay<Day25> {

	@Override
	public Day25 createDay() {
		return new Day25();
	}

	@Override
	@Disabled
	@Test
	public void testPart1() {
		super.testPart1();
	}

	@Override
	@Disabled
	@Test
	public void testPart2() {
		super.testPart2();
	}

	@Test
	public void testFindLoopSize_example1() {
		Assertions.assertEquals(8, ComboBreaker.findLoopSize(5764801));
	}

	@Test
	public void testLoopSize_example2() {
		Assertions.assertEquals(11, ComboBreaker.findLoopSize(17807724));
	}

	@Test
	public void testTransform_example1() {
		Assertions.assertEquals(14897079, ComboBreaker.transform(5764801, 11));
	}

	@Test
	public void testTransform_example2() {
		Assertions.assertEquals(14897079, ComboBreaker.transform(17807724, 8));
	}

	@Test
	public void testCalculateEncryptionKey_example1() {
		Assertions.assertEquals(14897079, ComboBreaker.calcEncryptionKey(5764801, 17807724));
	}

	@Test
	public void testCalculateEncryptionKey_part1() {
		Assertions.assertEquals(14897079, ComboBreaker.calcEncryptionKey(9659666, 12320657));
	}

}
