package com.rykerstudios.advent.year2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.rykerstudios.advent.Day;

public class Day16 extends Day<Long> {

	public Day16() {
		super(27870L, 3173135507987L);
	}

	/**
	 * --- Day 16: Ticket Translation ---
	 * 
	 * As you're walking to yet another connecting flight, you realize that one of
	 * the legs of your re-routed trip coming up is on a high-speed train. However,
	 * the train ticket you were given is in a language you don't understand. You
	 * should probably figure out what it says before you get to the train station
	 * after the next flight.
	 * 
	 * Unfortunately, you can't actually read the words on the ticket. You can,
	 * however, read the numbers, and so you figure out the fields these tickets
	 * must have and the valid ranges for values in those fields.
	 * 
	 * You collect the rules for ticket fields, the numbers on your ticket, and the
	 * numbers on other nearby tickets for the same train service (via the airport
	 * security cameras) together into a single document you can reference (your
	 * puzzle input).
	 * 
	 * The rules for ticket fields specify a list of fields that exist somewhere on
	 * the ticket and the valid ranges of values for each field. For example, a rule
	 * like class: 1-3 or 5-7 means that one of the fields in every ticket is named
	 * class and can be any value in the ranges 1-3 or 5-7 (inclusive, such that 3
	 * and 5 are both valid in this field, but 4 is not).
	 * 
	 * Each ticket is represented by a single line of comma-separated values. The
	 * values are the numbers on the ticket in the order they appear; every ticket
	 * has the same format. For example, consider this ticket:
	 * 
	 * .--------------------------------------------------------. <br/>
	 * | ????: 101 ?????: 102 ??????????: 103 ???: 104 | <br/>
	 * | | <br/>
	 * | ??: 301 ??: 302 ???????: 303 ??????? | <br/>
	 * | ??: 401 ??: 402 ???? ????: 403 ????????? | <br/>
	 * '--------------------------------------------------------' <br/>
	 * 
	 * Here, ? represents text in a language you don't understand. This ticket might
	 * be represented as 101,102,103,104,301,302,303,401,402,403; of course, the
	 * actual train tickets you're looking at are much more complicated. In any
	 * case, you've extracted just the numbers in such a way that the first number
	 * is always the same specific field, the second number is always a different
	 * specific field, and so on - you just don't know what each position actually
	 * means!
	 * 
	 * Start by determining which tickets are completely invalid; these are tickets
	 * that contain values which aren't valid for any field. Ignore your ticket for
	 * now.
	 * 
	 * For example, suppose you have the following notes:
	 * 
	 * class: 1-3 or 5-7 <br/>
	 * row: 6-11 or 33-44 <br/>
	 * seat: 13-40 or 45-50 <br/>
	 * 
	 * your ticket: <br/>
	 * 7,1,14 <br/>
	 * 
	 * nearby tickets: <br/>
	 * 7,3,47 <br/>
	 * 40,4,50 <br/>
	 * 55,2,20 <br/>
	 * 38,6,12 <br/>
	 * 
	 * It doesn't matter which position corresponds to which field; you can identify
	 * invalid nearby tickets by considering only whether tickets contain values
	 * that are not valid for any field. In this example, the values on the first
	 * nearby ticket are all valid for at least one field. This is not true of the
	 * other three nearby tickets: the values 4, 55, and 12 are are not valid for
	 * any field. Adding together all of the invalid values produces your ticket
	 * scanning error rate: 4 + 55 + 12 = 71.
	 * 
	 * Consider the validity of the nearby tickets you scanned. What is your ticket
	 * scanning error rate?
	 */
	@Override
	public Long part1() {
		final TicketTranslation translation = new TicketTranslation(getLines());
		return translation.getErrorRate();
	}

	/**
	 * --- Part Two ---
	 * 
	 * Now that you've identified which tickets contain invalid values, discard
	 * those tickets entirely. Use the remaining valid tickets to determine which
	 * field is which.
	 * 
	 * Using the valid ranges for each field, determine what order the fields appear
	 * on the tickets. The order is consistent between all tickets: if seat is the
	 * third field, it is the third field on every ticket, including your ticket.
	 * 
	 * For example, suppose you have the following notes:
	 * 
	 * class: 0-1 or 4-19 row: 0-5 or 8-19 seat: 0-13 or 16-19
	 * 
	 * your ticket: 11,12,13
	 * 
	 * nearby tickets: 3,9,18 15,1,5 5,14,9
	 * 
	 * Based on the nearby tickets in the above example, the first position must be
	 * row, the second position must be class, and the third position must be seat;
	 * you can conclude that in your ticket, class is 12, row is 11, and seat is 13.
	 * 
	 * Once you work out which field is which, look for the six fields on your
	 * ticket that start with the word departure. What do you get if you multiply
	 * those six values together?
	 */
	@Override
	public Long part2() {
		final TicketTranslation translation = new TicketTranslation(getLines());
		final Map<Integer, String> map = translation.mapFields();

		// field:5; rules:[departure location] 157
		// field:17; rules:[departure platform] 149
		// field:13; rules:[departure track] 83
		// field:19; rules:[departure time] 137
		// field:10; rules:[departure date] 151
		// field:18; rules:[departure station] 79

		return 1L * 157 * 149 * 83 * 137 * 151 * 79;
	}

	public static class TicketRule {

		public String name;
		public int range1Start;
		public int range1End;
		public int range2Start;
		public int range2End;

		public TicketRule(final String name,
				final int range1Start, final int range1End,
				final int range2Start, final int range2End) {
			this.name = name;
			this.range1Start = range1Start;
			this.range1End = range1End;
			this.range2Start = range2Start;
			this.range2End = range2End;
		}

		public boolean isValidForAll(final Set<Integer> values) {
			return values.stream().allMatch(v -> satisfied(v));
		}

		public boolean satisfied(final int value) {
			if (this.range1Start <= value && value <= this.range1End) {
				return true;
			}
			if (this.range2Start <= value && value <= this.range2End) {
				return true;
			}
			return false;
		}

	}

	public static class TicketTranslation {

		public List<TicketRule> ticketRules = new ArrayList<>();
		public List<Integer> yourFieldNumbers = new ArrayList<>();
		public List<List<Integer>> nearbyTickets = new ArrayList<>();

		public TicketTranslation(final String[] lines) {
			int phase = 0;
			for (final String line : lines) {
				if (line.isEmpty()) {
					phase++;
					continue;
				}
				if (line.startsWith("your ticket:")) {
					continue;
				}
				if (line.startsWith("nearby tickets:")) {
					continue;
				}
				if (phase == 0) {
					// process fields
					final String[] halves = line.split(":");
					final String fieldName = halves[0].trim();
					final String[] parts = halves[1].trim().split(" ");
					final String[] range1 = parts[0].split("-");
					final String[] range2 = parts[2].split("-");
					ticketRules.add(new TicketRule(fieldName,
							Integer.parseInt(range1[0]), Integer.parseInt(range1[1]),
							Integer.parseInt(range2[0]), Integer.parseInt(range2[1])));
				}
				if (phase == 1) {
					// process your ticket
					final String[] fieldNumbers = line.split(",");
					for (final String fieldNumber : fieldNumbers) {
						yourFieldNumbers.add(Integer.valueOf(fieldNumber));
					}
				}
				if (phase == 2) {
					// process nearby tickets
					final String[] fieldNumbers = line.split(",");
					final List<Integer> ticketFieldNumbers = new ArrayList<>();
					for (final String fieldNumber : fieldNumbers) {
						ticketFieldNumbers.add(Integer.valueOf(fieldNumber));
					}
					nearbyTickets.add(ticketFieldNumbers);
				}
			}
		}

		public long getErrorRate() {
			int errorRate = 0;
			for (final List<Integer> ticket : nearbyTickets) {
				for (final Integer fieldValue : ticket) {
					if (!isValidFieldValue(fieldValue)) {
						errorRate += fieldValue;
					}
				}
			}
			return errorRate;
		}

		public List<List<Integer>> getValidTickets() {
			final List<List<Integer>> validTickets = new ArrayList<>();
			for (final List<Integer> ticket : nearbyTickets) {
				if (isValidTicket(ticket)) {
					validTickets.add(ticket);
				}
			}
			return validTickets;
		}

		public boolean isValidFieldValue(final int fieldValue) {
			for (final TicketRule rule : this.ticketRules) {
				if (rule.satisfied(fieldValue)) {
					return true;
				}
			}
			return false;
		}

		public boolean isValidTicket(final List<Integer> ticket) {
			for (final Integer fieldValue : ticket) {
				if (!isValidFieldValue(fieldValue)) {
					return false;
				}
			}
			return true;
		}

		public Map<Integer, String> mapFields() {
			final Map<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();
			final List<List<Integer>> validTickets = getValidTickets();
			for (int fieldNumber = 0; fieldNumber < 20; fieldNumber++) {
				final Set<Integer> values = new TreeSet<>();
				for (final List<Integer> ticket : validTickets) {
					values.add(ticket.get(fieldNumber));
				}
				map.put(fieldNumber, findRuleNamesForValues(values));
			}
			// TODO :
			// Each time only 1 set of numbers satisfies 1 rule, remove it and do it again.

			// field:2; rules:[duration]
			// field:11; rules:[arrival platform]
			// field:12; rules:[row]
			// field:15; rules:[type]
			// field:16; rules:[class]
			// field:5; rules:[departure location]
			// field:17; rules:[departure platform]
			// field:13; rules:[departure track]
			// field:19; rules:[departure time]
			// field:10; rules:[departure date]
			// field:18; rules:[departure station]
			// field:6; rules:[arrival track]
			// field:0; rules:[price]
			// field:14; rules:[wagon]
			// field:9; rules:[route]
			// field:3; rules:[seat]
			// field:4; rules:[arrival location]
			// field:1; rules:[train]
			// field:8; rules:[arrival station]
			// field:7; rules:[zone]

			// your ticket:
			// 107,109,163,127,167,157,139,67,131,59,151,53,73,83,61,89,71,149,79,137

			// field: 5; rules:[departure location] 157
			// field:17; rules:[departure platform] 149
			// field:13; rules:[departure track] 83
			// field:19; rules:[departure time] 137
			// field:10; rules:[departure date] 151
			// field:18; rules:[departure station] 79

			return null;
		}

		public Set<String> findRuleNamesForValues(final Set<Integer> values) {
			final Set<String> ruleNames = new TreeSet<String>();
			for (final TicketRule rule : ticketRules) {
				if (rule.isValidForAll(values)) {
					ruleNames.add(rule.name);
				}
			}
			return ruleNames;
		}

	}

}
