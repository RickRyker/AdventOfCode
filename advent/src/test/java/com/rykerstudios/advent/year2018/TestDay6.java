package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay6 extends TestDay<Day6> {

	@Override
	public Day6 createDay() {
		return new Day6();
	}

	@Test
	public void testSample() {
		final List<int[]> list = new ArrayList<int[]>();
		list.add(new int[] { 1, 1 });
		list.add(new int[] { 1, 6 });
		list.add(new int[] { 8, 3 });
		list.add(new int[] { 3, 4 });
		list.add(new int[] { 5, 5 });
		list.add(new int[] { 8, 9 });
		day.init(list);
		day.fillGridWithClosest();
		for (int y = 0; y < day.maxY; y++) {
			for (int x = 0; x < day.maxX; x++) {
				System.out.print(day.grid[x][y]);
			}
			System.out.println("");
		}
		final List<Integer> sizes = new ArrayList<Integer>(day.getAreaSizes().values());
		Collections.sort(sizes);
		Collections.reverse(sizes);
		System.out.println(sizes);
		System.out.println(sizes.get(0));
		final Integer expected = 17;
		assertEquals(expected, sizes.get(0));
	}

}
