package com.rykerstudios.advent.year2015;

import java.util.HashMap;
import java.util.Map;

import com.rykerstudios.advent.Day;

public class Day7 extends Day<Integer> {

	final Map<String, String[]> map = new HashMap<String, String[]>();

	final Map<String, Integer> values = new HashMap<String, Integer>();

	public Day7() {
		super(3176, 14710);
	}
	private void connect(final String line) {
		final int arrow = line.indexOf("->");
		final String dest = line.substring(arrow + 3);
		final String[] operands = line.substring(0, arrow).split(" ");
		map.put(dest, operands);
	}

	protected int getValue(final String component) {
		try {
			return Integer.parseInt(component);
		} catch (final NumberFormatException e) {
			// ignore
		}
		Integer value = values.get(component);
		if (value != null) {
			return value;
		}
		final String[] operands = map.get(component);
		if (operands.length == 1) {
			// assignment
			if (map.containsKey(operands[0])) {
				value = getValue(operands[0]);
			} else {
				value = Integer.parseInt(operands[0]);
			}
		} else if (operands.length == 2 && "NOT".equals(operands[0])) {
			// NOT e -> f means that the bitwise complement of the value from wire e is
			// provided to wire f.
			value = ~getValue(operands[1]);
		} else if (operands.length == 3 && "AND".equals(operands[1])) {
			// x AND y -> z means that the bitwise AND of wire x and wire y is provided to
			// wire z.
			value = getValue(operands[0]) & getValue(operands[2]);
		} else if (operands.length == 3 && "OR".equals(operands[1])) {
			// x OR y -> z means that the bitwise OR of wire x and wire y is provided to
			// wire z.
			value = getValue(operands[0]) | getValue(operands[2]);
		} else if (operands.length == 3 && "LSHIFT".equals(operands[1])) {
			// p LSHIFT 2 -> q means that the value from wire p is left-shifted by 2 and
			// then provided to wire q.
			value = getValue(operands[0]) << getValue(operands[2]);
		} else if (operands.length == 3 && "RSHIFT".equals(operands[1])) {
			// p RSHIFT 2 -> q means that the value from wire p is right-shifted by 2 and
			// then provided to wire q.
			value = getValue(operands[0]) >> getValue(operands[2]);
		} else {
			throw new IllegalArgumentException("Unknown operand.");
		}
		values.put(component, value);
		return value;
	}

	/*
	 * --- Day 7: Some Assembly Required ---
	 * 
	 * This year, Santa brought little Bobby Tables a set of wires and bitwise logic
	 * gates! Unfortunately, little Bobby is a little under the recommended age
	 * range, and he needs help assembling the circuit.
	 * 
	 * Each wire has an identifier (some lowercase letters) and can carry a 16-bit
	 * signal (a number from 0 to 65535). A signal is provided to each wire by a
	 * gate, another wire, or some specific value. Each wire can only get a signal
	 * from one source, but can provide its signal to multiple destinations. A gate
	 * provides no signal until all of its inputs have a signal.
	 * 
	 * The included instructions booklet describes how to connect the parts
	 * together: x AND y -> z means to connect wires x and y to an AND gate, and
	 * then connect its output to wire z.
	 * 
	 * For example:
	 * 
	 * 123 -> x means that the signal 123 is provided to wire x. x AND y -> z means
	 * that the bitwise AND of wire x and wire y is provided to wire z. p LSHIFT 2
	 * -> q means that the value from wire p is left-shifted by 2 and then provided
	 * to wire q. NOT e -> f means that the bitwise complement of the value from
	 * wire e is provided to wire f. Other possible gates include OR (bitwise OR)
	 * and RSHIFT (right-shift).
	 * 
	 * If, for some reason, you'd like to emulate the circuit instead, almost all
	 * programming languages (for example, C, JavaScript, or Python) provide
	 * operators for these gates.
	 * 
	 * For example, here is a simple circuit:
	 * 
	 * 123 -> x 456 -> y x AND y -> d x OR y -> e x LSHIFT 2 -> f y RSHIFT 2 -> g
	 * NOT x -> h NOT y -> i After it is run, these are the signals on the wires:
	 * 
	 * d: 72 e: 507 f: 492 g: 114 h: 65412 i: 65079 x: 123 y: 456
	 * 
	 * In little Bobby's kit's instructions booklet (provided as your puzzle input),
	 * what signal is ultimately provided to wire a?
	 */
	@Override
	public Integer part1() {
		final String[] lines = getLines();
		for (final String line : lines) {
			connect(line);
		}
		return getValue("a");
	}

	/*
	 * --- Part Two ---
	 * 
	 * Now, take the signal you got on wire a, override wire b to that signal, and
	 * reset the other wires (including wire a).
	 * 
	 * What new signal is ultimately provided to wire a?
	 */
	@Override
	public Integer part2() {
		final String[] lines = getLines();
		for (final String line : lines) {
			connect(line);
		}
		connect(getExpectedPart1() + " -> b");
		return getValue("a");
	}

}
