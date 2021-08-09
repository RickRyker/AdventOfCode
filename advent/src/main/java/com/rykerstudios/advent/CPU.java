package com.rykerstudios.advent;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CPU {

	public static final class Instruction {
		final OPCODE opcode;
		final int a;
		final int b;
		final int c;

		public Instruction(final OPCODE opcode, final int a, final int b, final int c) {
			this.opcode = opcode;
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}

	private static final Logger log = LoggerFactory.getLogger(CPU.class);

	private long count = 0;
	private int ip = 0;
	private int ipr = -1;
	private int[] register = new int[4];
	private Map<Integer, OPCODE> opcodeMap = new HashMap<Integer, OPCODE>();
	private Instruction[] program = null;

	public CPU() {
	}

	public CPU(final int n) {
		this.register = new int[n];
	}

	public CPU(final Map<Integer, OPCODE> opcodeMap) {
		this.opcodeMap = opcodeMap;
	}

	private void execute(final Instruction instruction) {
		if (ipr >= 0) {
			register[ipr] = ip;
		}
		try {
			final int a = instruction.a;
			final int b = instruction.b;
			final int c = instruction.c;
			switch (instruction.opcode) {
			case ADDI: // Add Immediate
				register[c] = register[a] + b;
				break;
			case ADDR: // Add Register
				register[c] = register[a] + register[b];
				break;
			case BANI: // Bitwise AND Immediate
				register[c] = register[a] & b;
				break;
			case BANR: // Bitwise AND Register
				register[c] = register[a] & register[b];
				break;
			case BORI: // Bitwise OR Immediate
				register[c] = register[a] | b;
				break;
			case BORR: // Bitwise OR Register
				register[c] = register[a] | register[b];
				break;
			case EQIR: // Equals Immediate / Register
				register[c] = (a == register[b]) ? 1 : 0;
				break;
			case EQRI: // Equals Register / Immediate
				register[c] = (register[a] == b) ? 1 : 0;
				break;
			case EQRR: // Equals Register / Register
				register[c] = (register[a] == register[b]) ? 1 : 0;
				break;
			case GTIR: // Greater Than Immediate / Register
				register[c] = (a > register[b]) ? 1 : 0;
				break;
			case GTRI: // Greater Than Register / Immediate
				register[c] = (register[a] > b) ? 1 : 0;
				break;
			case GTRR: // Greater Than Register / Register
				register[c] = (register[a] > register[b]) ? 1 : 0;
				break;
			case MULI: // Multiply Immediate
				register[c] = register[a] * b;
				break;
			case MULR:// Multiply Register
				register[c] = register[a] * register[b];
				break;
			case SETI: // Set Immediate
				register[c] = a;
				break;
			case SETR: // Set Register
				register[c] = register[a];
				break;
			default:
				break;
			}
		} catch (final ArrayIndexOutOfBoundsException e) {
			final StringBuilder sb = new StringBuilder();
			sb.append("Bad register for opcode ");
			sb.append(instruction.opcode).append(" ");
			sb.append("[").append(instruction.a);
			sb.append(",").append(instruction.b);
			sb.append(",").append(instruction.c).append("]");
			log.error(sb.toString());
		}
		final StringBuilder sb = new StringBuilder();
		sb.append("ip=").append(ip);
		sb.append(" ").append(instruction.opcode);
		sb.append(" ").append(instruction.a);
		sb.append(" ").append(instruction.b);
		sb.append(" ").append(instruction.c);
		sb.append(" ").append(Day.toString(registers()));
		log.debug(sb.toString());
		if (ipr >= 0) {
			ip = register[ipr];
			ip++;
		}
		count++;
	}

	public void execute(final int operation, final int a, final int b, final int c) {
		execute(new Instruction(opcodeMap.get(operation), a, b, c));
	}

	public void execute(final String operation, final int a, final int b, final int c) {
		execute(new Instruction(OPCODE.get(operation), a, b, c));
	}

	public long getCount() {
		return this.count;
	}

	public void init(final int[] value) {
		for (int i = 0; i < value.length; i++) {
			register[i] = value[i];
		}
	}

	public int ip() {
		return ip;
	}

	public int[] registers() {
		final int[] result = new int[register.length];
		for (int i = 0; i < register.length; i++) {
			result[i] = register[i];
		}
		return register;
	}

	public int run() {
		if (program == null) {
			throw new IllegalStateException("No program to run!");
		}
		while (ip < program.length) {
			execute(program[ip]);
		}
		return ip;
	}

	public CPU setIPRegister(final int register) {
		this.ipr = register;
		return this;
	}

	public CPU setOpcodes(final Map<Integer, OPCODE> map) {
		this.opcodeMap = map;
		return this;
	}

	public CPU setProgram(final String[] lines) {
		final Instruction[] program = new Instruction[lines.length - 1];
		for (int i = 1; i < lines.length; i++) {
			final String[] s = lines[i].split(" ");
			final String operation = s[0].toUpperCase();
			final OPCODE opcode = OPCODE.get(operation);
			if (opcode == null) {
				throw new IllegalArgumentException("Unknown CPU operation:" + operation);
			}
			final int a = Integer.parseInt(s[1]);
			final int b = Integer.parseInt(s[2]);
			final int c = Integer.parseInt(s[3]);
			program[i - 1] = new Instruction(opcode, a, b, c);
		}
		this.program = program;
		// bind the instruction pointer to a register
		this.ipr = Integer.valueOf(lines[0].split(" ")[1]);
		return this;
	}

	public CPU setRegister(final int register, final int value) {
		this.register[register] = value;
		return this;
	}

	public CPU setRegisterCount(final int n) {
		this.register = new int[n];
		return this;
	}

	public CPU reset() {
		for (int i = 0; i < register.length; i++) {
			register[i] = 0;
		}
		return this;
	}

}
