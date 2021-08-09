package com.rykerstudios.advent.year2020;

import com.rykerstudios.advent.Day;

public class Day2 extends Day<Integer> {

	public Day2() {
		super(414, 0);
	}

	/**
	 * --- Day 2: Password Philosophy ---
	 * 
	 * Your flight departs in a few days from the coastal airport; the easiest way
	 * down to the coast from here is via toboggan.
	 * 
	 * The shopkeeper at the North Pole Toboggan Rental Shop is having a bad day.
	 * "Something's wrong with our computers; we can't log in!" You ask if you can
	 * take a look.
	 * 
	 * Their password database seems to be a little corrupted: some of the passwords
	 * wouldn't have been allowed by the Official Toboggan Corporate Policy that was
	 * in effect when they were chosen.
	 * 
	 * To try to debug the problem, they have created a list (your puzzle input) of
	 * passwords (according to the corrupted database) and the corporate policy when
	 * that password was set.
	 * 
	 * For example, suppose you have the following list:
	 * 
	 * 1-3 a: abcde <br/>
	 * 1-3 b: cdefg <br/>
	 * 2-9 c: ccccccccc <br/>
	 * 
	 * Each line gives the password policy and then the password. The password
	 * policy indicates the lowest and highest number of times a given letter must
	 * appear for the password to be valid. For example, 1-3 a means that the
	 * password must contain a at least 1 time and at most 3 times.
	 * 
	 * In the above example, 2 passwords are valid. The middle password, cdefg, is
	 * not; it contains no instances of b, but needs at least 1. The first and third
	 * passwords are valid: they contain one a or nine c, both within the limits of
	 * their respective policies.
	 * 
	 * How many passwords are valid according to their policies?
	 */
	@Override
	public Integer part1() {
		int count = 0;
		final String[] lines = getLines();
		for (final String line : lines) {
			final Password password = new Password(line);
			if (isValid(password)) {
				count++;
			}
		}
		return count;
	}

	static class Password {
		int minLetterCount;
		int maxLetterCount;
		char ruleLetter;
		String password;

		public Password(final String line) {
			final String[] parts = line.split(" ");
			final String[] range = parts[0].split("-");
			minLetterCount = Integer.parseInt(range[0]);
			maxLetterCount = Integer.parseInt(range[1]);
			ruleLetter = parts[1].replace(":", "").charAt(0);
			password = parts[2];
		}
	}

	int letterCount(final String password, final char letter) {
		int count = 0;
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) == letter) {
				count++;
			}
		}
		return count;
	}

	boolean isValid(final Password password) {
		final int count = letterCount(password.password, password.ruleLetter);
		if (password.minLetterCount <= count && count <= password.maxLetterCount) {
			return true;
		}
		return false;
	}

	/**
	 * --- Part Two ---
	 * 
	 * While it appears you validated the passwords correctly, they don't seem to be
	 * what the Official Toboggan Corporate Authentication System is expecting.
	 * 
	 * The shopkeeper suddenly realizes that he just accidentally explained the
	 * password policy rules from his old job at the sled rental place down the
	 * street! The Official Toboggan Corporate Policy actually works a little
	 * differently.
	 * 
	 * Each policy actually describes two positions in the password, where 1 means
	 * the first character, 2 means the second character, and so on. (Be careful;
	 * Toboggan Corporate Policies have no concept of "index zero"!) Exactly one of
	 * these positions must contain the given letter. Other occurrences of the
	 * letter are irrelevant for the purposes of policy enforcement.
	 * 
	 * Given the same example list from above: <br/>
	 * 
	 * 1-3 a: abcde is valid: position 1 contains a and position 3 does not. <br/>
	 * 1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b. <br/>
	 * 2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c. <br/>
	 * How many passwords are valid according to the new interpretation of the
	 * policies? <br/>
	 */
	@Override
	public Integer part2() {
		int count = 0;
		final String[] lines = getLines();
		for (final String line : lines) {
			final Password password = new Password(line);
			if (isValid2(password)) {
				count++;
			}
		}
		return count;
	}

	boolean isValid2(final Password password) {
		final char letter1 = password.password.charAt(password.minLetterCount - 1);
		final char letter2 = password.password.charAt(password.maxLetterCount - 1);
		if (letter1 == password.ruleLetter && letter2 != password.ruleLetter) {
			return true;
		}
		if (letter2 == password.ruleLetter && letter1 != password.ruleLetter) {
			return true;
		}
		return false;
	}

}
