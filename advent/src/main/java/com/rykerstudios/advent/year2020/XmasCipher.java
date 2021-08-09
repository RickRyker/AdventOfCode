package com.rykerstudios.advent.year2020;

public class XmasCipher {

	private final int preamble;
	private final long[] values;

	public XmasCipher(final int preambleLength, final String[] lines) {
		this.preamble = preambleLength;
		this.values = new long[lines.length];
		int index = 0;
		for (final String line : lines) {
			values[index] = Long.parseLong(line);
			index++;
		}
	}

	public long findAnomaly() {
		for (int i = preamble; i < values.length; i++) {
			boolean found = false;
			for (int j = i - preamble; j < i; j++) {
				for (int k = j + 1; k < i; k++) {
					if (values[i] == values[j] + values[k]) {
						found = true;
						break;
					}
				}
				if (found) {
					break;
				}
			}
			if (!found) {
				return values[i];
			}
		}
		return 0;
	}

	public long findWeakness(final long anomaly) {
		int i = 0;
		int j = 0;
		boolean found = false;
		for (i = 0; i < values.length; i++) {
			long sum = values[i];
			long smallest = values[i];
			long largest = values[i];
			for (j = i + 1; j < values.length; j++) {
				sum += values[j];
				if (smallest > values[j]) {
					smallest = values[j];
				}
				if (largest < values[j]) {
					largest = values[j];
				}
				if (sum == anomaly) {
					found = true;
					return smallest + largest;
				}
				if (sum >= anomaly) {
					break;
				}
			}
			if (found) {
			}
		}
		return 0;
	}

}
