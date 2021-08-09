package com.rykerstudios.advent.year2020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.Day;
import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay11 extends TestDay<Day11> {

	@Override
	public Day11 createDay() {
		return new Day11();
	}

	@Override
	@Test
	public void testPart1() {
		assertEquals(day.getExpectedPart1(), day.part1());
	}

	@Test
	public void testWaitingArea() {
		final String[] initialSeating = new String[] {
				"L.LL.LL.LL", "LLLLLLL.LL", "L.L.L..L..", "LLLL.LL.LL", "L.LL.LL.LL",
				"L.LLLLL.LL", "..L.L.....", "LLLLLLLLLL", "L.LLLLLL.L", "L.LLLLL.LL" };
		final WaitingArea waitingArea = new WaitingArea(initialSeating, 4);
		while (waitingArea.applySeatingRules(false)) {
			// System.out.println("Occupied Seats: " + waitingArea.getOccupiedSeats());
		}
		System.out.println("Occupied Seats: " + waitingArea.getOccupiedSeats());
		final int occupiedSeats = waitingArea.getOccupiedSeats();
		Assertions.assertEquals(37, occupiedSeats);
	}

	@Test
	public void testCountNeighbors() {
		final String[] initialSeating = new String[] { "#####", "#####", "#####", "#####", "#####" };
		final WaitingArea waitingArea = new WaitingArea(initialSeating, 4);
		assertEquals(8, waitingArea.countNeighbors(waitingArea.getSeating(), 1, 1));
		assertEquals(8, waitingArea.countNeighbors(waitingArea.getSeating(), 2, 2));
		assertEquals(8, waitingArea.countNeighbors(waitingArea.getSeating(), 3, 3));
		assertEquals(3, waitingArea.countNeighbors(waitingArea.getSeating(), 0, 0));
		assertEquals(5, waitingArea.countNeighbors(waitingArea.getSeating(), 0, 2));
	}

	@Test
	public void testIsEmpty() {
		final String[] initialSeating = new String[] { "#.L", "LLL", "L.L" };
		final WaitingArea waitingArea = new WaitingArea(initialSeating, 4);
		assertFalse(waitingArea.isEmpty(waitingArea.getSeating(), 0, 0));
		assertFalse(waitingArea.isEmpty(waitingArea.getSeating(), 0, 1));
		assertTrue(waitingArea.isEmpty(waitingArea.getSeating(), 0, 2));
		assertFalse(waitingArea.isEmpty(waitingArea.getSeating(), -1, 2));
		assertFalse(waitingArea.isEmpty(waitingArea.getSeating(), 0, -1));
		assertFalse(waitingArea.isEmpty(waitingArea.getSeating(), 3, 2));
		assertFalse(waitingArea.isEmpty(waitingArea.getSeating(), 0, 3));
	}

	@Test
	public void testIsFloor() {
		final String[] initialSeating = new String[] { "#.L", "LLL", "L.L" };
		final WaitingArea waitingArea = new WaitingArea(initialSeating, 4);
		assertFalse(waitingArea.isFloor(waitingArea.getSeating(), 0, 0));
		assertTrue(waitingArea.isFloor(waitingArea.getSeating(), 0, 1));
		assertFalse(waitingArea.isFloor(waitingArea.getSeating(), 0, 2));
		assertFalse(waitingArea.isFloor(waitingArea.getSeating(), -1, 2));
		assertFalse(waitingArea.isFloor(waitingArea.getSeating(), 0, -1));
		assertFalse(waitingArea.isFloor(waitingArea.getSeating(), 3, 2));
		assertFalse(waitingArea.isFloor(waitingArea.getSeating(), 0, 3));
	}

	@Test
	public void testIsOccupied() {
		final String[] initialSeating = new String[] { "#.L", "###", "#.#" };
		final WaitingArea waitingArea = new WaitingArea(initialSeating, 4);
		assertTrue(waitingArea.isOccupied(waitingArea.getSeating(), 0, 0));
		assertFalse(waitingArea.isOccupied(waitingArea.getSeating(), 0, 1));
		assertFalse(waitingArea.isOccupied(waitingArea.getSeating(), 0, 2));
		assertFalse(waitingArea.isOccupied(waitingArea.getSeating(), -1, 2));
		assertFalse(waitingArea.isOccupied(waitingArea.getSeating(), 0, -1));
		assertFalse(waitingArea.isOccupied(waitingArea.getSeating(), 3, 3));
		assertFalse(waitingArea.isOccupied(waitingArea.getSeating(), 0, 3));
	}

	@Test
	public void testIsOutOfBounds() {
		final String[] initialSeating = new String[] { "." };
		final WaitingArea waitingArea = new WaitingArea(initialSeating, 4);
		assertFalse(waitingArea.isOutOfBounds(0, 0));
		assertTrue(waitingArea.isOutOfBounds(-1, 0));
		assertTrue(waitingArea.isOutOfBounds(0, -1));
		assertTrue(waitingArea.isOutOfBounds(1, 0));
		assertTrue(waitingArea.isOutOfBounds(0, 1));
	}

	@Test
	public void testSeenNeighbors_Example1() {
		final String[] initialSeating = new String[] {
				".......#.", "...#.....", ".#.......",
				".........", "..#L....#", "....#....",
				".........", "#........", "...#....." };
		final WaitingArea waitingArea = new WaitingArea(initialSeating, 5);
		assertEquals(8, waitingArea.countFirstSeen(waitingArea.getSeating(), 4, 3));
	}

	@Test
	public void testSeenNeighbors_Example2() {
		final String[] initialSeating = new String[] {
				".............", ".L.L.#.#.#.#.", "............." };
		final WaitingArea waitingArea = new WaitingArea(initialSeating, 5);
		assertEquals(0, waitingArea.countFirstSeen(waitingArea.getSeating(), 1, 1));
	}

	@Test
	public void testSeenNeighbors_Example3() {
		final String[] initialSeating = new String[] {
				".##.##.",
				"#.#.#.#",
				"##...##",
				"...L...",
				"##...##",
				"#.#.#.#",
				".##.##."};
		final WaitingArea waitingArea = new WaitingArea(initialSeating, 5);
		assertEquals(0, waitingArea.countFirstSeen(waitingArea.getSeating(), 3, 3));
	}

	@Test
	public void testWaitingArea_checkFirstSeen() {
		final String[] initialSeating = new String[] {
				"L.LL.LL.LL", "LLLLLLL.LL", "L.L.L..L..", "LLLL.LL.LL", "L.LL.LL.LL",
				"L.LLLLL.LL", "..L.L.....", "LLLLLLLLLL", "L.LLLLLL.L", "L.LLLLL.LL" };
		final WaitingArea waitingArea = new WaitingArea(initialSeating, 5);
		waitingArea.applySeatingRules(true);
		Assertions.assertEquals(71, waitingArea.getOccupiedSeats());
		final char[][] expected = new char[][] {
				{ '#', '.', '#', '#', '.', '#', '#', '.', '#', '#' },
				{ '#', '#', '#', '#', '#', '#', '#', '.', '#', '#' },
				{ '#', '.', '#', '.', '#', '.', '.', '#', '.', '.' },
				{ '#', '#', '#', '#', '.', '#', '#', '.', '#', '#' },
				{ '#', '.', '#', '#', '.', '#', '#', '.', '#', '#' },
				{ '#', '.', '#', '#', '#', '#', '#', '.', '#', '#' },
				{ '.', '.', '#', '.', '#', '.', '.', '.', '.', '.' },
				{ '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
				{ '#', '.', '#', '#', '#', '#', '#', '#', '.', '#' },
				{ '#', '.', '#', '#', '#', '#', '#', '.', '#', '#' } };
		Assertions.assertTrue(Day.arrayEquals(expected, waitingArea.getSeating()));
		waitingArea.applySeatingRules(true);
		Assertions.assertEquals(7, waitingArea.getOccupiedSeats());
		final char[][] expected2 = new char[][] {
				{ '#', '.', 'L', 'L', '.', 'L', 'L', '.', 'L', '#' },
				{ '#', 'L', 'L', 'L', 'L', 'L', 'L', '.', 'L', 'L' },
				{ 'L', '.', 'L', '.', 'L', '.', '.', 'L', '.', '.' },
				{ 'L', 'L', 'L', 'L', '.', 'L', 'L', '.', 'L', 'L' },
				{ 'L', '.', 'L', 'L', '.', 'L', 'L', '.', 'L', 'L' },
				{ 'L', '.', 'L', 'L', 'L', 'L', 'L', '.', 'L', 'L' },
				{ '.', '.', 'L', '.', 'L', '.', '.', '.', '.', '.' },
				{ 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', '#' },
				{ '#', '.', 'L', 'L', 'L', 'L', 'L', 'L', '.', 'L' },
				{ '#', '.', 'L', 'L', 'L', 'L', 'L', '.', 'L', '#' } };
		Assertions.assertTrue(Day.arrayEquals(expected2, waitingArea.getSeating()));
		while (waitingArea.applySeatingRules(true)) {
			// System.out.println("Occupied Seats: " + waitingArea.getOccupiedSeats());
		}
		System.out.println("Occupied Seats: " + waitingArea.getOccupiedSeats());
		final int occupiedSeats = waitingArea.getOccupiedSeats();
		Assertions.assertEquals(26, occupiedSeats);
	}

}
