package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.CPU;
import com.rykerstudios.advent.TestDay;

public class TestDay19 extends TestDay<Day19> {

	@Override
	public Day19 createDay() {
		return new Day19();
	}

	@Test
	public void testSample() {
		final String[] lines = new String[] {
				"#ip 0", 
				"seti 5 0 1", 
				"seti 6 0 2", 
				"addi 0 1 0", 
				"addr 1 2 3", 
				"setr 1 0 0", 
				"seti 8 0 4", 
				"seti 9 0 5"};
		final CPU cpu = new CPU(6);
		cpu.setProgram(lines);
		cpu.run();
		assertEquals(6, cpu.registers()[0]);
	}

	@Override
	@Test
	public void testPart1() {
		assertEquals(day.getExpectedPart1(), day.part1());
	}

	@Override
	@Test
	public void testPart2() {
		assertEquals(day.getExpectedPart2(), day.part2());
	}

}
