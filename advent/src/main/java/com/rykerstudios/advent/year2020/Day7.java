package com.rykerstudios.advent.year2020;

import java.util.List;

import com.rykerstudios.advent.Day;

public class Day7 extends Day<Integer> {

	public Day7() {
		super(169, 82372);
	}

	/**
	 * --- Day 7: Handy Haversacks ---
	 * 
	 * You land at the regional airport in time for your next flight. In fact, it
	 * looks like you'll even have time to grab some food: all flights are currently
	 * delayed due to issues in luggage processing.
	 * 
	 * Due to recent aviation regulations, many rules (your puzzle input) are being
	 * enforced about bags and their contents; bags must be color-coded and must
	 * contain specific quantities of other color-coded bags. Apparently, nobody
	 * responsible for these regulations considered how long they would take to
	 * enforce!
	 * 
	 * For example, consider the following rules:
	 * 
	 * light red bags contain 1 bright white bag, 2 muted yellow bags. <br/>
	 * dark orange bags contain 3 bright white bags, 4 muted yellow bags. <br/>
	 * bright white bags contain 1 shiny gold bag. <br/>
	 * muted yellow bags contain 2 shiny gold bags, 9 faded blue bags. <br/>
	 * shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags. <br/>
	 * dark olive bags contain 3 faded blue bags, 4 dotted black bags. <br/>
	 * vibrant plum bags contain 5 faded blue bags, 6 dotted black bags. <br/>
	 * faded blue bags contain no other bags. <br/>
	 * dotted black bags contain no other bags. <br/>
	 * 
	 * These rules specify the required contents for 9 bag types. In this example,
	 * every faded blue bag is empty, every vibrant plum bag contains 11 bags (5
	 * faded blue and 6 dotted black), and so on.
	 * 
	 * You have a shiny gold bag. If you wanted to carry it in at least one other
	 * bag, how many different bag colors would be valid for the outermost bag? (In
	 * other words: how many colors can, eventually, contain at least one shiny gold
	 * bag?)
	 * 
	 * In the above rules, the following options would be available to you:
	 * 
	 * A bright white bag, which can hold your shiny gold bag directly. <br/>
	 * A muted yellow bag, which can hold your shiny gold bag directly, plus some
	 * other bags. <br/>
	 * A dark orange bag, which can hold bright white and muted yellow bags, either
	 * of which could then hold your shiny gold bag. <br/>
	 * A light red bag, which can hold bright white and muted yellow bags, either of
	 * which could then hold your shiny gold bag. <br/>
	 * 
	 * So, in this example, the number of bag colors that can eventually contain at
	 * least one shiny gold bag is 4. <br/>
	 * 
	 * How many bag colors can eventually contain at least one shiny gold bag? (The
	 * list of rules is quite long; make sure you get all of it.)
	 */
	@Override
	public Integer part1() {
		final List<LuggageRule> rules = LuggageRule.parse(getLines());
		final LuggageRule goldBag = LuggageRule.find(rules, "shiny gold");
		return goldBag.getCountContainedBy(rules, goldBag.getDescription());
	}

	/**
	 * --- Part Two ---
	 * 
	 * It's getting pretty expensive to fly these days - not because of ticket
	 * prices, but because of the ridiculous number of bags you need to buy!
	 * 
	 * Consider again your shiny gold bag and the rules from the above example:
	 * 
	 * faded blue bags contain 0 other bags. <br/>
	 * dotted black bags contain 0 other bags. <br/>
	 * vibrant plum bags contain 11 other bags: 5 faded blue bags and 6 dotted black
	 * bags. <br/>
	 * dark olive bags contain 7 other bags: 3 faded blue bags and 4 dotted black
	 * bags. <br/>
	 * 
	 * So, a single shiny gold bag must contain 1 dark olive bag (and the 7 bags
	 * within it) plus 2 vibrant plum bags (and the 11 bags within each of those): 1
	 * + 1*7 + 2 + 2*11 = 32 bags!
	 * 
	 * Of course, the actual rules have a small chance of going several levels
	 * deeper than this example; be sure to count all of the bags, even if the
	 * nesting becomes topologically impractical!
	 * 
	 * Here's another example:
	 * 
	 * shiny gold bags contain 2 dark red bags. <br/>
	 * dark red bags contain 2 dark orange bags. <br/>
	 * dark orange bags contain 2 dark yellow bags. <br/>
	 * dark yellow bags contain 2 dark green bags. <br/>
	 * dark green bags contain 2 dark blue bags. <br/>
	 * dark blue bags contain 2 dark violet bags. <br/>
	 * dark violet bags contain no other bags. <br/>
	 * 
	 * In this example, a single shiny gold bag must contain 126 other bags.
	 * 
	 * How many individual bags are required inside your single shiny gold bag?
	 */
	@Override
	public Integer part2() {
		final List<LuggageRule> rules = LuggageRule.parse(getLines());
		final LuggageRule goldBag = LuggageRule.find(rules, "shiny gold");
		return goldBag.getCountContains();
	}

}
