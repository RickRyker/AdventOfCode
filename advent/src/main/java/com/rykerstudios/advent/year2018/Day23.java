package com.rykerstudios.advent.year2018;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rykerstudios.advent.Day;

public class Day23 extends Day<Integer> {

	private static final Logger log = LoggerFactory.getLogger(Day23.class);

	public Day23() {
		super(232, 176367295); // 176367295 is too high
	}

	protected int distance(final int[] a, final int[] b) {
		final int dx = Math.abs(a[0] - b[0]);
		final int dy = Math.abs(a[1] - b[1]);
		final int dz = Math.abs(a[2] - b[2]);
		final int result = dx + dy + dz;
		return result;
	}

	public int[] findStrongest(final List<int[]> nanobots) {
		int maxStrength = 0;
		int[] strongest = null;
		for (final int[] nanobot : nanobots) {
			final int strength = getStrength(nanobot);
			if (maxStrength < strength) {
				maxStrength = strength;
				strongest = nanobot;
			}
		}
		return strongest;
	}

	protected List<int[]> inRange(final int[] center, final List<int[]> nanobots) {
		final StringBuilder sb = new StringBuilder();
		final int range = getStrength(center);
		final List<int[]> result = new ArrayList<int[]>();
		for (final int[] nanobot : nanobots) {
			final int distance = distance(center, nanobot);
			sb.append("The nanobot at ").append(nanobot[0]);
			sb.append(",").append(nanobot[1]);
			sb.append(",").append(nanobot[2]);
			sb.append(" is distance ").append(distance);
			sb.append(" away, and so it is ");
			if (distance <= range) {
				result.add(nanobot);
			} else {
				sb.append("not ");
			}
			sb.append("in range.");
			// log.info(sb.toString());
			sb.setLength(0);
		}
		return result;
	}

	protected int getStrength(final int[] nanobot) {
		return nanobot[3];
	}

	protected List<int[]> init(final String[] lines) {
		final List<int[]> nanobots = new ArrayList<int[]>();
		for (final String line : lines) {
			// pos=<-39857152,26545464,51505035>, r=86328482
			final int index1 = line.indexOf("<");
			final int index2 = line.indexOf(",", index1 + 1);
			final int index3 = line.indexOf(",", index2 + 1);
			final int index4 = line.indexOf(">", index3 + 1);
			final int index5 = line.indexOf("=", index4 + 1);
			final int x = Integer.parseInt(line.substring(index1 + 1, index2));
			final int y = Integer.parseInt(line.substring(index2 + 1, index3));
			final int z = Integer.parseInt(line.substring(index3 + 1, index4));
			final int r = Integer.parseInt(line.substring(index5 + 1));
			nanobots.add(new int[] { x, y, z, r });
		}
		return nanobots;
	}

	// --- Day 23: Experimental Emergency Teleportation ---
	//
	// Using your torch to search the darkness of the rocky cavern, you finally
	// locate the man's friend: a small reindeer.
	//
	// You're not sure how it got so far in this cave. It looks sick - too sick to
	// walk - and too heavy for you to carry all the way back. Sleighs won't be
	// invented for another 1500 years, of course.
	//
	// The only option is experimental emergency teleportation.
	//
	// You hit the "experimental emergency teleportation" button on the device and
	// push I accept the risk on no fewer than 18 different warning messages.
	// Immediately, the device deploys hundreds of tiny nanobots which fly around
	// the cavern, apparently assembling themselves into a very specific formation.
	// The device lists the X,Y,Z position (pos) for each nanobot as well as its
	// signal radius (r) on its tiny screen (your puzzle input).
	//
	// Each nanobot can transmit signals to any integer coordinate which is a
	// distance away from it less than or equal to its signal radius (as measured by
	// Manhattan distance). Coordinates a distance away of less than or equal to a
	// nanobot's signal radius are said to be in range of that nanobot.
	//
	// Before you start the teleportation process, you should determine which
	// nanobot is the strongest (that is, which has the largest signal radius) and
	// then, for that nanobot, the total number of nanobots that are in range of it,
	// including itself.
	//
	// For example, given the following nanobots:
	//
	// pos=<0,0,0>, r=4
	// pos=<1,0,0>, r=1
	// pos=<4,0,0>, r=3
	// pos=<0,2,0>, r=1
	// pos=<0,5,0>, r=3
	// pos=<0,0,3>, r=1
	// pos=<1,1,1>, r=1
	// pos=<1,1,2>, r=1
	// pos=<1,3,1>, r=1
	// The strongest nanobot is the first one (position 0,0,0) because its signal
	// radius, 4 is the largest. Using that nanobot's location and signal radius,
	// the following nanobots are in or out of range:
	//
	// The nanobot at 0,0,0 is distance 0 away, and so it is in range.
	// The nanobot at 1,0,0 is distance 1 away, and so it is in range.
	// The nanobot at 4,0,0 is distance 4 away, and so it is in range.
	// The nanobot at 0,2,0 is distance 2 away, and so it is in range.
	// The nanobot at 0,5,0 is distance 5 away, and so it is not in range.
	// The nanobot at 0,0,3 is distance 3 away, and so it is in range.
	// The nanobot at 1,1,1 is distance 3 away, and so it is in range.
	// The nanobot at 1,1,2 is distance 4 away, and so it is in range.
	// The nanobot at 1,3,1 is distance 5 away, and so it is not in range.
	// In this example, in total, 7 nanobots are in range of the nanobot with the
	// largest signal radius.
	//
	// Find the nanobot with the largest signal radius. How many nanobots are in
	// range of its signals?

	@Override
	public Integer part1() {
		final List<int[]> nanobots = init(getLines());
		final int[] strongest = findStrongest(nanobots);
		final List<int[]> inRange = inRange(strongest, nanobots);
		return inRange.size();
	}

	// --- Part Two ---
	//
	// Now, you just need to figure out where to position yourself so that you're
	// actually teleported when the nanobots activate.
	//
	// To increase the probability of success, you need to find the coordinate which
	// puts you in range of the largest number of nanobots. If there are multiple,
	// choose one closest to your position (0,0,0, measured by manhattan distance).
	//
	// For example, given the following nanobot formation:
	//
	// pos=<10,12,12>, r=2
	// pos=<12,14,12>, r=2
	// pos=<16,12,12>, r=4
	// pos=<14,14,14>, r=6
	// pos=<50,50,50>, r=200
	// pos=<10,10,10>, r=5
	//
	// Many coordinates are in range of some of the nanobots in this formation.
	// However, only the coordinate 12,12,12 is in range of the most nanobots: it is
	// in range of the first five, but is not in range of the nanobot at 10,10,10.
	// (All other coordinates are in range of fewer than five nanobots.) This
	// coordinate's distance from 0,0,0 is 36.
	//
	// Find the coordinates that are in range of the largest number of nanobots.
	// What is the shortest manhattan distance between any of those points and
	// 0,0,0?

	@Override
	public Integer part2() {
		final List<int[]> nanobots = init(getLines());
		final int[] origin = new int[] { 0, 0, 0 };
		int shortestDistanceToOrigin = Integer.MAX_VALUE;
		int mostNanobotsInRange = 0;
		int[] selected = null;
		// DID IT WRONG; my coordinate could be any +/- x,y,z in the input
		for (final int[] center : nanobots) {
			final int distanceToOrigin = distance(origin, center);
			final List<int[]> inRange = inRange(center, nanobots);
			final int count = inRange.size();
			if ((mostNanobotsInRange == count && distanceToOrigin < shortestDistanceToOrigin)
					|| mostNanobotsInRange < count) {
				mostNanobotsInRange = count;
				selected = center;
				shortestDistanceToOrigin = distanceToOrigin;
			}
		}
		final List<int[]> selectedInRange = inRange(selected, nanobots);
		for (final int[] nanobot : selectedInRange) {
			final int distance = distance(origin, nanobot);
			if (shortestDistanceToOrigin < distance) {
				shortestDistanceToOrigin = distance;
			}
		}
		return shortestDistanceToOrigin;
	}

}
