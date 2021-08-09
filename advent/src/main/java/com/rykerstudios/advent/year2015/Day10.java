package com.rykerstudios.advent.year2015;

import com.rykerstudios.advent.Day;

public class Day10 extends Day<Integer> {

	public Day10() {
		super(360154, 0);
	}

	/*
	 * --- Day 10: Elves Look, Elves Say ---
	 * 
	 * Today, the Elves are playing a game called look-and-say. They take turns
	 * making sequences by reading aloud the previous sequence and using that
	 * reading as the next sequence. For example, 211 is read as
	 * "one two, two ones", which becomes 1221 (1 2, 2 1s).
	 * 
	 * Look-and-say sequences are generated iteratively, using the previous value as
	 * input for the next step. For each step, take the previous value, and replace
	 * each run of digits (like 111) with the number of digits (3) followed by the
	 * digit itself (1).
	 * 
	 * For example:
	 * 
	 * 1 becomes 11 (1 copy of digit 1). 11 becomes 21 (2 copies of digit 1). 21
	 * becomes 1211 (one 2 followed by one 1). 1211 becomes 111221 (one 1, one 2,
	 * and two 1s). 111221 becomes 312211 (three 1s, two 2s, and one 1). Starting
	 * with the digits in your puzzle input, apply this process 40 times. What is
	 * the length of the result?
	 * 
	 * Your puzzle input is 1113122113.
	 */
	@Override
	public Integer part1() {
		String number = "1113122113";
		for (int i = 0; i < 40; i++) {
			number = lookAndSay(number);
		}
		return number.length();
	}

	protected String lookAndSay(final String number) {
		String result = "";
		String numeral = "";
		int count = 0;
		for (int i = 0; i < number.length(); i++) {
			final String digit = number.substring(i, i + 1);
			if (digit.equals(numeral)) {
				count++;
			} else {
				if (count > 0) {
					result += count + numeral;
				}
				numeral = digit;
				count = 1;
			}
		}
		if (count > 0) {
			result += count + numeral;
		}
		System.out.println(result);
		return result;
	}

	/*
	 * --- Part Two ---
	 * 
	 * Neat, right? You might also enjoy hearing John Conway talking about this
	 * sequence (that's Conway of Conway's Game of Life fame).
	 * 
	 * Now, starting again with the digits in your puzzle input, apply this process
	 * 50 times. What is the length of the new result?
	 * 
	 * Your puzzle input is still 1113122113.
	 */
	@Override
	public Integer part2() {
		String number = "1113122113";
		for (int i = 0; i < 50; i++) {
			number = lookAndSay(number);
		}
		return number.length();
	}

}
