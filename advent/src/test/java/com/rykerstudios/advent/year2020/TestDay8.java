package com.rykerstudios.advent.year2020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay8 extends TestDay<Day8> {

	@Override
	public Day8 createDay() {
		return new Day8();
	}

	@Test
	public void testAccumulatorLoop() {
		final String[] lines = new String[] { "nop +0", "acc +1", "jmp +4", "acc +3", "jmp -3", "acc -99", "acc +1",
				"jmp -4", "acc +6" };
		final Handheld handheld = new Handheld(lines);
		Assertions.assertEquals(0, handheld.getAccumulator());
		Assertions.assertEquals(0, handheld.getInstructionPointer());
		handheld.findLoop();
		Assertions.assertEquals(1, handheld.getInstructionPointer());
		Assertions.assertEquals(5, handheld.getAccumulator());
	}

	@Test // (expected = RuntimeException.class)
	public void testAccumulatorChange7() {
		final String[] lines = new String[] { "nop +0", "acc +1", "jmp +4", "acc +3", "jmp -3", "acc -99", "acc +1",
				"jmp -4", "acc +6" };
		final Handheld handheld = new Handheld(lines);
		handheld.changeInstruction(7);
		Assertions.assertEquals(0, handheld.getAccumulator());
		Assertions.assertEquals(0, handheld.getInstructionPointer());
		try {
			handheld.findLoop();
		} catch (final RuntimeException e) {
			Assertions.assertEquals(9, handheld.getInstructionPointer());
			Assertions.assertEquals(8, handheld.getAccumulator());
		}
	}

}
