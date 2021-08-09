package com.rykerstudios.advent.year2018;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rykerstudios.advent.TestDay;

public class TestDay9 extends TestDay<Day9> {

	@Override
	public Day9 createDay() {
		return new Day9();
	}

	@Test
	public void testSampleSteps() {
		final int players = 10;
		final int marbles = 25;
		day.init(players, marbles);
		assertEquals(players, day.totalPlayers);
		assertEquals(marbles, day.totalMarbles);
		assertEquals(0, day.marbleNumber);
		assertEquals(1, day.currentPosition);
		assertEquals(0, day.currentPlayer);
		assertEquals(1, day.circle.length);
		assertEquals(0, day.circle[0]);
		assertEquals(players + 1, day.scores.length);
		for (int i = 0; i <= day.totalPlayers; i++) {
			assertEquals(0, day.scores[i]);
		}
		while (day.marbleNumber < marbles) {
			for (int i = 1; i <= players; i++) {
				day.logCircle();
				day.move();
				assertEquals(i, day.currentPlayer);
				if (day.marbleNumber >= marbles) {
					break;
				}
			}
		}
		day.logCircle();
		assertEquals(32, day.scores[3]);
		assertEquals(32, day.lastMarbleWorth);
		assertEquals(32L, day.highScore());
	}

	@Test
	public void testSample_whenPlayers9_andMarbles25_thenScore32() {
		final int players = 9;
		final int marbles = 25;
		day.init(players, marbles);
		day.play();
		assertEquals(marbles, day.marbleNumber);
		assertEquals(32L, day.highScore());
	}

	@Test
	public void testSample_whenPlayers10_andMarbles1618_thenScore8317() {
		final int players = 10;
		final int marbles = 1618;
		day.init(players, marbles);
		day.play();
		assertEquals(marbles, day.marbleNumber);
		assertEquals(8317L, day.highScore());
	}

	@Test
	public void testSample_whenPlayers13_andMarbles7999_thenScore146373() {
		final int players = 13;
		final int marbles = 7999;
		day.init(players, marbles);
		day.play();
		assertEquals(marbles, day.marbleNumber);
		assertEquals(146373L, day.highScore());
	}

	@Test
	public void testSample_whenPlayers17_andMarbles1104_thenScore2764() {
		final int players = 17;
		final int marbles = 1104;
		day.init(players, marbles);
		day.play();
		assertEquals(marbles, day.marbleNumber);
		assertEquals(2764L, day.highScore());
	}

	@Test
	public void testSample_whenPlayers21_andMarbles6111_thenScore54718() {
		final int players = 21;
		final int marbles = 6111;
		day.init(players, marbles);
		day.play();
		assertEquals(marbles, day.marbleNumber);
		assertEquals(54718L, day.highScore());
	}

	@Test
	public void testSample_whenPlayers30_andMarbles5807_thenScore37305() {
		final int players = 30;
		final int marbles = 5807;
		day.init(players, marbles);
		day.play();
		assertEquals(marbles, day.marbleNumber);
		assertEquals(37305L, day.highScore());
	}

	@Override
	@Test
	public void testPart2() {
		assertEquals(Long.valueOf(41642400L), day.part2());
	}

}
