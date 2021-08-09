package com.rykerstudios.advent.year2020;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;
import com.rykerstudios.advent.year2020.Day22.CrabCombat;
import com.rykerstudios.advent.year2020.Day22.RecursiveCrabCombat;

@RunWith(JUnitPlatform.class)
public class TestDay22 extends TestDay<Day22> {

	@Override
	public Day22 createDay() {
		return new Day22();
	}

	@Test
	public void testPlayCard_example1Turn1() {
		final Queue<Integer> player1 = new ArrayDeque<>(Arrays.asList(new Integer[] { 9, 2, 6, 3, 1 }));
		final Queue<Integer> player2 = new ArrayDeque<>(Arrays.asList(new Integer[] { 5, 8, 4, 7, 10 }));
		final CrabCombat combat = new CrabCombat(new String[] {});
		combat.playCard(player1, player2);
		Assertions.assertEquals(6, player1.size());
		Assertions.assertEquals(4, player2.size());
		Assertions.assertTrue(player1.contains(9));
		Assertions.assertTrue(player1.contains(5));
	}

	@Test
	public void testPlayCards_part1_example1() {
		final Queue<Integer> player1 = new ArrayDeque<>(Arrays.asList(new Integer[] { 9, 2, 6, 3, 1 }));
		final Queue<Integer> player2 = new ArrayDeque<>(Arrays.asList(new Integer[] { 5, 8, 4, 7, 10 }));
		final CrabCombat combat = new CrabCombat(new String[] {});
		final boolean player1wins = combat.playGame(player1, player2);
		final int score = combat.getScore(player1wins ? player1 : player2);
		Assertions.assertEquals(score, 306);
	}

	@Test
	public void testPlayCards_part2_example1() {
		final Queue<Integer> player1 = new ArrayDeque<>(Arrays.asList(new Integer[] { 9, 2, 6, 3, 1 }));
		final Queue<Integer> player2 = new ArrayDeque<>(Arrays.asList(new Integer[] { 5, 8, 4, 7, 10 }));
		final RecursiveCrabCombat combat = new RecursiveCrabCombat(new String[] {});
		final boolean player1wins = combat.playGame(player1, player2);
		final int score = combat.getScore(player1wins ? player1 : player2);
		Assertions.assertEquals(score, 291);
	}

}
