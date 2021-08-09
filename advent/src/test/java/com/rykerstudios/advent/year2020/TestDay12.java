package com.rykerstudios.advent.year2020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay12 extends TestDay<Day12> {

	@Override
	public Day12 createDay() {
		return new Day12();
	}

	@Test
	public void testMovement_Example1() {
		final FerryNavigation ferry = new FerryNavigation("F10 N3 F7 R90 F11".split(" "), false);
		ferry.moveAll();
		Assertions.assertEquals(25, ferry.getDistanceFromOrigin());
	}

	@Test
	public void testR90() {
		final FerryNavigation ferry = new FerryNavigation("R90".split(" "), false);
		ferry.moveAll();
		Assertions.assertEquals(0, ferry.getEWLocation());
		Assertions.assertEquals(0, ferry.getNSLocation());
		Assertions.assertEquals('S', ferry.getFacing());
	}

	@Test
	public void testL90() {
		final FerryNavigation ferry = new FerryNavigation("L90".split(" "), false);
		ferry.moveAll();
		Assertions.assertEquals(0, ferry.getEWLocation());
		Assertions.assertEquals(0, ferry.getNSLocation());
		Assertions.assertEquals('N', ferry.getFacing());
	}

	@Test
	public void testMovement_ExamplePart2() {
		final FerryNavigation ferry = new FerryNavigation("F10 N3 F7 R90 F11".split(" "), true);
		ferry.moveAll();
		Assertions.assertEquals(286, ferry.getDistanceFromOrigin());
	}

	@Test
	public void testInitialWaypoint() {
		final FerryNavigation ferry = new FerryNavigation(new String[] {}, true);
		ferry.reset();
		Assertions.assertEquals(10, ferry.getEWWaypoint());
		Assertions.assertEquals(1, ferry.getNSWaypoint());
	}

	@Test
	public void testMoveWaypointLeft90() {
		final FerryNavigation ferry = new FerryNavigation(new String[] {}, true);
		ferry.moveWaypoint("L", 90);
		Assertions.assertEquals(-1, ferry.getEWWaypoint());
		Assertions.assertEquals(10, ferry.getNSWaypoint());
	}

	@Test
	public void testMoveWaypointLeft180() {
		final FerryNavigation ferry = new FerryNavigation(new String[] {}, true);
		ferry.moveWaypoint("L", 180);
		Assertions.assertEquals(-10, ferry.getEWWaypoint());
		Assertions.assertEquals(-1, ferry.getNSWaypoint());
	}

	@Test
	public void testMoveWaypointLeft270() {
		final FerryNavigation ferry = new FerryNavigation(new String[] {}, true);
		ferry.moveWaypoint("L", 270);
		Assertions.assertEquals(1, ferry.getEWWaypoint());
		Assertions.assertEquals(-10, ferry.getNSWaypoint());
	}

	@Test
	public void testMoveWaypointRight90() {
		final FerryNavigation ferry = new FerryNavigation(new String[] {}, true);
		ferry.moveWaypoint("R", 90);
		Assertions.assertEquals(1, ferry.getEWWaypoint());
		Assertions.assertEquals(-10, ferry.getNSWaypoint());
	}

	@Test
	public void testMoveWaypointRight180() {
		final FerryNavigation ferry = new FerryNavigation(new String[] {}, true);
		ferry.moveWaypoint("R", 180);
		Assertions.assertEquals(-10, ferry.getEWWaypoint());
		Assertions.assertEquals(-1, ferry.getNSWaypoint());
	}

	@Test
	public void testMoveWaypointRight270() {
		final FerryNavigation ferry = new FerryNavigation(new String[] {}, true);
		ferry.moveWaypoint("R", 270);
		Assertions.assertEquals(-1, ferry.getEWWaypoint());
		Assertions.assertEquals(10, ferry.getNSWaypoint());
	}

}
