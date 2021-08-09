package com.rykerstudios.advent.year2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rykerstudios.advent.CPU;
import com.rykerstudios.advent.Day;
import com.rykerstudios.advent.OPCODE;

public class Day16 extends Day<Integer> {

	private static final Logger log = LoggerFactory.getLogger(Day16.class);

	protected static Map<Integer, OPCODE> OPCODE_MAP = new HashMap<Integer, OPCODE>();

	static {
		OPCODE_MAP.put(0, OPCODE.EQIR);
		OPCODE_MAP.put(1, OPCODE.BORR);
		OPCODE_MAP.put(2, OPCODE.ADDR);
		OPCODE_MAP.put(3, OPCODE.GTRI);
		OPCODE_MAP.put(4, OPCODE.MULI);
		OPCODE_MAP.put(5, OPCODE.GTIR);
		OPCODE_MAP.put(6, OPCODE.MULR);
		OPCODE_MAP.put(7, OPCODE.BANR);
		OPCODE_MAP.put(8, OPCODE.BORI);
		OPCODE_MAP.put(9, OPCODE.EQRI);
		OPCODE_MAP.put(10, OPCODE.EQRR);
		OPCODE_MAP.put(11, OPCODE.BANI);
		OPCODE_MAP.put(12, OPCODE.SETR);
		OPCODE_MAP.put(13, OPCODE.GTRR);
		OPCODE_MAP.put(14, OPCODE.ADDI);
		OPCODE_MAP.put(15, OPCODE.SETI);
	}

	private static final String[] OPCODES = new String[] {
			"ADDI", "ADDR", 
			"BANI", "BANR", 
			"BORI", "BORR", 
			"EQIR", "EQRI", "EQRR", 
			"GTIR", "GTRI", "GTRR", 
			"MULI", "MULR", 
			"SETI", "SETR" };

	protected List<int[][]> samples = new ArrayList<int[][]>();

	private List<int[]> testProgram = new ArrayList<int[]>();

	public Day16() {
		super(614, 656);
	}

	public int getSamplesBehavingLikeThreeOpcodes() {
		final Map<Integer, Set<String>> opCodeActsLike = new HashMap<Integer, Set<String>>();
		int count = 0;
		for (final int[][] sample : samples) {
			final List<String> opcodes = new ArrayList<String>();
			for (final String operation : OPCODES) {
				final CPU cpu = new CPU(OPCODE_MAP);
				cpu.init(sample[0]);
				cpu.execute(operation, sample[1][1], sample[1][2], sample[1][3]);
				if (arrayEquals(sample[2], cpu.registers())) {
					opcodes.add(operation);
					final StringBuilder sb = new StringBuilder();
					sb.append("Good sample ");
					sb.append("[").append(sample[1][0]);
					sb.append(",").append(sample[1][1]);
					sb.append(",").append(sample[1][2]);
					sb.append(",").append(sample[1][3]).append("]");
					sb.append(" as operation ").append(operation);
					// log.info(sb.toString());
					if (!opCodeActsLike.containsKey(sample[1][0])) {
						opCodeActsLike.put(sample[1][0], new TreeSet<String>());
					}
					opCodeActsLike.get(sample[1][0]).add(operation);
				}
			}
			if (opcodes.size() >= 3) {
				count++;
			}
		}
		log.info(opCodeActsLike.toString());
		return count;
	}

	protected List<int[]> init(final String[] lines) {
		final List<int[]> program = new ArrayList<int[]>();
		boolean inSample = false;
		int[] before = null;
		int[] opcode = null;
		int[] after = null;
		for (final String line : lines) {
			if (line.trim().isEmpty()) {
				continue;
			}
			if (line.contains("Before")) {
				before = toIntArray(removeBrackets(line));
				inSample = true;
			} else if (line.contains("After")) {
				after = toIntArray(removeBrackets(line));
				final int[][] sample = new int[3][];
				sample[0] = before;
				sample[1] = opcode;
				sample[2] = after;
				samples.add(sample);
				inSample = false;
			} else if (inSample) {
				opcode = toIntArray(removeBrackets(line));
			} else {
				program.add(toIntArray(removeBrackets(line)));
				log.debug(line);
			}
		}
		log.info("Test program size : " + program.size());
		return program;
	}

	protected String logSamples(final List<int[][]> samples) {
		final StringBuilder sb = new StringBuilder();
		sb.append("Samples:");
		for (final int[][] sample : samples) {
			sb.append("\n\t").append(toString(sample));
		}
		log.info(sb.toString());
		return sb.toString();
	}

	// --- Day 16: Chronal Classification ---
	//
	// As you see the Elves defend their hot chocolate successfully, you go back to
	// falling through time. This is going to become a problem.
	//
	// If you're ever going to return to your own time, you need to understand how
	// this device on your wrist works. You have a little while before you reach
	// your next destination, and with a bit of trial and error, you manage to pull
	// up a programming manual on the device's tiny screen.
	//
	// According to the manual, the device has four registers (numbered 0 through 3)
	// that can be manipulated by instructions containing one of 16 opcodes. The
	// registers start with the value 0.
	//
	// Every instruction consists of four values: an opcode, two inputs (named A and
	// B), and an output (named C), in that order. The opcode specifies the behavior
	// of the instruction and how the inputs are interpreted. The output, C, is
	// always treated as a register.
	//
	// In the opcode descriptions below, if something says "value A", it means to
	// take the number given as A literally. (This is also called an "immediate"
	// value.) If something says "register A", it means to use the number given as A
	// to read from (or write to) the register with that number. So, if the opcode
	// addi adds register A and value B, storing the result in register C, and the
	// instruction addi 0 7 3 is encountered, it would add 7 to the value contained
	// by register 0 and store the sum in register 3, never modifying registers 0,
	// 1, or 2 in the process.
	//
	// Many opcodes are similar except for how they interpret their arguments. The
	// opcodes fall into seven general categories:
	//
	// Addition:
	//
	// addr (add register) stores into register C the result of adding register A
	// and register B.
	// addi (add immediate) stores into register C the result of adding register A
	// and value B.
	// Multiplication:
	//
	// mulr (multiply register) stores into register C the result of multiplying
	// register A and register B.
	// muli (multiply immediate) stores into register C the result of multiplying
	// register A and value B.
	// Bitwise AND:
	//
	// banr (bitwise AND register) stores into register C the result of the bitwise
	// AND of register A and register B.
	// bani (bitwise AND immediate) stores into register C the result of the bitwise
	// AND of register A and value B.
	// Bitwise OR:
	//
	// borr (bitwise OR register) stores into register C the result of the bitwise
	// OR of register A and register B.
	// bori (bitwise OR immediate) stores into register C the result of the bitwise
	// OR of register A and value B.
	// Assignment:
	//
	// setr (set register) copies the contents of register A into register C. (Input
	// B is ignored.)
	// seti (set immediate) stores value A into register C. (Input B is ignored.)
	// Greater-than testing:
	//
	// gtir (greater-than immediate/register) sets register C to 1 if value A is
	// greater than register B. Otherwise, register C is set to 0.
	// gtri (greater-than register/immediate) sets register C to 1 if register A is
	// greater than value B. Otherwise, register C is set to 0.
	// gtrr (greater-than register/register) sets register C to 1 if register A is
	// greater than register B. Otherwise, register C is set to 0.
	// Equality testing:
	// Before: [3, 2, 1, 1]
	//
	// eqir (equal immediate/register) sets register C to 1 if value A is equal to
	// register B. Otherwise, register C is set to 0.
	// eqri (equal register/immediate) sets register C to 1 if register A is equal
	// to value B. Otherwise, register C is set to 0.
	// eqrr (equal register/register) sets register C to 1 if register A is equal to
	// register B. Otherwise, register C is set to 0.
	//
	// Unfortunately, while the manual gives the name of each opcode, it doesn't
	// seem to indicate the number. However, you can monitor the CPU to see the
	// contents of the registers before and after instructions are executed to try
	// to work them out. Each opcode has a number from 0 through 15, but the manual
	// doesn't say which is which. For example, suppose you capture the following
	// sample:
	//
	// Before: [3, 2, 1, 1]
	// 9 2 1 2
	// After: [3, 2, 2, 1]
	//
	// This sample shows the effect of the instruction 9 2 1 2 on the registers.
	// Before the instruction is executed, register 0 has value 3, register 1 has
	// value 2, and registers 2 and 3 have value 1. After the instruction is
	// executed, register 2's value becomes 2.
	//
	// The instruction itself, 9 2 1 2, means that opcode 9 was executed with A=2,
	// B=1, and C=2. Opcode 9 could be any of the 16 opcodes listed above, but only
	// three of them behave in a way that would cause the result shown in the
	// sample:
	//
	// Opcode 9 could be mulr: register 2 (which has a value of 1) times register 1
	// (which has a value of 2) produces 2, which matches the value stored in the
	// output register, register 2.
	// Opcode 9 could be addi: register 2 (which has a value of 1) plus value 1
	// produces 2, which matches the value stored in the output register, register
	// 2.
	// Opcode 9 could be seti: value 2 matches the value stored in the output
	// register, register 2; the number given for B is irrelevant.
	// None of the other opcodes produce the result captured in the sample. Because
	// of this, the sample above behaves like three opcodes.
	//
	// You collect many of these samples (the first section of your puzzle input).
	// The manual also includes a small test program (the second section of your
	// puzzle input) - you can ignore it for now.
	//
	// Ignoring the opcode numbers, how many samples in your puzzle input behave
	// like three or more opcodes?

	@Override
	public Integer part1() {
		testProgram = init(getLines());
		final int result = getSamplesBehavingLikeThreeOpcodes();
		return result;
	}

	// --- Part Two ---
	//
	// Using the samples you collected, work out the number of each opcode and
	// execute the test program (the second section of your puzzle input).
	//
	// What value is contained in register 0 after executing the test program?

	@Override
	public Integer part2() {
		testProgram = init(getLines());
		return runTestProgram(testProgram);
	}

	private Integer runTestProgram(final List<int[]> instructions) {
		final CPU cpu = new CPU(OPCODE_MAP);
		for (final int[] instruction : instructions) {
			cpu.execute(instruction[0], instruction[1], instruction[2], instruction[3]);
		}
		return cpu.registers()[0];
	}

}
