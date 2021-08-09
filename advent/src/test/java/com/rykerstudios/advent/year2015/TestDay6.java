package com.rykerstudios.advent.year2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay6 extends TestDay<Day6> {

	@Override
	public Day6 createDay() {
		return new Day6();
	}

	@Test
	public void testInit_thenZeroLit() {
		day.init();
		assertEquals(0, day.countLit(), "After init should all be unlit.");
	}

	@Test
	public void testParseInstruction_whenTurnOn4_then4Lit() {
		day.init();
		day.parseInstruction("turn on 1,1 through 2,2", false);
		final long count = day.countLit();
		assertEquals(4L, count, "");
	}

	@Test
	public void testParseInstruction_whenTurnOn100_then100Lit() {
		day.init();
		day.parseInstruction("turn on 0,0 through 9,9", false);
		assertEquals(100L, day.countLit());
	}

	@Test
	public void testParseInstruction_whenTurnOn100Toggle64_then36Lit() {
		day.init();
		day.parseInstruction("turn on 0,0 through 9,9", false);
		day.parseInstruction("toggle 0,0 through 7,7", false);
		assertEquals(36L, day.countLit());
	}

	@Test
	public void testParseInstruction_whenTurnOn100TurnOff64_then36Lit() {
		day.init();
		day.parseInstruction("turn on 0,0 through 9,9", false);
		day.parseInstruction("turn off 0,0 through 7,7", false);
		assertEquals(36L, day.countLit());
	}

	@Test
	public void testParseInstruction_whenBrighten2For100_then200Brightness() {
		day.init();
		day.parseInstruction("toggle 0,0 through 9,9", true);
		assertEquals(200L, day.getBrightness());
	}

}
