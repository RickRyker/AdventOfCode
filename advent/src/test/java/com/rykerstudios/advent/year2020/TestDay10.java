package com.rykerstudios.advent.year2020;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay10 extends TestDay<Day10> {

	@Override
	public Day10 createDay() {
		return new Day10();
	}

	@Test
	public void testCountAdapterPaths() {
		final Day10 day = createDay();
		final long[] adapters = Day10.toLongArray(new String[] {
				"16", "10", "15", "5", "1", "11", 
				"7", "19", "6", "12", "4" });
		Arrays.sort(adapters);
		final long paths = day.getCountAdapterPaths(adapters);
		Assertions.assertEquals(8, paths);
	}

	@Test
	public void testCountAdapterPaths2() {
		final Day10 day = createDay();
		final long[] adapters = Day10.toLongArray(
				"28 33 18 42 31 14 46 20 48 47 24 23 49 45 19 " +
						"38 39 11 1 32 25 35 8 17 7 9 4 2 34 10 3");
		Arrays.sort(adapters);
		final long product = day.getCountAdapterPaths(adapters);
		Assertions.assertEquals(19208L, product);
	}

	@Test
	public void testJoltageProduct() {
		final Day10 day = createDay();
		final long[] adapters = Day10
				.toLongArray(new String[] { "16", "10", "15", "5", "1", "11", "7", "19", "6", "12", "4" });
		final long product = day.getJoltageProduct(adapters);
		Assertions.assertEquals(35, product);
	}

	@Test
	public void testJoltageProduct2() {
		final Day10 day = createDay();
		final long[] adapters = Day10
				.toLongArray("28 33 18 42 31 14 46 20 48 47 24 23 49 45 19 38 39 11 1 32 25 35 8 17 7 9 4 2 34 10 3");
		final long product = day.getJoltageProduct(adapters);
		Assertions.assertEquals(220, product);
	}

}
