package com.rykerstudios.advent.year2020;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.rykerstudios.advent.Day;

public class Day14 extends Day<Long> {

	public Day14() {
		super(9615006043476L, 4275496544925L);
	}

	/**
	 * --- Day 14: Docking Data ---
	 * 
	 * As your ferry approaches the sea port, the captain asks for your help again.
	 * The computer system that runs this port isn't compatible with the docking
	 * program on the ferry, so the docking parameters aren't being correctly
	 * initialized in the docking program's memory.
	 * 
	 * After a brief inspection, you discover that the sea port's computer system
	 * uses a strange bitmask system in its initialization program. Although you
	 * don't have the correct decoder chip handy, you can emulate it in software!
	 * 
	 * The initialization program (your puzzle input) can either update the bitmask
	 * or write a value to memory. Values and memory addresses are both 36-bit
	 * unsigned integers. For example, ignoring bitmasks for a moment, a line like
	 * mem[8] = 11 would write the value 11 to memory address 8.
	 * 
	 * The bitmask is always given as a string of 36 bits, written with the most
	 * significant bit (representing 2^35) on the left and the least significant bit
	 * (2^0, that is, the 1s bit) on the right. The current bitmask is applied to
	 * values immediately before they are written to memory: a 0 or 1 overwrites the
	 * corresponding bit in the value, while an X leaves the bit in the value
	 * unchanged.
	 * 
	 * For example, consider the following program:
	 * 
	 * mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X <br/>
	 * mem[8] = 11 <br/>
	 * mem[7] = 101 <br/>
	 * mem[8] = 0 <br/>
	 * 
	 * This program starts by specifying a bitmask (mask = ....). The mask it
	 * specifies will overwrite two bits in every written value: the 2s bit is
	 * overwritten with 0, and the 64s bit is overwritten with 1.
	 * 
	 * The program then attempts to write the value 11 to memory address 8. By
	 * expanding everything out to individual bits, the mask is applied as follows:
	 * 
	 * value: 000000000000000000000000000000001011 (decimal 11) <br/>
	 * mask: XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X <br/>
	 * result: 000000000000000000000000000001001001 (decimal 73) <br/>
	 * So, because of the mask, the value 73 is written to memory address 8 instead.
	 * Then, the program tries to write 101 to address 7:
	 * 
	 * value: 000000000000000000000000000001100101 (decimal 101) <br/>
	 * mask: XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X <br/>
	 * result: 000000000000000000000000000001100101 (decimal 101) <br/>
	 * This time, the mask has no effect, as the bits it overwrote were already the
	 * values the mask tried to set. Finally, the program tries to write 0 to
	 * address 8:
	 * 
	 * value: 000000000000000000000000000000000000 (decimal 0) <br/>
	 * mask: XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X <br/>
	 * result: 000000000000000000000000000001000000 (decimal 64) <br/>
	 * 64 is written to address 8 instead, overwriting the value that was there
	 * previously.
	 * 
	 * To initialize your ferry's docking program, you need the sum of all values
	 * left in memory after the initialization program completes. (The entire 36-bit
	 * address space begins initialized to the value 0 at every address.) In the
	 * above example, only two values in memory are not zero - 101 (at address 7)
	 * and 64 (at address 8) - producing a sum of 165.
	 * 
	 * Execute the initialization program. What is the sum of all values left in
	 * memory after it completes?
	 */
	@Override
	public Long part1() {
		final InitializationData initialization = new InitializationData(false, getLines());
		initialization.execute();
		return initialization.getSumOfAllValues();
	}

	/**
	 * --- Part Two ---
	 * 
	 * For some reason, the sea port's computer system still can't communicate with
	 * your ferry's docking program. It must be using version 2 of the decoder chip!
	 * 
	 * A version 2 decoder chip doesn't modify the values being written at all.
	 * Instead, it acts as a memory address decoder. Immediately before a value is
	 * written to memory, each bit in the bitmask modifies the corresponding bit of
	 * the destination memory address in the following way:
	 * 
	 * If the bitmask bit is 0, the corresponding memory address bit is unchanged.
	 * <br/>
	 * If the bitmask bit is 1, the corresponding memory address bit is overwritten
	 * with 1. <br/>
	 * If the bitmask bit is X, the corresponding memory address bit is floating.
	 * <br/>
	 * A floating bit is not connected to anything and instead fluctuates
	 * unpredictably. In practice, this means the floating bits will take on all
	 * possible values, potentially causing many memory addresses to be written all
	 * at once!
	 * 
	 * For example, consider the following program:
	 * 
	 * mask = 000000000000000000000000000000X1001X <br/>
	 * mem[42] = 100 <br/>
	 * mask = 00000000000000000000000000000000X0XX <br/>
	 * mem[26] = 1 <br/>
	 * When this program goes to write to memory address 42, it first applies the
	 * bitmask:
	 * 
	 * address: 000000000000000000000000000000101010 (decimal 42) <br/>
	 * mask: 000000000000000000000000000000X1001X <br/>
	 * result: 000000000000000000000000000000X1101X <br/>
	 * After applying the mask, four bits are overwritten, three of which are
	 * different, and two of which are floating. Floating bits take on every
	 * possible combination of values; with two floating bits, four actual memory
	 * addresses are written:
	 * 
	 * 000000000000000000000000000000011010 (decimal 26) <br/>
	 * 000000000000000000000000000000011011 (decimal 27) <br/>
	 * 000000000000000000000000000000111010 (decimal 58) <br/>
	 * 000000000000000000000000000000111011 (decimal 59) <br/>
	 * Next, the program is about to write to memory address 26 with a different
	 * bitmask:
	 * 
	 * address: 000000000000000000000000000000011010 (decimal 26) <br/>
	 * mask: 00000000000000000000000000000000X0XX <br/>
	 * result: 00000000000000000000000000000001X0XX <br/>
	 * This results in an address with three floating bits, causing writes to eight
	 * memory addresses:
	 * 
	 * 000000000000000000000000000000010000 (decimal 16) <br/>
	 * 000000000000000000000000000000010001 (decimal 17) <br/>
	 * 000000000000000000000000000000010010 (decimal 18) <br/>
	 * 000000000000000000000000000000010011 (decimal 19) <br/>
	 * 000000000000000000000000000000011000 (decimal 24) <br/>
	 * 000000000000000000000000000000011001 (decimal 25) <br/>
	 * 000000000000000000000000000000011010 (decimal 26) <br/>
	 * 000000000000000000000000000000011011 (decimal 27) <br/>
	 * The entire 36-bit address space still begins initialized to the value 0 at
	 * every address, and you still need the sum of all values left in memory at the
	 * end of the program. In this example, the sum is 208.
	 * 
	 * Execute the initialization program using an emulator for a version 2 decoder
	 * chip. What is the sum of all values left in memory after it completes?
	 */
	@Override
	public Long part2() {
		final InitializationData initialization = new InitializationData(true, getLines());
		initialization.execute();
		return initialization.getSumOfAllValues();
	}

	public static class InitializationData {

		final boolean version2;
		final String[] instructions;
		final Map<Long, Long> memory = new HashMap<>();
		long maskClear = 0;
		long maskSet = 0;
		final Set<String> maskFloats = new TreeSet<>();

		public InitializationData(final boolean version2, final String[] lines) {
			this.version2 = version2;
			this.instructions = lines;
		}

		public void execute() {
			for (final String instruction : instructions) {
				if (instruction.startsWith("mask")) {
					setBitMask(instruction.substring(7));
				} else if (instruction.startsWith("mem")) {
					final int leftBracketIndex = instruction.indexOf("[");
					final int rightBracketIndex = instruction.indexOf("]");
					final int equalsIndex = instruction.indexOf("=");
					final long register = Long
							.parseLong(instruction.substring(leftBracketIndex + 1, rightBracketIndex).trim());
					final long value = Long.parseLong(instruction.substring(equalsIndex + 2).trim());
					this.writeToMemory(register, value);
				}
			}
		}

		public Set<String> getFloatMasks(final String bitMask) {
			final Set<String> list = new TreeSet<>();
			if (!bitMask.contains("X")) {
				list.add(bitMask);
				return list;
			}
			final int index = bitMask.indexOf("X");
			final String firstHalf = bitMask.substring(0, index);
			final String lastHalf = bitMask.substring(index + 1);
			list.add(firstHalf + "0" + lastHalf);
			list.add(firstHalf + "1" + lastHalf);
			return getFloatMasks(list);
		}

		public Set<String> getFloatMasks(final Set<String> bitMasks) {
			final Set<String> set = new TreeSet<>();
			for (final String bitMask : bitMasks) {
				set.addAll(getFloatMasks(bitMask));
			}
			return set;
		}

		public long getSumOfAllValues() {
			long sum = 0;
			for (final Long value : this.memory.values()) {
				sum += value;
			}
			return sum;
		}

		public void setBitMask(final String bitMask) {
			if (version2) {
				maskClear = Long.parseLong(bitMask.replace("1", "0").replace("X", "1"), 2);
				maskFloats.clear();
				maskFloats.addAll(getFloatMasks(bitMask.replace("1", "0")));
			} else {
				maskClear = Long.parseLong(bitMask.replace("X", "1"), 2);
			}
			maskSet = Long.parseLong(bitMask.replace("X", "0"), 2);
		}

		public void writeToMemory(final Long register, final Long value) {
			if (this.version2) {
				final long maskedRegister = register & ~maskClear | maskSet;
				for (final String sMaskFloat : maskFloats) {
					final long maskFloat = Long.parseLong(sMaskFloat, 2);
					this.memory.put(maskedRegister | maskFloat, value);
				}
			} else {
				this.memory.put(register, value & maskClear | maskSet);
			}
		}

	}

}
