package com.rykerstudios.advent.year2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rykerstudios.advent.Day;

public class Day2 extends Day<Integer> {

	public Day2() {
		super(5704, 0);
	}

	/*
	 * --- Day 2: Inventory Management System ---
	 * 
	 * You stop falling through time, catch your breath, and check the screen on the
	 * device.
	 * "Destination reached. Current Year: 1518. Current Location: North Pole Utility Closet 83N10."
	 * You made it! Now, to find those anomalies.
	 * 
	 * Outside the utility closet, you hear footsteps and a voice.
	 * "...I'm not sure either. But now that so many people have chimneys, maybe he could sneak in that way?"
	 * Another voice responds,
	 * "Actually, we've been working on a new kind of suit that would let him fit through tight spaces like that. But, I heard that a few days ago, they lost the prototype fabric, the design plans, everything! Nobody on the team can even seem to remember important details of the project!"
	 * 
	 * "Wouldn't they have had enough fabric to fill several boxes in the warehouse? They'd be stored together, so the box IDs should be similar. Too bad it would take forever to search the warehouse for two similar box IDs..."
	 * They walk too far away to hear any more.
	 * 
	 * Late at night, you sneak to the warehouse - who knows what kinds of paradoxes
	 * you could cause if you were discovered - and use your fancy wrist device to
	 * quickly scan every box and produce a list of the likely candidates (your
	 * puzzle input).
	 * 
	 * To make sure you didn't miss any, you scan the likely candidate boxes again,
	 * counting the number that have an ID containing exactly two of any letter and
	 * then separately counting those with exactly three of any letter. You can
	 * multiply those two counts together to get a rudimentary checksum and compare
	 * it to what your device predicts.
	 * 
	 * For example, if you see the following box IDs:
	 * 
	 * abcdef contains no letters that appear exactly two or three times. bababc
	 * contains two a and three b, so it counts for both. abbcde contains two b, but
	 * no letter appears exactly three times. abcccd contains three c, but no letter
	 * appears exactly two times. aabcdd contains two a and two d, but it only
	 * counts once. abcdee contains two e. ababab contains three a and three b, but
	 * it only counts once. Of these box IDs, four of them contain a letter which
	 * appears exactly twice, and three of them contain a letter which appears
	 * exactly three times. Multiplying these together produces a checksum of 4 * 3
	 * = 12.
	 * 
	 * What is the checksum for your list of box IDs?
	 */
	@Override
	public Integer part1() {
		final String[] lines = getLines();
		final List<String> twos = getListOf(2, lines);
		final List<String> threes = getListOf(3, lines);
		return twos.size() * threes.size();
	}

	private List<String> getListOf(final int targetCount, final String... lines) {
		final List<String> results = new ArrayList<String>();
		for (final String line : lines) {
			final Map<Character, Integer> counts = new HashMap<Character, Integer>();
			for (final char c : line.toCharArray()) {
				if (counts.containsKey(c)) {
					counts.put(c, counts.get(c) + 1);
				} else {
					counts.put(c, 1);
				}
			}
			System.out.println(line + ": " + counts.values());
			if (counts.values().contains(targetCount)) {
				results.add(line);
			}
		}
		return results;
	}

	/*
	 * --- Part Two ---
	 *
	 * Confident that your list of box IDs is complete, you're ready to find the
	 * boxes full of prototype fabric.
	 * 
	 * The boxes will have IDs which differ by exactly one character at the same
	 * position in both strings. For example, given the following box IDs:
	 * 
	 * abcde fghij klmno pqrst fguij axcye wvxyz The IDs abcde and axcye are close,
	 * but they differ by two characters (the second and fourth). However, the IDs
	 * fghij and fguij differ by exactly one character, the third (h and u). Those
	 * must be the correct boxes.
	 * 
	 * What letters are common between the two correct box IDs? (In the example
	 * above, this is found by removing the differing character from either ID,
	 * producing fgij.)
	 */
	@Override
	public Integer part2() {
		final List<String> lines = Arrays.asList(getLines());
		Collections.sort(lines);
		final List<String> combinations = new ArrayList<String>();
		for (final String line : lines) {
			for (int i = 0; i < line.length(); i++) {
				final String s = line.substring(0, i) + "_" + line.substring(i + 1);
				if (combinations.contains(s)) {
					System.out.println(i + ":" + s);
					System.out.println(i + ":" + line);
				}
				combinations.add(s);
			}
		}
		return 0;
	}

}
