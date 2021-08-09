package com.rykerstudios.advent.year2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LuggageRule {

	private final String description;
	private final Map<String, Integer> rules = new HashMap<>();
	private final Map<LuggageRule, Integer> contains = new HashMap<>();
	private final List<LuggageRule> containedBy = new ArrayList<>();

	public LuggageRule(final String line) {
		// This would be easier using Regex to get repeated groups.
		final String[] parts = line
				.replace("bags", "")
				.replace("bag", "")
				.replace(".", "")
				.split("contain");
		this.description = parts[0].trim();
		if (!parts[1].contains("no other")) {
			final String[] bagParts = parts[1].trim().split(",");
			for (final String bagPart : bagParts) {
				final String[] rulePart = bagPart.trim().split(" ");
				if (rulePart[0].trim().length() > 0) {
					rules.put(rulePart[1] + " " + rulePart[2], Integer.parseInt(rulePart[0]));
				}
			}
		}
	}

	public int getCountContainedBy(final List<LuggageRule> rules, final String bagDescription) {
		int count = 0;
		for (final LuggageRule rule : rules) {
			if (isContainedBy(rule.getDescription())) {
				count++;
			}
		}
		return count;
	}

	public int getCountContains() {
		int count = 0;
		for (final Map.Entry<LuggageRule, Integer> entry : contains.entrySet()) {
			count += entry.getValue();
			count += entry.getValue() * entry.getKey().getCountContains();
		}
		return count;
	}

	public String getDescription() {
		return description;
	}

	public Map<String, Integer> getRules() {
		return rules;
	}

	public boolean isContainedBy(final String bagDescription) {
		for (final LuggageRule rule : containedBy) {
			if (rule.getDescription().equals(bagDescription)) {
				return true;
			}
			if (rule.isContainedBy(bagDescription)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("{description:'").append(this.description).append("'");
		sb.append(",containedBy:'").append(this.containedBy).append("'");
		sb.append("}");
		return sb.toString();
	}

	public static LuggageRule find(final List<LuggageRule> rules, final String bagDescription) {
		for (final LuggageRule rule : rules) {
			if (rule.getDescription().equals(bagDescription)) {
				return rule;
			}
		}
		return null;
	}

	public static List<LuggageRule> parse(final String[] lines) {
		final Map<String, LuggageRule> lookup = new HashMap<>();
		final List<LuggageRule> rules = new ArrayList<LuggageRule>();
		for (final String line : lines) {
			final LuggageRule luggageRule = new LuggageRule(line);
			rules.add(luggageRule);
			lookup.put(luggageRule.getDescription(), luggageRule);
		}
		for (final LuggageRule container : lookup.values()) {
			for (final String contained : container.getRules().keySet()) {
				final LuggageRule containedLuggage = lookup.get(contained);
				containedLuggage.containedBy.add(container);
				container.contains.put(containedLuggage, container.getRules().get(contained));
			}
		}
		return rules;
	}

}
