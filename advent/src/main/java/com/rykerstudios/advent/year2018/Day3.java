package com.rykerstudios.advent.year2018;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rykerstudios.advent.Day;

public class Day3 extends Day<Integer> {

	public Day3() {
		super(103482, 686);
	}

	final List<int[]> claims = new ArrayList<int[]>();
	final Set<Integer> good = new HashSet<Integer>();
	int maxWidth = 0;
	int maxHeight = 0;

	private void parseClaim(final String line) {
		// System.out.println(line);
		final int startClaimNbr = line.indexOf("#", 0) + 1;
		final int endClaimNbr = line.indexOf(" ", startClaimNbr);
		final int atSign = line.indexOf("@", endClaimNbr);
		final int startLeft = line.indexOf(" ", atSign) + 1;
		final int endLeft = line.indexOf(",", startLeft);
		final int startTop = line.indexOf(",", endLeft) + 1;
		final int endTop = line.indexOf(":", startTop);
		final int startWidth = line.indexOf(" ", endTop) + 1;
		final int endWidth = line.indexOf("x", startWidth);
		final int startHeight = line.indexOf("x", startWidth) + 1;
		final int endHeight = line.length();
		final String claimNbr = line.substring(startClaimNbr, endClaimNbr);
		final String left = line.substring(startLeft, endLeft);
		final String top = line.substring(startTop, endTop);
		final String width = line.substring(startWidth, endWidth);
		final String height = line.substring(startHeight, endHeight);
		final int[] claim = new int[] {
			Integer.parseInt(claimNbr),
			Integer.parseInt(left),
			Integer.parseInt(top),
			Integer.parseInt(width),
			Integer.parseInt(height)
		};
		claims.add(claim);
		if (maxWidth < claim[1] + claim[3]) {
			maxWidth = claim[1] + claim[3];
		}
		if (maxHeight < claim[2] + claim[4]) {
			maxHeight = claim[2] + claim[4];
		}
	}

	protected void readClaims() {
		final String[] lines = getLines();
		for (final String line : lines) {
			parseClaim(line);
		}
	}

	private int processClaims() {
		int count = 0;
		final int[][] grid = new int[maxWidth][maxHeight];
		final int[][] argh = new int[maxWidth][maxHeight];
		for (final int[] claim : claims) {
			final int claimNbr = claim[0];
			final int left = claim[1];
			final int top = claim[2];
			final int width = claim[3];
			final int height = claim[4];
			boolean cleanClaim = true;
			for (int i = left; i < left + width; i++) {
				for (int j = top; j < top + height; j++) {
					if (grid[i][j] == 0) {
						grid[i][j] = claimNbr;
					} else {
						argh[i][j]++;
						cleanClaim = false;
						good.remove(grid[i][j]);
					}
				}
			}
			if (cleanClaim) {
				good.add(claimNbr);
			}
		}
		for (int i = 0; i < maxWidth; i++) {
			for (int j = 0; j < maxHeight; j++) {
				if (argh[i][j] != 0) {
					count++;
				}
			}
		}
		return count;
	}

	/*
--- Day 3: No Matter How You Slice It ---

The Elves managed to locate the chimney-squeeze prototype fabric for Santa's suit (thanks to someone who helpfully wrote its box IDs on the wall of the warehouse in the middle of the night). Unfortunately, anomalies are still affecting them - nobody can even agree on how to cut the fabric.

The whole piece of fabric they're working on is a very large square - at least 1000 inches on each side.

Each Elf has made a claim about which area of fabric would be ideal for Santa's suit. All claims have an ID and consist of a single rectangle with edges parallel to the edges of the fabric. Each claim's rectangle is defined as follows:

The number of inches between the left edge of the fabric and the left edge of the rectangle.
The number of inches between the top edge of the fabric and the top edge of the rectangle.
The width of the rectangle in inches.
The height of the rectangle in inches.
A claim like #123 @ 3,2: 5x4 means that claim ID 123 specifies a rectangle 3 inches from the left edge, 2 inches from the top edge, 5 inches wide, and 4 inches tall. Visually, it claims the square inches of fabric represented by # (and ignores the square inches of fabric represented by .) in the diagram below:

...........
...........
...#####...
...#####...
...#####...
...#####...
...........
...........
...........
The problem is that many of the claims overlap, causing two or more claims to cover part of the same areas. For example, consider the following claims:

#1 @ 1,3: 4x4
#2 @ 3,1: 4x4
#3 @ 5,5: 2x2
Visually, these claim the following areas:

........
...2222.
...2222.
.11XX22.
.11XX22.
.111133.
.111133.
........
The four square inches marked with X are claimed by both 1 and 2. (Claim 3, while adjacent to the others, does not overlap either of them.)

If the Elves all proceed with their own plans, none of them will have enough fabric. How many square inches of fabric are within two or more claims?
	 */
	@Override
	public Integer part1() {
		readClaims();
		return processClaims();
	}

	// --- Part Two ---
	//
	// Amidst the chaos, you notice that exactly one claim doesn't overlap by even a
	// single square inch of fabric with any other claim. If you can somehow draw
	// attention to it, maybe the Elves will be able to make Santa's suit after all!
	//
	// For example, in the claims above, only claim 3 is intact after all claims are
	// made.
	//
	// What is the ID of the only claim that doesn't overlap?

	@Override
	public Integer part2() {
		readClaims();
		processClaims();
		return good.iterator().next();
	}

}
