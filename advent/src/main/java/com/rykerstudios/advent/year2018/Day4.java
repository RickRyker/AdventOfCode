package com.rykerstudios.advent.year2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rykerstudios.advent.Day;

public class Day4 extends Day<Integer> {

	public Day4() {
		super(103720, 110913);
	}

	/*
	 * --- Day 4: Repose Record ---
	 * 
	 * You've sneaked into another supply closet - this time, it's across from the
	 * prototype suit manufacturing lab. You need to sneak inside and fix the issues
	 * with the suit, but there's a guard stationed outside the lab, so this is as
	 * close as you can safely get.
	 * 
	 * As you search the closet for anything that might help, you discover that
	 * you're not the first person to want to sneak in. Covering the walls, someone
	 * has spent an hour starting every midnight for the past few months secretly
	 * observing this guard post! They've been writing down the ID of the one guard
	 * on duty that night - the Elves seem to have decided that one guard was enough
	 * for the overnight shift - as well as when they fall asleep or wake up while
	 * at their post (your puzzle input).
	 * 
	 * For example, consider the following records, which have already been
	 * organized into chronological order:

[1518-11-01 00:00] Guard #10 begins shift
[1518-11-01 00:05] falls asleep
[1518-11-01 00:25] wakes up
[1518-11-01 00:30] falls asleep
[1518-11-01 00:55] wakes up
[1518-11-01 23:58] Guard #99 begins shift
[1518-11-02 00:40] falls asleep
[1518-11-02 00:50] wakes up
[1518-11-03 00:05] Guard #10 begins shift
[1518-11-03 00:24] falls asleep
[1518-11-03 00:29] wakes up
[1518-11-04 00:02] Guard #99 begins shift
[1518-11-04 00:36] falls asleep
[1518-11-04 00:46] wakes up
[1518-11-05 00:03] Guard #99 begins shift
[1518-11-05 00:45] falls asleep
[1518-11-05 00:55] wakes up

Timestamps are written using year-month-day hour:minute format. 
The guard falling asleep or waking up is always the one whose shift most recently started. 
Because all asleep/awake times are during the midnight hour (00:00 - 00:59), 
only the minute portion (00 - 59) is relevant for those events.

Visually, these records show that the guards are asleep at these times:

Date   ID   Minute
            000000000011111111112222222222333333333344444444445555555555
            012345678901234567890123456789012345678901234567890123456789
11-01  #10  .....####################.....#########################.....
11-02  #99  ........................................##########..........
11-03  #10  ........................#####...............................
11-04  #99  ....................................##########..............
11-05  #99  .............................................##########.....
	 * 
	 * The columns are Date, which shows the month-day portion of the relevant day;
	 * ID, which shows the guard on duty that day; and Minute, which shows the
	 * minutes during which the guard was asleep within the midnight hour. (The
	 * Minute column's header shows the minute's ten's digit in the first row and
	 * the one's digit in the second row.) Awake is shown as ., and asleep is shown
	 * as #.
	 * 
	 * Note that guards count as asleep on the minute they fall asleep, and they
	 * count as awake on the minute they wake up. For example, because Guard #10
	 * wakes up at 00:25 on 1518-11-01, minute 25 is marked as awake.
	 * 
	 * If you can figure out the guard most likely to be asleep at a specific time,
	 * you might be able to trick that guard into working tonight so you can have
	 * the best chance of sneaking in. You have two strategies for choosing the best
	 * guard/minute combination.
	 * 
	 * Strategy 1: Find the guard that has the most minutes asleep. What minute does
	 * that guard spend asleep the most?
	 * 
	 * In the example above, Guard #10 spent the most minutes asleep, a total of 50
	 * minutes (20+25+5), while Guard #99 only slept for a total of 30 minutes
	 * (10+10+10). Guard #10 was asleep most during minute 24 (on two days, whereas
	 * any other minute the guard was asleep was only seen on one day).
	 * 
	 * While this example listed the entries in chronological order, your entries
	 * are in the order you found them. You'll need to organize them before they can
	 * be analyzed.
	 * 
	 * What is the ID of the guard you chose multiplied by the minute you chose? (In
	 * the above example, the answer would be 10 * 24 = 240.)
	 */
	@Override
	public Integer part1() {
		loadRecords();
		final int result = parseRecords();
		return result;
	}

	private final List<String> records = new ArrayList<String>();
	private final Map<Integer, int[]> sleepTimes = new HashMap<Integer, int[]>();
	private final Map<Integer, Integer> sleepTotals = new HashMap<Integer, Integer>();

	protected int parseRecords() {
		sleepTimes.clear();
		sleepTotals.clear();
		int guard = -1;
		int sleepStart = 0;
		int maxSleep = 0;
		int maxSleepGuard = -1;
		for (final String line : records) {
			if (line.contains("begins shift")) {
				final int hash = line.indexOf("#") + 1;
				final int space = line.indexOf(" ", hash);
				guard = Integer.parseInt(line.substring(hash, space));
				if (sleepTimes.get(guard) == null) {
					sleepTimes.put(guard, new int[60]);
				}
				if (sleepTotals.get(guard) == null) {
					sleepTotals.put(guard, 0);
				}
				continue;
			}
			final int beginIndex = line.indexOf(":") + 1;
			final int endIndex = line.indexOf("]");
			final int minute = Integer.parseInt(line.substring(beginIndex, endIndex));
			if (line.contains("falls asleep")) {
				sleepStart = minute;
				continue;
			}
			if (line.contains("wakes up")) {
				final int[] times = sleepTimes.get(guard);
				for (int i = sleepStart; i < minute; i++) {
					times[i]++;
				}
				int asleep = minute - sleepStart;
				sleepTotals.put(guard, sleepTotals.get(guard) + asleep);
				if (maxSleep < sleepTotals.get(guard)) {
					maxSleep = sleepTotals.get(guard);
					maxSleepGuard = guard;
				}
			}
		}
		System.out.println("Guard:" + maxSleepGuard);
		int minuteMark = 0;
		int maxMinutes = -1;
		final int[] guardSleepTimes = sleepTimes.get(maxSleepGuard);
		for (int i = 0; i < 60; i++) {
			final int minutes = guardSleepTimes[i];
			if (maxMinutes < minutes) {
				maxMinutes = minutes;
				minuteMark = i;
			}
		}
		System.out.println("MaxSleepGuard: " + maxSleepGuard + "; Minute:" + minuteMark + "; Result:"
				+ (maxSleepGuard * minuteMark));
		return maxSleepGuard * minuteMark;
	}

	protected void loadRecords() {
		records.clear();
		records.addAll(getRecords());
		Collections.sort(records);
		System.out.println(records);
	}

	protected List<String> getRecords() {
		final String[] lines = getLines();
		final List<String> records = new ArrayList<String>();
		for (final String line : lines) {
			records.add(line);
		}
		return records;
	}

	/*
	 * Strategy 2: Of all guards, which guard is most frequently asleep on the same
	 * minute?
	 * 
	 * In the example above, Guard #99 spent minute 45 asleep more than any other
	 * guard or minute - three times in total. (In all other cases, any guard spent
	 * any minute asleep at most twice.)
	 * 
	 * What is the ID of the guard you chose multiplied by the minute you chose? (In
	 * the above example, the answer would be 99 * 45 = 4455.)
	 * 
	 */
	@Override
	public Integer part2() {
		loadRecords();
		parseRecords();
		int maxCount = 0;
		int maxGuard = 0;
		int maxMinute = 0;
		for (final Integer guard : sleepTimes.keySet()) {
			final int[] times = sleepTimes.get(guard);
			for (int minute = 0; minute < 60; minute++) {
				final int count = times[minute];
				if (maxCount < count) {
					maxCount = count;
					maxGuard = guard;
					maxMinute = minute;
				}
			}
		}
		System.out.println("MaxGuard: " + maxGuard + "; Minute:" + maxMinute + "; Result:" + (maxGuard * maxMinute));
		return maxGuard * maxMinute;
	}

}
