package com.rykerstudios.advent.year2019;

import com.rykerstudios.advent.Day;

public class Day4 extends Day<Integer> {

	public Day4() {
		super(2814, 1234);
	}

	// --- Day 4: Secure Container ---

	// You arrive at the Venus fuel depot only to discover it's protected
	// by a password. The Elves had written the password on a sticky note,
	// but someone threw it out.

	// However, they do remember a few key facts about the password:

	// It is a six-digit number.
	// The value is within the range given in your puzzle input.
	// Two adjacent digits are the same (like 22 in 122345).
	// Going from left to right, the digits never decrease;
	// they only ever increase or stay the same (like 111123 or 135679).
	// Other than the range rule, the following are true:

	// 111111 meets these criteria (double 11, never decreases).
	// 223450 does not meet these criteria (decreasing pair of digits 50).
	// 123789 does not meet these criteria (no double).

	// How many different passwords within the range given
	// in your puzzle input meet these criteria?

	protected boolean check(final String password) {
		final char[] digits = password.toCharArray();
		boolean doubleDigits = false;
		for (int i = 0; i < 5; i++) {
			if (digits[i] == digits[i + 1]) {
				doubleDigits = true;
			}
			if (digits[i] > digits[i + 1]) {
				return false;
			}
		}
		if (!doubleDigits) {
			return false;
		}
		return true;
	}

	protected boolean check2(final String password) {
		final char[] digits = password.toCharArray();
		boolean doubleDigits = false;
		for (int i = 0; i < 5; i++) {
			if (digits[i] == digits[i + 1]) {
				if (i == 0) {
					if (digits[i] != digits[i + 2]) {
						doubleDigits = true;
					}
				} else if (i == 4) {
					if (digits[i] != digits[i - 1]) {
						doubleDigits = true;
					}
				} else {
					if (digits[i] != digits[i + 2] && digits[i] != digits[i - 1]) {
						doubleDigits = true;
					}
				}
			}
			if (digits[i] > digits[i + 1]) {
				return false;
			}
		}
		if (!doubleDigits) {
			return false;
		}
		return true;
	}

	@Override
	public Integer part1() {
		int count = 0;
		// 109165-576723
		for (int i = 109165; i <= 576723; i++) {
			final String password = Integer.toString(i);
			if (check(password)) {
				count++;
			}
		}
		return count;
	}

	// --- Part Two ---

	// An Elf just remembered one more important detail:
	// the two adjacent matching digits are not part of a larger group
	// of matching digits.

	// Given this additional criterion, but still ignoring the range rule,
	// the following are now true:

	// 112233 meets these criteria because the digits never decrease
	// and all repeated digits are exactly two digits long.
	// 123444 no longer meets the criteria
	// (the repeated 44 is part of a larger group of 444).
	// 111122 meets the criteria (even though 1 is repeated more than twice,
	// it still contains a double 22).

	// How many different passwords within the range given
	// in your puzzle input meet all of the criteria?

	@Override
	public Integer part2() {
		int count = 0;
		// 109165-576723
		for (int i = 109165; i <= 576723; i++) {
			final String password = Integer.toString(i);
			if (check2(password)) {
				count++;
			}
		}
		return count;
	}

}
