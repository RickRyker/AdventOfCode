package com.rykerstudios.advent.year2015;

import java.util.HashMap;
import java.util.Map;

import com.rykerstudios.advent.Day;

public class Day9 extends Day<Integer> {

	String[] cities;
	final Map<String, Integer> cityId = new HashMap<String, Integer>();
	int count = 0;
	int[][] routes = null;

	public Day9() {
		super(117, 909);
	}

	private int calcDistance(final int[] path) {
		String s = "";
		int distance = 0;
		for (int i = 1; i < path.length; i++) {
			final int from = path[i - 1];
			final int to = path[i];
			s += "routes[" + from + "][" + to + "]:" + routes[from][to] + "; ";
			distance += routes[from][to];
		}
		System.out.println("Path:" + s + "; Dist:" + distance);
		return distance;
	}

	private int getShortestDistance() {
		int shortestDistance = Integer.MAX_VALUE;
		final int[] path = new int[0];
		final int[] dest = new int[count];
		for (int j = 0; j < count; j++) {
			dest[j] = j;
		}
		final int distance = getShortestDistance(path, dest);
		if (shortestDistance > distance) {
			shortestDistance = distance;
		}
		return shortestDistance;
	}

	private int getLongestDistance() {
		int longestDistance = 0;
		final int[] path = new int[0];
		final int[] dest = new int[count];
		for (int j = 0; j < count; j++) {
			dest[j] = j;
		}
		final int distance = getLongestDistance(path, dest);
		if (longestDistance < distance) {
			longestDistance = distance;
		}
		return longestDistance;
	}

	private int getShortestDistance(final int[] path, final int[] dest) {
		int shortestDistance = Integer.MAX_VALUE;
		if (dest.length == 0) {
			return calcDistance(path);
		}
		for (int i = 0; i < dest.length; i++) {
			final int[] path2 = new int[path.length + 1];
			for (int j = 0; j < path.length; j++) {
				path2[j] = path[j];
			}
			path2[path.length] = dest[i];
			final int[] dest2 = new int[dest.length - 1];
			for (int j = 0; j < i; j++) {
				dest2[j] = dest[j];
			}
			for (int j = i + 1; j < dest.length; j++) {
				dest2[j - 1] = dest[j];
			}
			final int distance = getShortestDistance(path2, dest2);
			if (shortestDistance > distance) {
				shortestDistance = distance;
			}
		}
		return shortestDistance;
	}

	private int getLongestDistance(final int[] path, final int[] dest) {
		int longestDistance = 0;
		if (dest.length == 0) {
			return calcDistance(path);
		}
		for (int i = 0; i < dest.length; i++) {
			final int[] path2 = new int[path.length + 1];
			for (int j = 0; j < path.length; j++) {
				path2[j] = path[j];
			}
			path2[path.length] = dest[i];
			final int[] dest2 = new int[dest.length - 1];
			for (int j = 0; j < i; j++) {
				dest2[j] = dest[j];
			}
			for (int j = i + 1; j < dest.length; j++) {
				dest2[j - 1] = dest[j];
			}
			final int distance = getLongestDistance(path2, dest2);
			if (longestDistance < distance) {
				longestDistance = distance;
			}
		}
		return longestDistance;
	}

	protected void loadRoutes(final String[] connections) {
		for (final String connection : connections) {
			// System.out.println(connection);
			final String[] words = connection.split(" ");
			final String cityA = words[0];
			final String cityB = words[2];
			if (cityId.get(cityA) == null) {
				cityId.put(cityA, cityId.size());
			}
			if (cityId.get(cityB) == null) {
				cityId.put(cityB, cityId.size());
			}
		}
		final int n = count = cityId.size();
		cities = new String[n];
		routes = new int[n][n];
		for (final String connection : connections) {
			final String[] words = connection.split(" ");
			final String cityA = words[0];
			final String cityB = words[2];
			final int a = cityId.get(words[0]);
			final int b = cityId.get(words[2]);
			final int dist = Integer.parseInt(words[4]);
			routes[a][b] = dist;
			routes[b][a] = dist;
			if (cities[a] == null) {
				cities[a] = cityA;
			}
			if (cities[b] == null) {
				cities[b] = cityB;
			}
		}
	}

	/*
	 * --- Day 9: All in a Single Night ---
	 * 
	 * Every year, Santa manages to deliver all of his presents in a single night.
	 * 
	 * This year, however, he has some new locations to visit; his elves have
	 * provided him the distances between every pair of locations. He can start and
	 * end at any two (different) locations he wants, but he must visit each
	 * location exactly once. What is the shortest distance he can travel to achieve
	 * this?
	 * 
	 * For example, given the following distances:
	 * 
	 * London to Dublin = 464 London to Belfast = 518 Dublin to Belfast = 141 The
	 * possible routes are therefore:
	 * 
	 * Dublin -> London -> Belfast = 982 London -> Dublin -> Belfast = 605 London ->
	 * Belfast -> Dublin = 659 Dublin -> Belfast -> London = 659 Belfast -> Dublin
	 * -> London = 605 Belfast -> London -> Dublin = 982 The shortest of these is
	 * London -> Dublin -> Belfast = 605, and so the answer is 605 in this example.
	 * 
	 * What is the distance of the shortest route?
	 */
	@Override
	public Integer part1() {
		loadRoutes(getLines());
		final int shortestDistance = getShortestDistance();
		return shortestDistance;
	}

	/*
	 * 
	 * --- Part Two ---
	 * 
	 * The next year, just to show off, Santa decides to take the route with the
	 * longest distance instead.
	 * 
	 * He can still start and end at any two (different) locations he wants, and he
	 * still must visit each location exactly once.
	 * 
	 * For example, given the distances above, the longest route would be 982 via
	 * (for example) Dublin -> London -> Belfast.
	 * 
	 * What is the distance of the longest route?
	 */
	@Override
	public Integer part2() {
		loadRoutes(getLines());
		return getLongestDistance();
	}

}
