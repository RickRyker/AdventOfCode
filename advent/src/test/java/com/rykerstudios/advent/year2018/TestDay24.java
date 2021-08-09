package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay24 extends TestDay<Day24> {

	@Override
	public Day24 createDay() {
		return new Day24();
	}

	@Test
	public void testPower_when18unitsWith8ap() {
		final Day24.Group group = new Day24.Group();
		group.units = 18;
		group.hitPoints = 729;
		group.weaknesses = new String[] { "fire" };
		group.immunities = new String[] { "cold", "slashing" };
		group.attackDamage = 8;
		group.attackType = "radiation";
		group.initiative = 10;
		assertEquals(144, group.power());
	}

	@Test
	public void testSample() {
		final String[] lines = new String[] { "Immune System:",
				"17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2",
				"989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3",
				"", "Infection:",
				"801 units each with 4706 hit points (weak to radiation) with an attack that does 116 bludgeoning damage at initiative 1",
				"4485 units each with 2961 hit points (immune to radiation; weak to fire, cold) with an attack that does 12 slashing damage at initiative 4" };
		day.init(lines);
		day.combat();
		assertEquals(5216, day.getTotalUnits());
	}

	@Test
	public void testSamplePart2() {
		final String[] lines = new String[] { "Immune System:",
				"17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2",
				"989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3",
				"", "Infection:",
				"801 units each with 4706 hit points (weak to radiation) with an attack that does 116 bludgeoning damage at initiative 1",
				"4485 units each with 2961 hit points (immune to radiation; weak to fire, cold) with an attack that does 12 slashing damage at initiative 4" };
		day.init(lines);
		day.boost(1570);
		day.combat();
		assertEquals(51, day.getTotalUnits());
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
