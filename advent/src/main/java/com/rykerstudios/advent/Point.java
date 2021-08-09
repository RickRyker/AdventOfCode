package com.rykerstudios.advent;

public class Point implements Comparable<Point> {

	public final int x;
	public final int y;

	public Point(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Point) {
			final Point that = (Point) obj;
			return this.x == that.x && this.y == that.y;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return x + 37 * y;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("[").append(x).append(",").append(y).append("]");
		return sb.toString();
	}

	@Override
	public int compareTo(final Point that) {
		if (that == null) {
			return -1;
		}
		if (this.y != that.y) {
			return this.y - that.y;
		}
		return this.x - that.x;
	}

}