package com.rykerstudios.advent.year2015;

import java.util.ArrayList;
import java.util.List;

import com.rykerstudios.advent.Day;

public class Day5 extends Day<Integer> {

	public Day5() {
		super(258, 53);
	}

	protected int getVowelCount(final String s) {
		int vowelCount = 0;
		for (int i = 0; i < s.length(); i++) {
			final String letter = s.substring(i, i + 1);
			System.out.println(letter);
			if ("aeiou".contains(letter)) {
				vowelCount++;
			}
		}
		return vowelCount;
	}

	protected boolean hasDoubledLetter(final String s) {
		String lastLetter = "";
		for (int i = 0; i < s.length(); i++) {
			final String letter = s.substring(i, i + 1);
			System.out.println(letter);
			if (lastLetter.equals(letter)) {
				return true;
			}
			lastLetter = letter;
		}
		return false;
	}

	protected boolean hasForbiddenLetters(final String s) {
		if (s.contains("ab")) {
			return true;
		}
		if (s.contains("cd")) {
			return true;
		}
		if (s.contains("pq")) {
			return true;
		}
		if (s.contains("xy")) {
			return true;
		}
		return false;
	}

	protected boolean isNice(final String s) {
		return getVowelCount(s) >= 3 && hasDoubledLetter(s) && !hasForbiddenLetters(s);
	}

	private List<String> getWordsWithPatternXYX(final String[] words) {
		final List<String> list = new ArrayList<String>();
		for (final String s : words) {
			for (int i = 0; i < s.length() - 2; i++) {
				final String a = s.substring(i, i + 1);
				final String b = s.substring(i + 2, i + 3);
				if (a.equals(b)) {
					list.add(s);
					break;
				}
			}
		}
		return list;
	}

	/*
	 * It contains a pair of any two letters that appears at least twice in the
	 * string without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like
	 * aaa (aa, but it overlaps). It contains at least one letter which repeats with
	 * exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa.
	 */
	protected boolean isNice2(final String s) {
		for (int i = 0; i < s.length() - 1; i++) {
			final String pair = s.substring(i, i + 2);
			final String rest = s.substring(i + 2);
			if (rest.contains(pair)) {
				System.out.println(s + " is Nice!");
				return true;
			}
		}
		System.out.println(s + " is NOT nice.");
		return false;
	}

	/*
	 * --- Day 5: Doesn't He Have Intern-Elves For This? ---
	 * 
	 * Santa needs help figuring out which strings in his text file are naughty or
	 * nice.
	 * 
	 * A nice string is one with all of the following properties:
	 * 
	 * It contains at least three vowels (aeiou only), like aei, xazegov, or
	 * aeiouaeiouaeiou. It contains at least one letter that appears twice in a row,
	 * like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd). It does not contain
	 * the strings ab, cd, pq, or xy, even if they are part of one of the other
	 * requirements. For example:
	 * 
	 * ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...),
	 * a double letter (...dd...), and none of the disallowed substrings. aaa is
	 * nice because it has at least three vowels and a double letter, even though
	 * the letters used by different rules overlap. jchzalrnumimnmhp is naughty
	 * because it has no double letter. haegwjzuvuyypxyu is naughty because it
	 * contains the string xy. dvszwmarrgswjxmb is naughty because it contains only
	 * one vowel. How many strings are nice?
	 */
	@Override
	public Integer part1() {
		int count = 0;
		final String[] lines = getLines();
		for (final String line : lines) {
			if (isNice(line)) {
				count++;
			}
		}
		return count;
	}

	/*
	 * --- Part Two ---
	 * 
	 * Realizing the error of his ways, Santa has switched to a better model of
	 * determining whether a string is naughty or nice. None of the old rules apply,
	 * as they are all clearly ridiculous.
	 * 
	 * Now, a nice string is one with all of the following properties:
	 * 
	 * It contains a pair of any two letters that appears at least twice in the
	 * string without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like
	 * aaa (aa, but it overlaps). It contains at least one letter which repeats with
	 * exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa. For
	 * example:
	 * 
	 * qjhvhtzxzqqjkmpb is nice because is has a pair that appears twice (qj) and a
	 * letter that repeats with exactly one letter between them (zxz). xxyxx is nice
	 * because it has a pair that appears twice and a letter that repeats with one
	 * between, even though the letters used by each rule overlap. uurcxstgmygtbstg
	 * is naughty because it has a pair (tg) but no repeat with a single letter
	 * between them. ieodomkazucvgmuy is naughty because it has a repeating letter
	 * with one between (odo), but no pair that appears twice.
	 * 
	 * How many strings are nice under these new rules?
	 */
	@Override
	public Integer part2() {
		int count = 0;
		final List<String> words = getWordsWithPatternXYX(getLines());
		System.out.println(words.size() + " words have a letter pattern ...xyx...");
		for (final String word : words) {
			if (isNice2(word)) {
				count++;
			}
		}
		System.out.println(count + " words with that pattern have a pair repeated later in the string.");
		return count;
	}

}
