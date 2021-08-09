package com.rykerstudios.advent.year2015;

import com.rykerstudios.advent.Day;

public class Day8 extends Day<Integer> {

	public Day8() {
		super(1333, 2117);
	}

	final String decode(final String s) {
		String output = s;
		if (output.startsWith("\"")) {
			output = output.substring(1);
		}
		if (output.endsWith("\"")) {
			output = output.substring(0, output.length() - 1);
		}
		while (output.contains("\\x")) {
			final int i = output.indexOf("\\x");
			final String r = output.substring(i, i + 4);
			@SuppressWarnings("unused")
			final String hex = r.substring(2);
			// final String hx = String.valueOf((char) Integer.parseInt(hex, 16));
			// We don't care about the actual replacement value - only its length.
			output = output.replace(r, "?");
		}
		output = output.replace("\\\\", "\\");
		output = output.replace("\\\"", "\"");
		// return StringEscapeUtils.escapeJava(s);
		return output;
	}

	/*
	 * --- Day 8: Matchsticks ---
	 * 
	 * Space on the sleigh is limited this year, and so Santa will be bringing his
	 * list as a digital copy. He needs to know how much space it will take up when
	 * stored.
	 * 
	 * It is common in many programming languages to provide a way to escape special
	 * characters in strings. For example, C, JavaScript, Perl, Python, and even PHP
	 * handle special characters in very similar ways.
	 * 
	 * However, it is important to realize the difference between the number of
	 * characters in the code representation of the string literal and the number of
	 * characters in the in-memory string itself.
	 * 
	 * For example:
	 * 
	 * "" is 2 characters of code (the two double quotes), but the string contains
	 * zero characters. "abc" is 5 characters of code, but 3 characters in the
	 * string data. "aaa\"aaa" is 10 characters of code, but the string itself
	 * contains six "a" characters and a single, escaped quote character, for a
	 * total of 7 characters in the string data. "\x27" is 6 characters of code, but
	 * the string itself contains just one - an apostrophe ('), escaped using
	 * hexadecimal notation. Santa's list is a file that contains many double-quoted
	 * string literals, one on each line. The only escape sequences used are \\
	 * (which represents a single backslash), \" (which represents a lone
	 * double-quote character), and \x plus two hexadecimal characters (which
	 * represents a single character with that ASCII code).
	 * 
	 * Disregarding the whitespace in the file, what is the number of characters of
	 * code for string literals minus the number of characters in memory for the
	 * values of the strings in total for the entire file?
	 * 
	 * For example, given the four strings above, the total number of characters of
	 * string code (2 + 5 + 10 + 6 = 23) minus the total number of characters in
	 * memory for string values (0 + 3 + 7 + 1 = 11) is 23 - 11 = 12.
	 */
	@Override
	public Integer part1() {
		StringBuilder sb = new StringBuilder();
		sb.append("INPUT").append("\t").append("CODE_LEN").append("\t");
		sb.append("MEMORY").append("\t").append("MEM_LEN");
		System.out.println(sb.toString());
		int result = 0;
		for (final String s : getLines()) {
			final int codeLength = s.length();
			final String decoded = decode(s);
			final int memoryLength = decoded.length();
			final int difference = codeLength - memoryLength;
			result += difference;
			sb = new StringBuilder();
			sb.append(s).append("\t").append(codeLength).append("\t");
			sb.append(decoded).append("\t").append(memoryLength);
			System.out.println(sb.toString());
		}
		System.out.println("");
		return result;
	}

	@Override
	public Integer part2() {
		return 0;
	}

}
