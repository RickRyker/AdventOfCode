package com.rykerstudios.advent.year2020;

import java.util.TreeSet;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;
import com.rykerstudios.advent.year2020.Day20.JurassicJigsaw;

@RunWith(JUnitPlatform.class)
public class TestDay20 extends TestDay<Day20> {

	@Override
	public Day20 createDay() {
		return new Day20();
	}

	@Override
	@Disabled
	@Test
	public void testPart1() {
		super.testPart1();
	}

	@Test
	public void testPart1_example() {
		final String[] lines = createDay().getTestDataLines();
		final JurassicJigsaw jigsaw = new JurassicJigsaw(lines);
		jigsaw.markTilesWithMatchingEdges();
		System.out.println(new TreeSet<>(jigsaw.tiles));
	}

	@Override
	@Disabled
	@Test
	public void testPart2() {
		super.testPart2();
	}

}
