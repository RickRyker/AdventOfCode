package com.rykerstudios.advent.year2020;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay7 extends TestDay<Day7> {

	@Override
	public Day7 createDay() {
		return new Day7();
	}

	@Test
	public void testContainedBy() {
		final String[] lines = new String[] {
				"light red bags contain 1 bright white bag, 2 muted yellow bags.", 
				"dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
				"bright white bags contain 1 shiny gold bag.",
				"muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.", 
				"shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.", 
				"dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
				"vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.", 
				"faded blue bags contain no other bags.",
				"dotted black bags contain no other bags." };
		final List<LuggageRule> rules = LuggageRule.parse(lines);
		Assertions.assertEquals(9, rules.size());
		final LuggageRule goldBag = LuggageRule.find(rules, "shiny gold");
		final int count = goldBag.getCountContainedBy(rules, goldBag.getDescription());
		Assertions.assertEquals(4, count);
	}

	@Test
	public void testContainsExample1() {
		final String[] lines = new String[] {
				"light red bags contain 1 bright white bag, 2 muted yellow bags.",
				"dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
				"bright white bags contain 1 shiny gold bag.",
				"muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
				"shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
				"dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
				"vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
				"faded blue bags contain no other bags.", "dotted black bags contain no other bags." };
		final List<LuggageRule> rules = LuggageRule.parse(lines);
		final LuggageRule goldBag = LuggageRule.find(rules, "shiny gold");
		final int count = goldBag.getCountContains();
		Assertions.assertEquals(32, count);
	}

	@Test
	public void testContainsExample2() {
		final String[] lines = new String[] {
				"shiny gold bags contain 2 dark red bags.",
				"dark red bags contain 2 dark orange bags.",
				"dark orange bags contain 2 dark yellow bags.",
				"dark yellow bags contain 2 dark green bags.",
				"dark green bags contain 2 dark blue bags.",
				"dark blue bags contain 2 dark violet bags.",
				"dark violet bags contain no other bags." };
		final List<LuggageRule> rules = LuggageRule.parse(lines);
		final LuggageRule goldBag = LuggageRule.find(rules, "shiny gold");
		final int count = goldBag.getCountContains();
		Assertions.assertEquals(126, count);
	}

}
