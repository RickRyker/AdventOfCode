package com.rykerstudios.advent.year2020;

import com.rykerstudios.advent.Day;

public class Day13 extends Day<Long> {

	public Day13() {
		super(6568L, 554865447501099L);
	}

	/**
	 * --- Day 13: Shuttle Search ---
	 * 
	 * Your ferry can make it safely to a nearby port, but it won't get much
	 * further. When you call to book another ship, you discover that no ships
	 * embark from that port to your vacation island. You'll need to get from the
	 * port to the nearest airport.
	 * 
	 * Fortunately, a shuttle bus service is available to bring you from the sea
	 * port to the airport! Each bus has an ID number that also indicates how often
	 * the bus leaves for the airport.
	 * 
	 * Bus schedules are defined based on a timestamp that measures the number of
	 * minutes since some fixed reference point in the past. At timestamp 0, every
	 * bus simultaneously departed from the sea port. After that, each bus travels
	 * to the airport, then various other locations, and finally returns to the sea
	 * port to repeat its journey forever.
	 * 
	 * The time this loop takes a particular bus is also its ID number: the bus with
	 * ID 5 departs from the sea port at timestamps 0, 5, 10, 15, and so on. The bus
	 * with ID 11 departs at 0, 11, 22, 33, and so on. If you are there when the bus
	 * departs, you can ride that bus to the airport!
	 * 
	 * Your notes (your puzzle input) consist of two lines. The first line is your
	 * estimate of the earliest timestamp you could depart on a bus. The second line
	 * lists the bus IDs that are in service according to the shuttle company;
	 * entries that show x must be out of service, so you decide to ignore them.
	 * 
	 * To save time once you arrive, your goal is to figure out the earliest bus you
	 * can take to the airport. (There will be exactly one such bus.)
	 * 
	 * For example, suppose you have the following notes:
	 * 
	 * 939 <br/>
	 * 7,13,x,x,59,x,31,19 <br/>
	 * 
	 * Here, the earliest timestamp you could depart is 939, and the bus IDs in
	 * service are 7, 13, 59, 31, and 19. Near timestamp 939, these bus IDs depart
	 * at the times marked D:
	 * 
	 * time bus 7 bus 13 bus 59 bus 31 bus 19 <br/>
	 * 929 . . . . . <br/>
	 * 930 . . . D . <br/>
	 * 931 D . . . D <br/>
	 * 932 . . . . . <br/>
	 * 933 . . . . . <br/>
	 * 934 . . . . . <br/>
	 * 935 . . . . . <br/>
	 * 936 . D . . . <br/>
	 * 937 . . . . . <br/>
	 * 938 D . . . . <br/>
	 * 939 . . . . . <br/>
	 * 940 . . . . . <br/>
	 * 941 . . . . . <br/>
	 * 942 . . . . . <br/>
	 * 943 . . . . . <br/>
	 * 944 . . D . . <br/>
	 * 945 D . . . . <br/>
	 * 946 . . . . . <br/>
	 * 947 . . . . . <br/>
	 * 948 . . . . . <br/>
	 * 949 . D . . . <br/>
	 * 
	 * The earliest bus you could take is bus ID 59. It doesn't depart until
	 * timestamp 944, so you would need to wait 944 - 939 = 5 minutes before it
	 * departs. Multiplying the bus ID by the number of minutes you'd need to wait
	 * gives 295.
	 * 
	 * What is the ID of the earliest bus you can take to the airport multiplied by
	 * the number of minutes you'll need to wait for that bus?
	 */
	@Override
	public Long part1() {
		final BusSchedule schedule = new BusSchedule(getLines());
		return schedule.getEarliestBusTimesWaitMinutes();
	}

	/**
	 * --- Part Two ---
	 * 
	 * The shuttle company is running a contest: one gold coin for anyone that can
	 * find the earliest timestamp such that the first bus ID departs at that time
	 * and each subsequent listed bus ID departs at that subsequent minute. (The
	 * first line in your input is no longer relevant.)
	 * 
	 * For example, suppose you have the same list of bus IDs as above:
	 * 
	 * 7,13,x,x,59,x,31,19
	 * 
	 * An x in the schedule means there are no constraints on what bus IDs must
	 * depart at that time.
	 * 
	 * This means you are looking for the earliest timestamp (called t) such that:
	 * 
	 * Bus ID 7 departs at timestamp t. <br/>
	 * Bus ID 13 departs one minute after timestamp t. <br/>
	 * There are no requirements or restrictions on departures at two or three
	 * minutes after timestamp t. <br/>
	 * Bus ID 59 departs four minutes after timestamp t. <br/>
	 * There are no requirements or restrictions on departures at five minutes after
	 * timestamp t. <br/>
	 * Bus ID 31 departs six minutes after timestamp t. <br/>
	 * Bus ID 19 departs seven minutes after timestamp t. <br/>
	 * The only bus departures that matter are the listed bus IDs at their specific
	 * offsets from t. Those bus IDs can depart at other times, and other bus IDs
	 * can depart at those times. For example, in the list above, because bus ID 19
	 * must depart seven minutes after the timestamp at which bus ID 7 departs, bus
	 * ID 7 will always also be departing with bus ID 19 at seven minutes after
	 * timestamp t. <br/>
	 * 
	 * In this example, the earliest timestamp at which this occurs is 1068781:
	 * 
	 * time bus 7 bus 13 bus 59 bus 31 bus 19 1068773 . . . . . 1068774 D . . . .
	 * 1068775 . . . . . 1068776 . . . . . 1068777 . . . . . 1068778 . . . . .
	 * 1068779 . . . . . 1068780 . . . . . 1068781 D . . . . 1068782 . D . . .
	 * 1068783 . . . . . 1068784 . . . . . 1068785 . . D . . 1068786 . . . . .
	 * 1068787 . . . D . 1068788 D . . . D 1068789 . . . . . 1068790 . . . . .
	 * 1068791 . . . . . 1068792 . . . . . 1068793 . . . . . 1068794 . . . . .
	 * 1068795 D D . . . 1068796 . . . . . 1068797 . . . . .
	 * 
	 * In the above example, bus ID 7 departs at timestamp 1068788 (seven minutes
	 * after t). This is fine; the only requirement on that minute is that bus ID 19
	 * departs then, and it does.
	 * 
	 * Here are some other examples:
	 * 
	 * The earliest timestamp that matches the list 17,x,13,19 is 3417. <br/>
	 * 67,7,59,61 first occurs at timestamp 754018. <br/>
	 * 67,x,7,59,61 first occurs at timestamp 779210. <br/>
	 * 67,7,x,59,61 first occurs at timestamp 1261476. <br/>
	 * 1789,37,47,1889 first occurs at timestamp 1202161486. <br/>
	 * 
	 * However, with so many bus IDs in your list, surely the actual earliest
	 * timestamp will be larger than 100000000000000!
	 * 
	 * What is the earliest timestamp such that all of the listed bus IDs depart at
	 * offsets matching their positions in the list?
	 */
	@Override
	public Long part2() {
		final BusSchedule schedule = new BusSchedule(getLines());
		return schedule.getConvergentTime();
	}

	public static class BusInformation {

		int id;
		int offset;
		long multiplier;
		long time;

		public BusInformation(final int index, final int id) {
			this.id = id;
			this.offset = index;
			this.time = id;
		}

	}

	public static class BusSchedule {

		final int earliestDeparture;
		final BusInformation[] buses;

		public BusSchedule(final String[] lines) {
			this.earliestDeparture = Integer.parseInt(lines[0]);
			final String[] busInfo = lines[1].replace("x", "-1").split(",");
			buses = new BusInformation[busInfo.length];
			for (int i = 0; i < busInfo.length; i++) {
				buses[i] = new BusInformation(i, Integer.parseInt(busInfo[i]));
			}
		}

		public Long getEarliestBusTimesWaitMinutes() {
			long earliestBusId = 0;
			long earliestBusTime = Long.MAX_VALUE;
			for (final BusInformation bus : this.buses) {
				if (bus.id <= 0) {
					continue;
				}
				final long tripsMade = this.earliestDeparture / bus.id;
				final long nextBusArrival = bus.id * (tripsMade + 1);
				if (earliestBusTime > nextBusArrival) {
					earliestBusTime = nextBusArrival;
					earliestBusId = bus.id;
				}
			}
			return earliestBusId * (earliestBusTime - earliestDeparture);
		}

		// Because the bus periods are all prime numbers (and therefore all co-prime),
		// This could be calculated using the Chinese Remainder Theorem. (CRT)
		// TO DO: implement using CRT
		public long getConvergentTime() {
			long multiplier = 0;
			long increment = 1;
			boolean needsWork = true;
			while (needsWork) {
				buses[0].time = buses[0].id * multiplier;
				needsWork = false;
				for (int i = 1; i < buses.length; i++) {
					if (buses[i].id == -1) {
						buses[i].time = buses[i - 1].time + 1;
						continue;
					}
					long newTime = 0;
					int j = 1;
					do {
						newTime = buses[i].id * ((buses[0].time / buses[i].id) + j++);
					} while ((newTime - buses[i - 1].time) <= 0);
					buses[i].time = newTime;
					if (buses[i].multiplier != 0) {
						increment = multiplier - buses[i].multiplier;
					}
					buses[i].multiplier = multiplier;
					if (buses[i].time != (buses[i - 1].time + 1)) {
						needsWork = true;
						break;
					}
				}
				multiplier += increment;
				final StringBuilder sb = new StringBuilder();
				for (int i = 0; i < buses.length; i++) {
					sb.append(buses[i].id + ":" + buses[i].time + ";");
				}
				// System.out.println(sb.toString());
			}
			return buses[0].time;
		}

	}

}
