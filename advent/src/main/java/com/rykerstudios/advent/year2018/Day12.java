package com.rykerstudios.advent.year2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rykerstudios.advent.Day;

public class Day12 extends Day<Long> {

	protected List<Map<Integer, Boolean>> generations = new ArrayList<Map<Integer, Boolean>>();

	protected String[] rules = null;

	public Day12() {
		super(2166L, 2100000000061L);
	}

	protected int getPotCount(final int t) {
		if (generations.get(t).isEmpty()) {
			grow(t);
		}
		final Map<Integer, Boolean> plants = generations.get(t);
		return getPotCount(plants);
	}

	protected int getPotCount(final Map<Integer, Boolean> plants) {
		int count = 0;
		for (final Map.Entry<Integer, Boolean> entry : plants.entrySet()) {
			if (entry.getValue()) {
				count++;
			}
		}
		return count;
	}

	protected long getSumPotNumbers(final int t) {
		if (generations.get(t).isEmpty()) {
			grow(t);
		}
		final Map<Integer, Boolean> plants = generations.get(t);
		return getSumPotNumbers(plants);
	}

	protected long getSumPotNumbers(final Map<Integer, Boolean> plants) {
		int count = 0;
		for (final Map.Entry<Integer, Boolean> entry : plants.entrySet()) {
			if (entry.getValue()) {
				count += entry.getKey();
			}
		}
		return count;
	}

	protected String get(final Map<Integer, Boolean> plants, final int pot) {
		final StringBuilder sb = new StringBuilder();
		sb.append(getPotString(plants.get(pot - 2)));
		sb.append(getPotString(plants.get(pot - 1)));
		sb.append(getPotString(plants.get(pot)));
		sb.append(getPotString(plants.get(pot + 1)));
		sb.append(getPotString(plants.get(pot + 2)));
		return sb.toString();
	}

	protected String getPotString(final Boolean filled) {
		return filled != null && filled ? "#" : ".";
	}

	protected boolean survives(final Map<Integer, Boolean> plants, final Integer pot) {
		final String s = get(plants, pot);
		// System.out.print(s + " => ");
		for (final String rule : rules) {
			if (rule.substring(0, 5).equals(s)) {
				final String result = rule.substring(9);
				// System.out.println(result);
				return "#".equals(result);
			}
		}
		// System.out.println("Should throw No Rule found exception.");
		// System.out.println(".");
		return false;
	}

	protected void grow(final int t) {
		if (t == 0) {
			return;
		}
		if (generations.size() < t + 1) {
			throw new IllegalStateException("Array generations not large enough.");
		}
		if (generations.get(t - 1).isEmpty()) {
			grow(t - 1);
		}
		final Map<Integer, Boolean> plants = generations.get(t);
		final Map<Integer, Boolean> prev = generations.get(t - 1);
		grow(plants, prev);
	}

	protected void grow(final Map<Integer, Boolean> plants, final Map<Integer, Boolean> prev) {
		final int firstPot = getFirstPotNumber(prev);
		final int lastPot = getLastPotNumber(prev);
		if (survives(prev, firstPot - 2)) {
			plants.put(firstPot - 2, true);
		}
		if (survives(prev, firstPot - 1)) {
			plants.put(firstPot - 1, true);
		}
		for (final Map.Entry<Integer, Boolean> entry : prev.entrySet()) {
			final Integer pot = entry.getKey();
			plants.put(pot, survives(prev, pot));
		}
		if (survives(prev, lastPot + 1)) {
			plants.put(lastPot + 1, true);
		}
		if (survives(prev, lastPot + 2)) {
			plants.put(lastPot + 2, true);
		}
	}

	protected int getLastPotNumber(final Map<Integer, Boolean> pots) {
		int result = 0;
		for (final Integer key : pots.keySet()) {
			if (result < key) {
				result = key;
			}
		}
		return result;
	}

	protected int getFirstPotNumber(final Map<Integer, Boolean> pots) {
		int result = 0;
		for (final Integer key : pots.keySet()) {
			if (result > key) {
				result = key;
			}
		}
		return result;
	}

	protected void init(final String[] input, final int genCount) {
		for (int i = 0; i <= genCount; i++) {
			generations.add(i, new HashMap<Integer, Boolean>());
		}
		initPlants(input[0].substring(input[0].indexOf(":") + 1).trim());
		initRules(input);
	}

	protected void initPlants(final String s) {
		if (generations.isEmpty()) {
			generations.add(0, new HashMap<Integer, Boolean>());
		}
		final Map<Integer, Boolean> plants = generations.get(0);
		for (int i = 0; i < s.length(); i++) {
			plants.put(i, "#".equals(s.substring(i, i + 1)));
		}
		System.out.println(plants);
	}

	protected void initRules(final String[] lines) {
		rules = new String[lines.length - 1];
		int r = 0;
		for (final String line : lines) {
			if (line.contains("=>")) {
				rules[r++] = line;
			}
		}
		// System.out.println(rules[rules.length - 1]);
	}

	// --- Day 12: Subterranean Sustainability ---
	//
	// The year 518 is significantly more underground than your history books
	// implied. Either that, or you've arrived in a vast cavern network under the
	// North Pole.
	//
	// After exploring a little, you discover a long tunnel that contains a row of
	// small pots as far as you can see to your left and right. A few of them
	// contain plants - someone is trying to grow things in these
	// geothermally-heated caves.
	//
	// The pots are numbered, with 0 in front of you. To the left, the pots are
	// numbered -1, -2, -3, and so on; to the right, 1, 2, 3.... Your puzzle input
	// contains a list of pots from 0 to the right and whether they do (#) or do not
	// (.) currently contain a plant, the initial state. (No other pots currently
	// contain plants.) For example, an initial state of #..##.... indicates that
	// pots 0, 3, and 4 currently contain plants.
	//
	// Your puzzle input also contains some notes you find on a nearby table:
	// someone has been trying to figure out how these plants spread to nearby pots.
	// Based on the notes, for each generation of plants, a given pot has or does
	// not have a plant based on whether that pot (and the two pots on either side
	// of it) had a plant in the last generation. These are written as LLCRR => N,
	// where L are pots to the left, C is the current pot being considered, R are
	// the pots to the right, and N is whether the current pot will have a plant in
	// the next generation. For example:
	//
	// A note like ..#.. => . means that a pot that contains a plant but with no
	// plants within two pots of it will not have a plant in it during the next
	// generation.
	// A note like ##.## => . means that an empty pot with two plants on each side
	// of it will remain empty in the next generation.
	// A note like .##.# => # means that a pot has a plant in a given generation if,
	// in the previous generation, there were plants in that pot, the one
	// immediately to the left, and the one two pots to the right, but not in the
	// ones immediately to the right and two to the left.
	// It's not clear what these plants are for, but you're sure it's important, so
	// you'd like to make sure the current configuration of plants is sustainable by
	// determining what will happen after 20 generations.
	//
	// For example, given the following input:
	//
	// initial state: #..#.#..##......###...###
	//
	// ...## => #
	// ..#.. => #
	// .#... => #
	// .#.#. => #
	// .#.## => #
	// .##.. => #
	// .#### => #
	// #.#.# => #
	// #.### => #
	// ##.#. => #
	// ##.## => #
	// ###.. => #
	// ###.# => #
	// ####. => #
	//
	// For brevity, in this example, only the combinations which do produce a plant
	// are listed. (Your input includes all possible combinations.) Then, the next
	// 20 generations will look like this:
	//
	// ________________1_________2_________3_____
	// ______0_________0_________0_________0_____
	// 0: ...#..#.#..##......###...###...........
	// 1: ...#...#....#.....#..#..#..#...........
	// 2: ...##..##...##....#..#..#..##..........
	// 3: ..#.#...#..#.#....#..#..#...#..........
	// 4: ...#.#..#...#.#...#..#..##..##.........
	// 5: ....#...##...#.#..#..#...#...#.........
	// 6: ....##.#.#....#...#..##..##..##........
	// 7: ...#..###.#...##..#...#...#...#........
	// 8: ...#....##.#.#.#..##..##..##..##.......
	// 9: ...##..#..#####....#...#...#...#.......
	// 10: ..#.#..#...#.##....##..##..##..##......
	// 11: ...#...##...#.#...#.#...#...#...#......
	// 12: ...##.#.#....#.#...#.#..##..##..##.....
	// 13: ..#..###.#....#.#...#....#...#...#.....
	// 14: ..#....##.#....#.#..##...##..##..##....
	// 15: ..##..#..#.#....#....#..#.#...#...#....
	// 16: .#.#..#...#.#...##...#...#.#..##..##...
	// 17: ..#...##...#.#.#.#...##...#....#...#...
	// 18: ..##.#.#....#####.#.#.#...##...##..##..
	// 19: .#..###.#..#.#.#######.#.#.#..#.#...#..
	// 20: .#....##....#####...#######....#.#..##.
	//
	// The generation is shown along the left, where 0 is the initial state. The pot
	// numbers are shown along the top, where 0 labels the center pot,
	// negative-numbered pots extend to the left, and positive pots extend toward
	// the right. Remember, the initial state begins at pot 0, which is not the
	// leftmost pot used in this example.
	//
	// After one generation, only seven plants remain. The one in pot 0 matched the
	// rule looking for ..#.., the one in pot 4 matched the rule looking for .#.#.,
	// pot 9 matched .##.., and so on.
	//
	// In this example, after 20 generations, the pots shown as # contain plants,
	// the farthest left of which is pot -2, and the farthest right of which is pot
	// 34. Adding up all the numbers of plant-containing pots after the 20th
	// generation produces 325.
	//
	// After 20 generations, what is the sum of the numbers of all pots which
	// contain a plant?

	@Override
	public Long part1() {
		final int generations = 20;
		init(getLines(), generations);
		return getSumPotNumbers(generations);
	}

	// --- Part Two ---
	//
	// You realize that 20 generations aren't enough. After all, these plants will
	// need to last another 1500 years to even reach your timeline, not to mention
	// your future.
	//
	// After fifty billion (50000000000) generations, what is the sum of the numbers
	// of all pots which contain a plant?

	@Override
	public Long part2() {
		init(getLines(), 1);
		Map<Integer, Boolean> prev = generations.get(0);
		Map<Integer, Boolean> plants = new HashMap<Integer, Boolean>();
		long prevSum = 0;
		long sum = 0;
		long diff = 0;
		long tBreakout = -1;
		for (long t = 0; t < 50000000000L; t++) {
			plants = new HashMap<Integer, Boolean>();
			grow(plants, prev);
			prev = plants;
			prevSum = sum;
			sum = getSumPotNumbers(plants);
			System.out.println(sum);
			if (diff == sum - prevSum) {
				tBreakout = t + 1;
				break;
			}
			diff = sum - prevSum;
		}
		final long result = sum + (50000000000L - tBreakout) * diff;
		System.out.println(result);
		return result;
	}

}
