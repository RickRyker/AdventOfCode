package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay7 extends TestDay<Day7> {

	@Override
	public Day7 createDay() {
		return new Day7();
	}

	@Test
	public void testSample() {
		final String[] dependencies = new String[] {
				"Step C must be finished before step A can begin.",
				"Step C must be finished before step F can begin.", 
				"Step A must be finished before step B can begin.",
				"Step A must be finished before step D can begin.",
				"Step B must be finished before step E can begin.",
				"Step D must be finished before step E can begin.",
				"Step F must be finished before step E can begin."};
		day.loadDependencies(dependencies);
		final String order = day.getOrderedSteps(day.getDependencies());
		assertEquals("CABDFE", order);
	}

}
