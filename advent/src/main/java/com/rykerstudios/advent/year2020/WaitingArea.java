package com.rykerstudios.advent.year2020;

import com.rykerstudios.advent.Day;

public class WaitingArea {

	private static final char SEAT_FLOOR = '.';
	private static final char SEAT_EMPTY = 'L';
	private static final char SEAT_OCCUPIED = '#';

	private final int rows;
	private final int cols;
	private final int tolerance;

	private char[][] currentSeating;

	public WaitingArea(final String[] initialSeating, final int tolerance) {
		this.tolerance = tolerance;
		rows = initialSeating.length;
		cols = initialSeating[0].length();
		this.currentSeating = new char[rows][cols];
		for (int row = 0; row < rows; row++) {
			final String currentRow = initialSeating[row];
			for (int col = 0; col < cols; col++) {
				currentSeating[row][col] = currentRow.charAt(col);
			}
		}
	}

	public char[][] getSeating() {
		return currentSeating;
	}

	public boolean applySeatingRules(final boolean firstSeen) {
		final char[][] seating = new char[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				seating[row][col] = applySeatingRules(currentSeating, row, col, firstSeen);
			}
		}
		System.out.println("Applied Seating Rules: " + Day.toString(seating));
		final boolean unchanged = Day.arrayEquals(this.currentSeating, seating);
		this.currentSeating = seating;
		return !unchanged;
	}

	public char applySeatingRules(final char[][] seating, final int row, final int col, final boolean firstSeen) {
		// Floor (.) never changes; seats don't move, and nobody sits on the floor.
		if (isFloor(this.currentSeating, row, col)) {
			return SEAT_FLOOR;
		}
		final int neighbors = firstSeen ? countFirstSeen(seating, row, col) : countNeighbors(seating, row, col);
		// If a seat is empty (L)
		// and there are no occupied seats nearby,
		// the seat becomes occupied.
		if (isEmpty(this.currentSeating, row, col)) {
			if (neighbors == 0) {
				return SEAT_OCCUPIED;
			}
		}
		// If a seat is occupied (#)
		// and too many occupied seats adjacent nearby,
		// the seat becomes empty.
		if (isOccupied(this.currentSeating, row, col)) {
			if (neighbors >= this.tolerance) {
				return SEAT_EMPTY;
			}
		}
		// Otherwise, the seat's state does not change.
		return seating[row][col];
	}

	public int countFirstSeen(char[][] seating, int theRow, int theCol) {
		int count = 0;
		for (int rowDelta = -1; rowDelta <= 1; rowDelta++) {
			for (int colDelta = -1; colDelta <= 1; colDelta++) {
				if (rowDelta == 0 && colDelta == 0) {
					continue;
				}
				for (int distance = 1; distance < rows && distance < cols; distance++) {
					final int row = theRow + rowDelta * distance;
					final int col = theCol + colDelta * distance;
					if (isOutOfBounds(row, col)) {
						break;
					}
					if (isEmpty(seating, row, col)) {
						break;
					}
					if (isOccupied(seating, row, col)) {
						count++;
						break;
					}
				}
			}
		}
		return count;
	}

	public int countNeighbors(char[][] seating, int row, int col) {
		int count = 0;
		for (int row2 = row - 1; row2 < row + 2; row2++) {
			for (int col2 = col - 1; col2 < col + 2; col2++) {
				if (row2 == row && col2 == col) {
					// skip counting your own seat
					continue;
				}
				if (isOccupied(seating, row2, col2)) {
					count++;
				}
			}
		}
		return count;
	}

	public int getOccupiedSeats() {
		int count = 0;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (isOccupied(this.currentSeating, row, col)) {
					count++;
				}
			}
		}
		return count;
	}

	public boolean isEmpty(final char[][] seating, final int row, final int col) {
		if (isOutOfBounds(row, col)) {
			return false;
		}
		return (seating[row][col] == 'L');
	}

	public boolean isFloor(final char[][] seating, final int row, final int col) {
		if (isOutOfBounds(row, col)) {
			return false;
		}
		return (seating[row][col] == '.');
	}

	public boolean isOccupied(final char[][] seating, final int row, final int col) {
		if (isOutOfBounds(row, col)) {
			return false;
		}
		return (seating[row][col] == '#');
	}

	public boolean isOutOfBounds(final int row, final int col) {
		return row < 0 || row >= rows || col < 0 || col >= cols;
	}

}
