package com.rykerstudios.advent.year2019;

import java.util.LinkedList;
import java.util.Queue;

public class IntCodeComputer {

	private static final int MODE_POS = 0;
	private static final int MODE_IMM = 1;
	private static final int MODE_REL = 2;

	private static final int OP_ADD = 1;
	private static final int OP_MUL = 2;
	private static final int OP_INP = 3;
	private static final int OP_OUT = 4;
	private static final int OP_JNZ = 5;
	private static final int OP_JZE = 6;
	private static final int OP_TLT = 7;
	private static final int OP_TEQ = 8;
	private static final int OP_REL = 9;
	private static final int OP_STP = 99;

	private String name = "PGM";

	private boolean running = true;

	private int base = 0;
	private int ip = 0;
	private int[] modes = new int[] { 0, 0, 0, 0 };
	private long lastOutput = 0;

	private long[] memory = null;
	private long[] params = new long[] { 0, 0, 0, 0 };

	private Queue<Long> inputs = new LinkedList<Long>();
	private Queue<Long> outputs = new LinkedList<Long>();

	public IntCodeComputer(final int[] memory) {
		this.memory = new long[memory.length];
		for (int i = 0; i < memory.length; i++) {
			this.memory[i] = memory[i];
		}
	}

	public IntCodeComputer(final long[] memory) {
		this.memory = new long[memory.length];
		for (int i = 0; i < memory.length; i++) {
			this.memory[i] = memory[i];
		}
	}

	public IntCodeComputer(final int[] memory, final long input) {
		this(memory);
		addInput(input);
	}

	public void addInput(final long... inputs) {
		for (final long input : inputs) {
			this.inputs.add(input);
		}
	}

	public void addOutput(final long output) {
		outputs.add(output);
	}

	public boolean calc() {
		while (running) {
			System.out.print("PGM:" + name + "; IP:" + ip + "; ");
			if (memory.length <= ip) {
				System.out.println("IP past end of memory!");
				running = false;
				return false;
			}
			final int instruction = (int) memory[ip];
			params[0] = (int) memory[ip];
			params[1] = (ip + 1 < memory.length) ? memory[ip + 1] : 0;
			params[2] = (ip + 2 < memory.length) ? memory[ip + 2] : 0;
			params[3] = (ip + 3 < memory.length) ? memory[ip + 3] : 0;
			modes[0] = instruction % 100;
			modes[1] = (instruction / 100) % 10;
			modes[2] = (instruction / 1000) % 10;
			modes[3] = (instruction / 10000) % 10;
			final int opc = modes[0];
			if (OP_STP == opc) {
				running = false;
				return false;
			} else if (OP_ADD == opc) {
				writeParam(3, readParam(1) + readParam(2));
				ip += 4;
			} else if (OP_MUL == opc) {
				writeParam(3, readParam(1) * readParam(2));
				ip += 4;
			} else if (OP_JNZ == opc) {
				if (readParam(1) != 0) {
					ip = (int) readParam(2);
				} else {
					ip += 3;
				}
			} else if (OP_JZE == opc) {
				if (readParam(1) == 0) {
					ip = (int) readParam(2);
				} else {
					ip += 3;
				}
			} else if (OP_TLT == opc) {
				writeParam(3, readParam(1) < readParam(2) ? 1 : 0);
				ip += 4;
			} else if (OP_TEQ == opc) {
				writeParam(3, readParam(1) == readParam(2) ? 1 : 0);
				ip += 4;
			} else if (OP_REL == opc) {
				base += readParam(1);
				ip += 2;
			} else if (OP_INP == opc) {
				if (!hasInputs()) {
					break;
				}
				writeParam(1, inputs.poll());
				ip += 2;
			} else if (OP_OUT == opc) {
				lastOutput = readParam(1);
				outputs.add(lastOutput);
				ip += 2;
			} else {
				throw new IllegalArgumentException("Unknown operation code " + opc);
			}
		}
		return running;
	}

	public boolean calc(final long[] memory) {
		this.memory = memory;
		return this.calc();
	}

	private void checkMemory(final int address) {
		if (memory.length <= address) {
			final long[] old = memory;
			memory = new long[address + 1];
			for (int i = 0; i < old.length; i++) {
				memory[i] = old[i];
			}
		}
	}

	public long getLastOutput() {
		return this.lastOutput;
	}

	public long[] getMemory() {
		return this.memory;
	}

	public long getOutput() {
		return outputs.poll();
	}

	public boolean hasInputs() {
		return inputs.size() > 0;
	}

	public boolean hasOutputs() {
		return outputs.size() > 0;
	}

	public boolean isRunning() {
		return running;
	}

	public long readParam(final int i) {
		final int mode = modes[i];
		final int operand = (int) params[i];
		switch (mode) {
		case MODE_IMM:
			return params[i]; // original long value
		case MODE_POS:
			checkMemory(operand);
			return memory[operand];
		case MODE_REL:
			checkMemory(operand + base);
			return memory[operand + base];
		default:
			throw new IllegalArgumentException("Unknown mode " + mode);
		}
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void writeParam(final int i, final long value) {
		final int mode = modes[i];
		final int operand = (int) params[i];
		switch (mode) {
		case MODE_POS:
			checkMemory(operand);
			memory[operand] = value;
			break;
		case MODE_REL:
			checkMemory(operand + base);
			memory[operand + base] = value;
			break;
		default:
			throw new IllegalArgumentException("Unknown mode " + mode);
		}
	}

}
