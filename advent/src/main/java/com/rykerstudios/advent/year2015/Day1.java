package com.rykerstudios.advent.year2015;

import com.rykerstudios.advent.Day;

public class Day1 extends Day<Integer> {

	public Day1() {
		super(74, 1795);
	}

	/*
	 * --- Day 1: Not Quite Lisp --- Santa is trying to deliver presents in a large
	 * apartment building, but he can't find the right floor - the directions he got
	 * are a little confusing. He starts on the ground floor (floor 0) and then
	 * follows the instructions one character at a time.
	 * 
	 * An opening parenthesis, (, means he should go up one floor, and a closing
	 * parenthesis, ), means he should go down one floor.
	 * 
	 * The apartment building is very tall, and the basement is very deep; he will
	 * never find the top or bottom floors.
	 * 
	 * For example:
	 * 
	 * (()) and ()() both result in floor 0. ((( and (()(()( both result in floor 3.
	 * ))((((( also results in floor 3. ()) and ))( both result in floor -1 (the
	 * first basement level). ))) and )())()) both result in floor -3. To what floor
	 * do the instructions take Santa?
	 */
	@Override
	public Integer part1() {
		int floor = 0;
		final String content = getContent();
		for (int i = 0; i < content.length(); i++) {
			final String s = content.substring(i, i + 1);
			if ("(".equals(s)) {
				floor++;
			} else if (")".equals(s)) {
				floor--;
			} else {
				System.out.println("ERROR!");
			}
		}
		return floor;
	}

	/*
	 * --- Part Two --- Now, given the same instructions, find the position of the
	 * first character that causes him to enter the basement (floor -1). The first
	 * character in the instructions has position 1, the second character has
	 * position 2, and so on.
	 * 
	 * For example:
	 * 
	 * ) causes him to enter the basement at character position 1. ()()) causes him
	 * to enter the basement at character position 5. What is the position of the
	 * character that causes Santa to first enter the basement?
	 */

	@Override
	public Integer part2() {
		long floor = 0;
		final String content = getContent();
		int i = 0;
		for (; i < content.length(); i++) {
			final String s = content.substring(i, i + 1);
			if ("(".equals(s)) {
				floor++;
			} else if (")".equals(s)) {
				floor--;
				if (floor < 0) {
					break;
				}
			} else {
				System.out.println("ERROR!");
			}
		}
		return i + 1;
	}

}
