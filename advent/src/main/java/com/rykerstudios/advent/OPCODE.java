package com.rykerstudios.advent;

public enum OPCODE {

	ADDI("Add Immediate"),
	ADDR("Add Register"),
	BANI("Bitwise And Immediate"),
	BANR("Bitwise And Register"),
	BORI("Bitwise Or Immediate"),
	BORR("Bitwise Or Register"),
	EQIR("Equals Immediate / Register"),
	EQRI("Equals Register / Immediate"),
	EQRR("Equals Register / Register"),
	GTIR("Greater Than Immediate / Register"),
	GTRI("Greater Than Register / Immediate"),
	GTRR("Greater Than Register / Register"),
	MULI("Multiply Immediate"),
	MULR("Multiply Register"),
	SETI("Set Immediate"),
	SETR("Set Register");

	private String desc;

	private OPCODE(final String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public static OPCODE get(final String s) {
		for (final OPCODE op : OPCODE.values()) {
			if (op.name().equalsIgnoreCase(s)) {
				return op;
			}
		}
		return null;
	}

	public static void main(final String[] args) {
		final OPCODE op = OPCODE.get("SETI");
		System.out.println(op);
	}

}
