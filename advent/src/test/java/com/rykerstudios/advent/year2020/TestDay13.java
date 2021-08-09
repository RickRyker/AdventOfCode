package com.rykerstudios.advent.year2020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;
import com.rykerstudios.advent.year2020.Day13.BusSchedule;

@RunWith(JUnitPlatform.class)
public class TestDay13 extends TestDay<Day13> {

	@Override
	public Day13 createDay() {
		return new Day13();
	}

	@Override
	@Test
	public void testPart1() {
		super.testPart1();
	}

	@Test
	public void testPart1_example1() {
		final String[] lines = new String[] { "939", "7,13,x,x,59,x,31,19" };
		final BusSchedule schedule = new BusSchedule(lines);
		final long result = schedule.getEarliestBusTimesWaitMinutes();
		Assertions.assertEquals(295, result);
	}

	@Override
	@Test
	public void testPart2() {
		super.testPart2();
	}

	@Test
	public void testPart2_example1() {
		final String[] lines = new String[] { "1068781", "7,13,x,x,59,x,31,19" };
		final BusSchedule schedule = new BusSchedule(lines);
		final long result = schedule.getConvergentTime();
		Assertions.assertEquals(1068781, result);
	}

	@Test
	public void testPart2_example2() {
		final String[] lines = new String[] { "3417", "17,x,13,19" };
		final BusSchedule schedule = new BusSchedule(lines);
		final long result = schedule.getConvergentTime();
		Assertions.assertEquals(3417, result);
	}

	@Test
	public void testPart2_example3() {
		final String[] lines = new String[] { "754018", "67,7,59,61" };
		final BusSchedule schedule = new BusSchedule(lines);
		final long result = schedule.getConvergentTime();
		Assertions.assertEquals(754018, result);
	}

	@Test
	public void testPart2_example4() {
		final String[] lines = new String[] { "779210", "67,x,7,59,61" };
		final BusSchedule schedule = new BusSchedule(lines);
		final long result = schedule.getConvergentTime();
		Assertions.assertEquals(779210, result);
	}

	@Test
	public void testPart2_example5() {
		final String[] lines = new String[] { "1261476", "67,7,x,59,61" };
		final BusSchedule schedule = new BusSchedule(lines);
		final long result = schedule.getConvergentTime();
		Assertions.assertEquals(1261476, result);
	}

	@Test
	public void testPart2_example6() {
		final String[] lines = new String[] { "1202161486", "1789,37,47,1889" };
		final BusSchedule schedule = new BusSchedule(lines);
		final long result = schedule.getConvergentTime();
		Assertions.assertEquals(1202161486, result);
	}

}
