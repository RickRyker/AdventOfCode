package com.rykerstudios.advent.year2015;

import com.rykerstudios.advent.Day;

public class Day12 extends Day<Integer> {

	public Day12() {
		super(111754, -69766);
	}

	protected Integer sumTheNumbers(final String content) {
		int sum = 0;
		int index = 0;
		boolean inNumber = false;
		final char[] a = content.toCharArray();
		final int len = a.length;
		while (index < len) {
			if (!inNumber) {
				while (index < len && !Character.isDigit(a[index])) {
					index++;
				}
			}
			if (index >= len) {
				break;
			}
			inNumber = true;
			int beginIndex = index;
			// check for unary minus in front of number
			if (beginIndex > 0 && a[beginIndex - 1] == '-') {
				beginIndex--;
			}
			while (index < len && Character.isDigit(a[index])) {
				index++;
			}
			inNumber = false;
			final int endIndex = index;
			final String s = content.substring(beginIndex, endIndex);
			// System.out.println(s);
			final int value = Integer.parseInt(s);
			sum += value;
			if (index >= len) {
				break;
			}
		}
		System.out.println("Sum:" + sum);
		return sum;
	}

	/*
	 * --- Day 12: JSAbacusFramework.io ---
	 * 
	 * Santa's Accounting-Elves need help balancing the books after a recent order.
	 * Unfortunately, their accounting software uses a peculiar storage format.
	 * That's where you come in.
	 * 
	 * They have a JSON document which contains a variety of things: arrays
	 * ([1,2,3]), objects ({"a":1, "b":2}), numbers, and strings. Your first job is
	 * to simply find all of the numbers throughout the document and add them
	 * together.
	 * 
	 * For example:
	 * 
	 * [1,2,3] and {"a":2,"b":4} both have a sum of 6. [[[3]]] and
	 * {"a":{"b":4},"c":-1} both have a sum of 3. {"a":[-1,1]} and [-1,{"a":1}] both
	 * have a sum of 0. [] and {} both have a sum of 0. You will not encounter any
	 * strings containing numbers.
	 * 
	 * What is the sum of all numbers in the document?
	 */
	@Override
	public Integer part1() {
		final String content = getContent().trim();
		System.out.println(content);
		System.out.println("Part1 Length:" + content);
		return sumTheNumbers(content);
	}

	/*
	 * --- Part Two ---
	 * 
	 * Uh oh - the Accounting-Elves have realized that they double-counted
	 * everything red.
	 * 
	 * Ignore any object (and all of its children) which has any property with the
	 * value "red". Do this only for objects ({...}), not arrays ([...]).
	 * 
	 * [1,2,3] still has a sum of 6.
	 * 
	 * [1,{"c":"red","b":2},3] now has a sum of 4, because the middle object is
	 * ignored.
	 * 
	 * {"d":"red","e":[1,2,3,4],"f":5} now has a sum of 0, because the entire
	 * structure is ignored.
	 * 
	 * [1,"red",5] has a sum of 6, because "red" in an array has no effect.
	 */
	@Override
	public Integer part2() {
		final String content = getContent().trim();
		System.out.println(content);
		System.out.println("Part2 Length:" + content.length());
		final String newContent = removeObjectsWithRedValues(content);
		System.out.println(newContent);
		System.out.println("Part2 Length:" + newContent.length());
		return sumTheNumbers(newContent);
	}

	protected String removeObjectsWithRedValues(final String content) {
		final String RED = ":\"red\"";
		String newContent = content;
		while (true) {
			boolean removed = false;
			int red = newContent.indexOf(RED);
			if (red > -1) {
				final int beginIndex = newContent.substring(0, red).lastIndexOf("{");
				int nextOpenBrace = newContent.indexOf("{", red);
				int endIndex = newContent.indexOf("}", red) + 1;
				while (nextOpenBrace > -1 && endIndex > nextOpenBrace) {
					// embedded object that also needs to be skipped
					nextOpenBrace = newContent.indexOf("{", nextOpenBrace + 1);
					endIndex = newContent.indexOf("}", endIndex) + 1;
				}
				newContent = newContent.substring(0, beginIndex) + newContent.substring(endIndex);
				removed = true;
			}
			if (!removed) {
				break;
			}
		}
		return newContent;
	}

}
