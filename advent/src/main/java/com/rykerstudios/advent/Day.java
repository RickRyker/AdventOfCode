package com.rykerstudios.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Day<DATA_TYPE> {

	protected static final Logger log = LoggerFactory.getLogger(Day.class);

	public static boolean arrayEquals(final char[] a, final char[] b) {
		if (a != null && b != null) {
			if (a.length == b.length) {
				for (int i = 0; i < a.length; i++) {
					if (a[i] != b[i]) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public static boolean arrayEquals(final char[][] a, final char[][] b) {
		if (a != null && b != null) {
			if (a.length == b.length) {
				final int rows = a.length;
				final int cols = a[0].length;
				for (int row = 0; row < rows; row++) {
					for (int col = 0; col < cols; col++) {
						if (a[row][col] != b[row][col]) {
							return false;
						}
					}
				}
				return true;
			}
		}
		return false;
	}

	public static boolean arrayEquals(final int[] a, final int[] b) {
		if (a != null && b != null) {
			if (a.length == b.length) {
				for (int i = 0; i < a.length; i++) {
					if (a[i] != b[i]) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public static boolean arrayEquals(final int[] a, final long[] b) {
		if (a != null && b != null) {
			if (a.length == b.length) {
				for (int i = 0; i < a.length; i++) {
					if (a[i] != (int) b[i]) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	protected static String removeBrackets(final String s) {
		if (s == null) {
			return null;
		}
		if (s.contains("[")) {
			return s.substring(s.indexOf("[") + 1).replace("[", "").replace("]", "");
		}
		return s;
	}

	public static int[] toIntArray(final String s) {
		if (s == null) {
			return new int[0];
		}
		String s2 = s.trim();
		if (s2.contains("\n")) {
			s2 = s2.replace("\n", ",");
		}
		if (!s2.contains(",")) {
			s2 = s2.replace(" ", ",");
		}
		return toIntArray(s2.trim().replace(" ", "").split(","));
	}

	public static int[] toIntArray(final String[] sa) {
		final int[] a = new int[sa.length];
		for (int i = 0; i < a.length; i++) {
			a[i] = Integer.parseInt(sa[i]);
		}
		return a;
	}

	public static long[] toLongArray(final String s) {
		if (s == null) {
			return new long[0];
		}
		String s2 = s.trim();
		if (s2.contains("\n")) {
			s2 = s2.replace("\n", ",");
		}
		if (!s2.contains(",")) {
			s2 = s2.replace(" ", ",");
		}
		return toLongArray(s2.trim().replace(" ", "").split(","));
	}

	public static long[] toLongArray(final String[] sa) {
		final long[] a = new long[sa.length];
		for (int i = 0; i < a.length; i++) {
			a[i] = Long.parseLong(sa[i]);
		}
		return a;
	}

	public static String toString(final char[] a) {
		final StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i]).append(",");
		}
		if (a.length > 0) {
			sb.setLength(sb.length() - 1);
		}
		sb.append("]");
		return sb.toString();
	}

	public static String toString(final char[][] a) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			sb.append("[");
			for (int j = 0; j < a[i].length; j++) {
				sb.append(a[i][j]).append(",");
			}
			if (a[i].length > 0) {
				sb.setLength(sb.length() - 1);
			}
			sb.append("],");
		}
		if (a.length > 0) {
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();
	}

	public static String toString(final int[] a) {
		final StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i]).append(",");
		}
		if (a.length > 0) {
			sb.setLength(sb.length() - 1);
		}
		sb.append("]");
		return sb.toString();
	}

	public static String toString(final int[][] a) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			sb.append("[");
			for (int j = 0; j < a[i].length; j++) {
				sb.append(a[i][j]).append(",");
			}
			if (a[i].length > 0) {
				sb.setLength(sb.length() - 1);
			}
			sb.append("],");
		}
		if (a.length > 0) {
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();
	}

	public static String toString(final long[] a) {
		final StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i]).append(",");
		}
		if (a.length > 0) {
			sb.setLength(sb.length() - 1);
		}
		sb.append("]");
		return sb.toString();
	}

	public static String toString(final String[] a) {
		final StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i]).append(",");
		}
		if (a.length > 0) {
			sb.setLength(sb.length() - 1);
		}
		sb.append("]");
		return sb.toString();
	}

	private String content = null;

	private DATA_TYPE expectedPart1 = null;

	private DATA_TYPE expectedPart2 = null;

	public Day() {
	}

	protected Day(final DATA_TYPE expectedPart1, final DATA_TYPE expectedPart2) {
		this.expectedPart1 = expectedPart1;
		this.expectedPart2 = expectedPart2;
	}

	public String getContent() {
		if (content == null) {
			loadFile();
		}
		return content;
	}

	public String getDataFileName() {
		// Determine the default file name to load
		final String packageName = getClass().getPackage().getName();
		final String[] packageNameParts = packageName.split("\\.");
		final String prefix = packageNameParts[packageNameParts.length - 1] + "/";
		final String fileName = prefix + getClass().getSimpleName() + ".txt";
		// log.info(fileName);
		return fileName;
	}

	public DATA_TYPE getExpectedPart1() {
		return expectedPart1;
	}

	public DATA_TYPE getExpectedPart2() {
		return expectedPart2;
	}

	public int[] getInts() {
		return toIntArray(getLines());
	}

	public String[] getLines() {
		return getContent().split("\n");
	}

	public long[] getLongs() {
		return toLongArray(getLines());
	}

	public String getTestData(final String suffix) {
		return loadFile(getTestDataFileName(suffix));
	}

	public String getTestDataFileName(final String suffix) {
		return getDataFileName().replace(".txt", suffix + ".txt");
	}

	public String[] getTestDataLines() {
		return getTestData("Example").split("\n");
	}

	public String[] getTestDataLines(final String suffix) {
		return getTestData(suffix).split("\n");
	}

	public Day<DATA_TYPE> loadFile() {
		setContent(loadFile(getDataFileName()));
		return this;
	}

	public String loadFile(final String fileName) {
		if (fileName != null) {
			final ClassLoader loader = getClass().getClassLoader();
			try (final InputStream is = loader.getResourceAsStream(fileName)) {
				return readFromInputStream(is);
			} catch (final IOException e) {
				log.error("IOException: " + e);
			}
		}
		return null;
	}

	public DATA_TYPE part1() {
		return null;
	}

	public DATA_TYPE part2() {
		return null;
	}

	protected String readFromInputStream(final InputStream inputStream) throws IOException {
		final StringBuilder resultStringBuilder = new StringBuilder();
		try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}

	protected void setContent(final String content) {
		this.content = content;
	}

	public void setExpectedPart1(final DATA_TYPE expectedPart1) {
		this.expectedPart1 = expectedPart1;
	}

	public void setExpectedPart2(final DATA_TYPE expectedPart2) {
		this.expectedPart2 = expectedPart2;
	}

}
