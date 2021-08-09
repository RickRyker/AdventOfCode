package com.rykerstudios.advent.year2020;

import java.util.HashSet;
import java.util.Set;

public class Handheld {

	private Instruction[] instructions;

	private int accumulator = 0;
	private int ip = 0;

	public Handheld(final String lines[]) {
		instructions = new Instruction[lines.length];
		int i = 0;
		for (final String line : lines) {
			instructions[i] = new Instruction(line);
			i++;
		}
	}

	public void changeInstruction(final int ip) {
		final Instruction instruction = instructions[ip];
		if ("jmp".equals(instruction.op)) {
			instruction.op = "nop";
		} else if ("nop".equals(instruction.op)) {
			instruction.op = "jmp";
		}
	}

	public void executeStep() {
		if (ip >= instructions.length) {
			final String message = "Program Termination. IP:" + ip + "; ACC:" + accumulator;
			System.out.println(message);
			throw new RuntimeException(message);
		}
		final Instruction instruction = instructions[ip];
		System.out.println(ip + ":" + instruction.op + " " + instruction.val);
		final String nextOp = instruction.op;
		final int value = instruction.val;
		if ("acc".equals(nextOp)) {
			accumulator += value;
		} else if ("jmp".equals(nextOp)) {
			ip += value;
			return;
		} else if ("nop".equals(nextOp)) {
			// do nothing
		}
		ip++;
	}

	public void findLoop() {
		reset();
		System.out.println("--------");
		final Set<Integer> visited = new HashSet<>();
		while (!visited.contains(getInstructionPointer())) {
			visited.add(getInstructionPointer());
			executeStep();
		}
		System.out.println("--------");
		System.out.println(ip + ":" + instructions[ip].op + " " + instructions[ip].val);
		return;
	}

	public int getAccumulator() {
		return accumulator;
	}

	public int getInstructionPointer() {
		return ip;
	}

	public void reset() {
		accumulator = 0;
		ip = 0;
	}

}

class Instruction {

	String op;
	int val;

	public Instruction(final String line) {
		final String[] parts = line.split(" ");
		op = parts[0];
		val = Integer.parseInt(parts[1]);
	}

}
