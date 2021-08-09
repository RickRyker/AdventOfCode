package com.rykerstudios.advent.year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestIntCodeComputer {

	// --- Day 2 ---

	@Test
	public void testCalc_whenDay2Test1_then3500() {
		final int[] memory = { 1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50 };
		final IntCodeComputer program = new IntCodeComputer(memory, 0);
		program.calc();
		assertEquals(3500, program.getMemory()[0]);
	}

	@Test
	public void testCalc_whenDay2Test2_1plus1_then2() {
		final int[] memory = { 1, 0, 0, 0, 99 };
		final IntCodeComputer program = new IntCodeComputer(memory, 0);
		program.calc();
		assertEquals(2, program.getMemory()[0]);
	}

	@Test
	public void testCalc_whenDay2Test3_3times2_then6() {
		final int[] memory = { 2, 3, 0, 3, 99 };
		final IntCodeComputer program = new IntCodeComputer(memory, 0);
		program.calc();
		assertEquals(6, program.getMemory()[3]);
	}

	@Test
	public void testCalc_whenDay2Test4_99times99_then9801() {
		final int[] memory = { 2, 4, 4, 5, 99, 0 };
		final IntCodeComputer program = new IntCodeComputer(memory, 0);
		program.calc();
		assertEquals(9801, program.getMemory()[5]);
	}

	@Test
	public void testCalc_whenDay2Test5() {
		final int[] memory = { 1, 1, 1, 4, 99, 5, 6, 0, 99 };
		final IntCodeComputer program = new IntCodeComputer(memory, 0);
		program.calc();
		assertEquals(30, (int) program.getMemory()[0]);
		assertEquals(2, (int) program.getMemory()[4]);
	}

	// --- Day 5 ---

	@Test
	public void testCalc_whenDay5Test1_33times3_then99() {
		final int[] memory = { 1002, 4, 3, 4, 33 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 1);
		computer.calc();
		assertEquals(99, computer.getMemory()[4]);
	}

	@Test
	public void testCalc_whenDay5Test2_7equalTo8_then0() {
		final int[] memory = { 3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 7);
		computer.calc();
		assertEquals(0, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test3_8equalTo8_then1() {
		final int[] memory = { 3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 8);
		computer.calc();
		assertEquals(1, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test4_7lessThan8_then0() {
		final int[] memory = { 3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 7);
		computer.calc();
		assertEquals(1, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test5_8lessThan8_then1() {
		final int[] memory = { 3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 8);
		computer.calc();
		assertEquals(0, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test6_usingImmediate_7equals8_then0() {
		final int[] memory = { 3, 3, 1108, -1, 8, 3, 4, 3, 99 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 7);
		computer.calc();
		assertEquals(0, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test7_usingImmediate_when8equal8_then1() {
		final int[] memory = { 3, 3, 1108, -1, 8, 3, 4, 3, 99 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 8);
		computer.calc();
		assertEquals(1, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test8_usingImmediate_when7lessThan8_then1() {
		final int[] memory = { 3, 3, 1107, -1, 8, 3, 4, 3, 99 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 7);
		computer.calc();
		assertEquals(1, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test9_usingImmediate_when8lessThan8_then0() {
		final int[] memory = { 3, 3, 1107, -1, 8, 3, 4, 3, 99 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 8);
		computer.calc();
		assertEquals(0, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test10_jumpUsingPosition_when0_then0() {
		final int[] memory = { 3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 0);
		computer.calc();
		assertEquals(0, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test11_jumpUsingPosition_when8_then1() {
		final int[] memory = { 3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 8);
		computer.calc();
		assertEquals(1, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test12_jumpUsingImmediate_when0_then0() {
		final int[] memory = { 3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 0);
		computer.calc();
		assertEquals(0, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test13_jumpUsingImmediate_when8_then1() {
		final int[] memory = { 3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 8);
		computer.calc();
		assertEquals(1, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test14_jumpUsingBoth_when7_then1000() {
		final int[] memory = { 3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0,
				0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46,
				98, 99 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 7);
		computer.calc();
		assertEquals(999, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test15_whenJumpUsingBoth_when8_then1000() {
		final int[] memory = { 3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0,
				0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46,
				98, 99 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 8);
		computer.calc();
		assertEquals(1000, computer.getLastOutput());
	}

	@Test
	public void testCalc_whenDay5Test16_whenJumpUsingBoth_when9_then1000() {
		final int[] memory = { 3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0,
				0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46,
				98, 99 };
		final IntCodeComputer computer = new IntCodeComputer(memory, 9);
		computer.calc();
		assertEquals(1001, computer.getLastOutput());
	}

	// --- Day 9 ---

	@Test
	public void testCalc_whenDay9Test1_opCode9() {
		final long[] memory = { 109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99 };
		final IntCodeComputer computer = new IntCodeComputer(memory);
		computer.calc();
		for (int i = 0; i < memory.length; i++) {
			assertEquals(memory[i], computer.getOutput());
		}
	}

	@Test
	public void testCalc_whenDay9Test2_largeOperands_thenCalcLargeOutput() {
		final long[] memory = { 1102, 34915192, 34915192, 7, 4, 7, 99, 0 };
		final IntCodeComputer computer = new IntCodeComputer(memory);
		computer.calc();
		assertEquals(1219070632396864L, computer.getOutput());
	}

	@Test
	public void testCalc_whenDay9Test3_largeOperands_thenOutputLargeOperand() {
		final long[] memory = { 104, 1125899906842624L, 99 };
		final IntCodeComputer computer = new IntCodeComputer(memory);
		computer.calc();
		assertEquals(1125899906842624L, computer.getOutput());
	}

}
