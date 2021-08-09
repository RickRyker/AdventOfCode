package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.CPU;
import com.rykerstudios.advent.Day;
import com.rykerstudios.advent.TestDay;

public class TestDay16 extends TestDay<Day16> {

	@Override
	public Day16 createDay() {
		return new Day16();
	}

	@Test
	public void testInit() {
		day.init(new String[] {
				"Before: [1, 1, 2, 0]", "8 1 0 3", "After: [1, 1, 2, 1]"
		});
		day.logSamples(day.samples);
		assertEquals("[1,1,2,0][8,1,0,3][1,1,2,1]", Day.toString(day.samples.get(0)));
		assertTrue(true);
	}

	@Test
	public void testSample1_when3211_and9212_then3221() {
		final int[] before = new int[] { 3, 2, 1, 1 };
		final int[] expected = new int[] { 3, 2, 2, 1 };
		final CPU cpu = new CPU(Day16.OPCODE_MAP);
		cpu.init(before);
		cpu.execute("MULR", 2, 1, 2);
		assertTrue(Day.arrayEquals(expected, cpu.registers()));
		cpu.init(before);
		cpu.execute("ADDI", 2, 1, 2);
		assertTrue(Day.arrayEquals(expected, cpu.registers()));
		cpu.init(before);
		cpu.execute("SETI", 2, 1, 2);
		assertTrue(Day.arrayEquals(expected, cpu.registers()));
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
