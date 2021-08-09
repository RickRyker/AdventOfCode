package com.rykerstudios.advent.year2020;

import java.util.ArrayList;
import java.util.List;

public class BoardingPass {

	private final int row;
	private final int col;
	private final int seatId;

	public BoardingPass(final String line) {
		// first 7 bits for column, last 3 bits for column
		row = Integer.parseInt(line.substring(0, 7).replace("F", "0").replace("B", "1"), 2);
		col = Integer.parseInt(line.substring(7, 10).replace("L", "0").replace("R", "1"), 2);
		seatId = row * 8 + col;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public int getSeatId() {
		return seatId;
	}

	public static List<BoardingPass> parse(final String[] lines) {
		final List<BoardingPass> passes = new ArrayList<>();
		for (final String line : lines) {
			passes.add(new BoardingPass(line));
		}
		return passes;
	}

}
