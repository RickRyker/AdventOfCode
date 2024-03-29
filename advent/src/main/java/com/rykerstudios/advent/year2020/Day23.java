package com.rykerstudios.advent.year2020;

import com.rykerstudios.advent.Day;

public class Day23 extends Day<Integer> {

	public Day23() {
		super(0, 0);
	}

	/**
	 * --- Day 23: Crab Cups ---
	 * 
	 * The small crab challenges you to a game! The crab is going to mix up some
	 * cups, and you have to predict where they'll end up.
	 * 
	 * The cups will be arranged in a circle and labeled clockwise (your puzzle
	 * input). For example, if your labeling were 32415, there would be five cups in
	 * the circle; going clockwise around the circle from the first cup, the cups
	 * would be labeled 3, 2, 4, 1, 5, and then back to 3 again.
	 * 
	 * Before the crab starts, it will designate the first cup in your list as the
	 * current cup. The crab is then going to do 100 moves.
	 * 
	 * Each move, the crab does the following actions:
	 * 
	 * The crab picks up the three cups that are immediately clockwise of the
	 * current cup. They are removed from the circle; cup spacing is adjusted as
	 * necessary to maintain the circle. <br/>
	 * The crab selects a destination cup: the cup with a label equal to the current
	 * cup's label minus one. If this would select one of the cups that was just
	 * picked up, the crab will keep subtracting one until it finds a cup that
	 * wasn't just picked up. If at any point in this process the value goes below
	 * the lowest value on any cup's label, it wraps around to the highest value on
	 * any cup's label instead. <br/>
	 * The crab places the cups it just picked up so that they are immediately
	 * clockwise of the destination cup. They keep the same order as when they were
	 * picked up. <br/>
	 * The crab selects a new current cup: the cup which is immediately clockwise of
	 * the current cup. <br/>
	 * 
	 * For example, suppose your cup labeling were 389125467. If the crab were to do
	 * merely 10 moves, the following changes would occur: <br/>
	 * 
	 * -- move 1 -- <br/>
	 * cups: (3) 8 9 1 2 5 4 6 7 <br/>
	 * pick up: 8, 9, 1 <br/>
	 * destination: 2 <br/>
	 * 
	 * -- move 2 -- <br/>
	 * cups: 3 (2) 8 9 1 5 4 6 7 <br/>
	 * pick up: 8, 9, 1 <br/>
	 * destination: 7 <br/>
	 * 
	 * -- move 3 -- <br/>
	 * cups: 3 2 (5) 4 6 7 8 9 1 <br/>
	 * pick up: 4, 6, 7 <br/>
	 * destination: 3 <br/>
	 * 
	 * -- move 4 -- <br/>
	 * cups: 7 2 5 (8) 9 1 3 4 6 <br/>
	 * pick up: 9, 1, 3 <br/>
	 * destination: 7 <br/>
	 * 
	 * -- move 5 -- <br/>
	 * cups: 3 2 5 8 (4) 6 7 9 1 <br/>
	 * pick up: 6, 7, 9 <br/>
	 * destination: 3 <br/>
	 * 
	 * -- move 6 -- <br/>
	 * cups: 9 2 5 8 4 (1) 3 6 7 <br/>
	 * pick up: 3, 6, 7 <br/>
	 * destination: 9 <br/>
	 * 
	 * -- move 7 -- <br/>
	 * cups: 7 2 5 8 4 1 (9) 3 6 <br/>
	 * pick up: 3, 6, 7 <br/>
	 * destination: 8 <br/>
	 * 
	 * -- move 8 -- <br/>
	 * cups: 8 3 6 7 4 1 9 (2) 5 <br/>
	 * pick up: 5, 8, 3 <br/>
	 * destination: 1 <br/>
	 * 
	 * -- move 9 -- <br/>
	 * cups: 7 4 1 5 8 3 9 2 (6) <br/>
	 * pick up: 7, 4, 1 <br/>
	 * destination: 5 <br/>
	 * 
	 * -- move 10 -- <br/>
	 * cups: (5) 7 4 1 8 3 9 2 6 <br/>
	 * pick up: 7, 4, 1 <br/>
	 * destination: 3 <br/>
	 * 
	 * -- final -- <br/>
	 * cups: 5 (8) 3 7 4 1 9 2 6 <br/>
	 * 
	 * In the above example, the cups' values are the labels as they appear moving
	 * clockwise around the circle; the current cup is marked with ( ).
	 * 
	 * After the crab is done, what order will the cups be in? Starting after the
	 * cup labeled 1, collect the other cups' labels clockwise into a single string
	 * with no extra characters; each number except 1 should appear exactly once. In
	 * the above example, after 10 moves, the cups clockwise from 1 are labeled 9,
	 * 2, 6, 5, and so on, producing 92658374. If the crab were to complete all 100
	 * moves, the order after cup 1 would be 67384529.
	 * 
	 * Using your labeling, simulate 100 moves. What are the labels on the cups
	 * after cup 1?
	 */
	@Override
	public Integer part1() {
		// puzzle input is 186524973
		int count = 0;
		return count;
	}

	@Override
	public Integer part2() {
		int count = 0;
		return count;
	}

	public static class CrabCups {

		int count;
		int[] currentCups;

		public CrabCups(final String startingCups) {
			final String[] cups = startingCups.split("|");
			this.count = cups.length;
			this.currentCups = new int[count];
			for (int i = 0; i < count; i++) {
				this.currentCups[i] = Integer.parseInt(cups[i]);
			}
		}

		public int[] move(int[] cups) {
			final int[] result = new int[this.count];
			// Each move, the crab does the following actions:
			// The crab picks up the three cups that are immediately clockwise of the
			// current cup.
			// They are removed from the circle; cup spacing is adjusted as necessary to
			// maintain the circle.
			// The crab selects a destination cup: the cup with a label equal to the current
			// cup's label minus one.
			// If this would select one of the cups that was just picked up,
			// the crab will keep subtracting one until it finds a cup that wasn't just
			// picked up.
			// If at any point in this process the value goes below the lowest value on any
			// cup's label,
			// it wraps around to the highest value on any cup's label instead.
			// The crab places the cups it just picked up so that they are immediately
			// clockwise of the destination cup.
			// They keep the same order as when they were picked up.
			// The crab selects a new current cup: the cup which is immediately clockwise of
			// the current cup.

			// Assumptions: the current cup will always be in cups[0]
			// The removed cups will always be in cups[1] to cups[3]
			// Remove them and move the remaining cups forward into their places
			// Once the destination cup is selected,
			// select aside the new current cup from the cup to its right
			// and rotate all cups until the new current cup is in cup[0] again

			return result;
		}

	}

}
