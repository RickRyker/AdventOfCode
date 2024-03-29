package com.rykerstudios.advent.year2015;

import java.util.HashSet;
import java.util.Set;

import com.rykerstudios.advent.Day;

public class Day11 extends Day<String> {

	private static final String ALLOWED = "abcdefghjkmnpqrstuvwxyz";
	private static final String INPUT = "hepxcrrq";
	private static final String EXPECTED1 = "hepxxyzz";
	private static final String EXPECTED2 = "";

	public Day11() {
		super(EXPECTED1, EXPECTED2);
	}
	protected boolean allCharactersAllowed(final String s) {
		final char[] a = s.toCharArray();
		for (char c : a) {
			if (!ALLOWED.contains("" + c)) {
				return false;
			}
		}
		return true;
	}

	protected Set<String> getPairs(final String s) {
		final Set<String> set = new HashSet<String>();
		String previousLetter = "";
		for (int i = 0; i < s.length(); i++) {
			final String letter = s.substring(i, i + 1);
			if (previousLetter.equals(letter)) {
				set.add(letter);
			}
			previousLetter = letter;
		}
		return set;
	}

	protected boolean hasStraight(final String s) {
		final char[] a = s.toCharArray();
		if (a.length < 3) {
			return false;
		}
		for (int i = 0; i < a.length - 2; i++) {
			if (a[i + 1] - a[i] == 1 && a[i + 2] - a[i + 1] == 1) {
				return true;
			}
		}
		return false;
	}

	protected boolean hasTwoDoubledCharacters(final String s) {
		if (s.length() < 4) {
			return false;
		}
		return getPairs(s).size() > 1;
	}

	protected String next(final String s) {
		char[] a = s.toCharArray();
		int p = a.length - 1;
		while (p >= 0 && a[p] == 'z') {
			a[p] = 'a';
			p--;
		}
		if (p >= 0) {
			char c = a[p];
			c++;
			a[p] = c;
		}
		final String result = new String(a);
		// System.out.println(result);
		return result;
	}

	/*
	 * --- Day 11: Corporate Policy ---
	 * 
	 * Santa's previous password expired, and he needs help choosing a new one.
	 * 
	 * To help him remember his new password after the old one expires, Santa has
	 * devised a method of coming up with a password based on the previous one.
	 * Corporate policy dictates that passwords must be exactly eight lowercase
	 * letters (for security reasons), so he finds his new password by incrementing
	 * his old password string repeatedly until it is valid.
	 * 
	 * Incrementing is just like counting with numbers: xx, xy, xz, ya, yb, and so
	 * on. Increase the rightmost letter one step; if it was z, it wraps around to
	 * a, and repeat with the next letter to the left until one doesn't wrap around.
	 * 
	 * Unfortunately for Santa, a new Security-Elf recently started, and he has
	 * imposed some additional password requirements:
	 * 
	 * Passwords must include one increasing straight of at least three letters,
	 * like abc, bcd, cde, and so on, up to xyz. They cannot skip letters; abd
	 * doesn't count. Passwords may not contain the letters i, o, or l, as these
	 * letters can be mistaken for other characters and are therefore confusing.
	 * Passwords must contain at least two different, non-overlapping pairs of
	 * letters, like aa, bb, or zz. For example:
	 * 
	 * hijklmmn meets the first requirement (because it contains the straight hij)
	 * but fails the second requirement requirement (because it contains i and l).
	 * abbceffg meets the third requirement (because it repeats bb and ff) but fails
	 * the first requirement. abbcegjk fails the third requirement, because it only
	 * has one double letter (bb). The next password after abcdefgh is abcdffaa. The
	 * next password after ghijklmn is ghjaabcc, because you eventually skip all the
	 * passwords that start with ghi..., since i is not allowed. Given Santa's
	 * current password (your puzzle input), what should his next password be?
	 * 
	 * Your puzzle input is hepxcrrq.
	 */
	@Override
	public String part1() {
		String result = next(INPUT);
		while (!valid(result)) {
			result = next(result);
		}
		System.out.println(result);
		return result;
	}

	/*
	 * --- Part Two ---
	 * 
	 * Santa's password expired again. What's the next one?
	 */
	@Override
	public String part2() {
		String result = next(EXPECTED1);
		while (!valid(result)) {
			result = next(result);
		}
		System.out.println(result);
		return result;
	}

	protected boolean valid(final String s) {
		return allCharactersAllowed(s) && hasStraight(s) && hasTwoDoubledCharacters(s);
	}

}
