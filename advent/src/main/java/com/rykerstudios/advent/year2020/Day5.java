package com.rykerstudios.advent.year2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rykerstudios.advent.Day;

public class Day5 extends Day<Integer> {

	public Day5() {
		super(871, 640);
	}

	/**
	 * --- Day 5: Binary Boarding ---
	 * 
	 * You board your plane only to discover a new problem: you dropped your
	 * boarding pass! You aren't sure which seat is yours, and all of the flight
	 * attendants are busy with the flood of people that suddenly made it through
	 * passport control.
	 * 
	 * You write a quick program to use your phone's camera to scan all of the
	 * nearby boarding passes (your puzzle input); perhaps you can find your seat
	 * through process of elimination.
	 * 
	 * Instead of zones or groups, this airline uses binary space partitioning to
	 * seat people. A seat might be specified like FBFBBFFRLR, where F means
	 * "front", B means "back", L means "left", and R means "right".
	 * 
	 * The first 7 characters will either be F or B; these specify exactly one of
	 * the 128 rows on the plane (numbered 0 through 127). Each letter tells you
	 * which half of a region the given seat is in. Start with the whole list of
	 * rows; the first letter indicates whether the seat is in the front (0 through
	 * 63) or the back (64 through 127). The next letter indicates which half of
	 * that region the seat is in, and so on until you're left with exactly one row.
	 * 
	 * For example, consider just the first seven characters of FBFBBFFRLR:
	 * 
	 * Start by considering the whole range, rows 0 through 127. <br/>
	 * F means to take the lower half, keeping rows 0 through 63. <br/>
	 * B means to take the upper half, keeping rows 32 through 63. <br/>
	 * F means to take the lower half, keeping rows 32 through 47. <br/>
	 * B means to take the upper half, keeping rows 40 through 47. <br/>
	 * B keeps rows 44 through 47. <br/>
	 * F keeps rows 44 through 45. <br/>
	 * The final F keeps the lower of the two, row 44. <br/>
	 * 
	 * The last three characters will be either L or R; these specify exactly one of
	 * the 8 columns of seats on the plane (numbered 0 through 7). The same process
	 * as above proceeds again, this time with only three steps. L means to keep the
	 * lower half, while R means to keep the upper half.
	 * 
	 * For example, consider just the last 3 characters of FBFBBFFRLR:
	 * 
	 * Start by considering the whole range, columns 0 through 7. <br/>
	 * R means to take the upper half, keeping columns 4 through 7. <br/>
	 * L means to take the lower half, keeping columns 4 through 5. <br/>
	 * The final R keeps the upper of the two, column 5. <br/>
	 * 
	 * So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.
	 * 
	 * Every seat also has a unique seat ID: multiply the row by 8, then add the
	 * column. In this example, the seat has ID 44 * 8 + 5 = 357.
	 * 
	 * Here are some other boarding passes:
	 * 
	 * BFFFBBFRRR: row 70, column 7, seat ID 567. <br/>
	 * FFFBBBFRRR: row 14, column 7, seat ID 119. <br/>
	 * BBFFBBFRLL: row 102, column 4, seat ID 820. <br/>
	 * 
	 * As a sanity check, look through your list of boarding passes. What is the
	 * highest seat ID on a boarding pass?
	 */
	@Override
	public Integer part1() {
		int highestSeat = 0;
		final List<BoardingPass> passes = BoardingPass.parse(getLines());
		for (final BoardingPass pass : passes) {
			if (highestSeat < pass.getSeatId()) {
				highestSeat = pass.getSeatId();
			}
		}
		return highestSeat;
	}

	/**
	 * --- Part Two ---
	 * 
	 * Ding! The "fasten seat belt" signs have turned on. Time to find your seat.
	 * 
	 * It's a completely full flight, so your seat should be the only missing
	 * boarding pass in your list. However, there's a catch: some of the seats at
	 * the very front and back of the plane don't exist on this aircraft, so they'll
	 * be missing from your list as well.
	 * 
	 * Your seat wasn't at the very front or back, though; the seats with IDs +1 and
	 * -1 from yours will be in your list.
	 * 
	 * What is the ID of your seat?
	 */
	@Override
	public Integer part2() {
		final List<Integer> seatIds = new ArrayList<>();
		final List<BoardingPass> passes = BoardingPass.parse(getLines());
		for (final BoardingPass pass : passes) {
			seatIds.add(pass.getSeatId());
		}
		Collections.sort(seatIds);
		System.out.println(seatIds);
		for (int i = seatIds.get(0); i < seatIds.get(seatIds.size() - 1); i++) {
			if (seatIds.get(i + 1) - seatIds.get(i) != 1) {
				return seatIds.get(i) + 1;
			}
		}
		return 0;
	}

}
