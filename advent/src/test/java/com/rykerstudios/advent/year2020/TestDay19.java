package com.rykerstudios.advent.year2020;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;
import com.rykerstudios.advent.year2020.Day19.MessageRule;
import com.rykerstudios.advent.year2020.Day19.MonsterMessages;

@RunWith(JUnitPlatform.class)
public class TestDay19 extends TestDay<Day19> {

	@Override
	public Day19 createDay() {
		return new Day19();
	}

	@Override
	@Test
	public void testPart1() {
		super.testPart1();
	}

	@Override
	@Test
	public void testPart2() {
		super.testPart2();
	}

	private String[] EXAMPLE1 = new String[] {
		"0: 1 2",
		"1: \"a\"",
		"2: 1 3 | 3 1",
		"3: \"b\"",
		"",
		"aab",
		"aba",
		"baa"
	};

	@Test
	public void testExample1() {
		final MonsterMessages messageRules = new MonsterMessages(EXAMPLE1, false);
		final MessageRule rule = messageRules.getRule("0");
		Assertions.assertTrue(rule.isExactMatch("aab"));
		Assertions.assertTrue(rule.isExactMatch("aba"));
		Assertions.assertFalse(rule.isExactMatch("baa"));
		Assertions.assertEquals(2, messageRules.match("0").size());
	}

	private String[] EXAMPLE2 = new String[] { 
		 "0: 4 1 5",
		 "1: 2 3 | 3 2",
		 "2: 4 4 | 5 5",
		 "3: 4 5 | 5 4",
		 "4: \"a\"",
		 "5: \"b\"",
		 "",
		 "aaaabb",
		 "aaabab",
		 "aaabbb",
		 "aabaab",
		 "aabbbb",
		 "abaaab",
		 "ababbb",
		 "abbabb",
		 "abbbab",
		 "bababa",
		 "aaaabbb"
	};

	@Test
	public void testExample2() {
		final MonsterMessages messageRules = new MonsterMessages(EXAMPLE2, false);
		final MessageRule rule = messageRules.getRule("0");
		Assertions.assertTrue(rule.isExactMatch("aaaabb"));
		Assertions.assertTrue(rule.isExactMatch("aaabab"));
		Assertions.assertTrue(rule.isExactMatch("aabaab"));
		Assertions.assertTrue(rule.isExactMatch("aabbbb"));
		Assertions.assertTrue(rule.isExactMatch("abaaab"));
		Assertions.assertTrue(rule.isExactMatch("ababbb"));
		Assertions.assertTrue(rule.isExactMatch("abbabb"));
		Assertions.assertTrue(rule.isExactMatch("abbbab"));
		Assertions.assertFalse(rule.isExactMatch("bababa"));
		Assertions.assertFalse(rule.isExactMatch("aaaabbb"));
	}

	final static String[] PART2_EXAMPLE = new String[] {
			"42: 9 14 | 10 1",
			"9: 14 27 | 1 26",
			"10: 23 14 | 28 1",
			"1: \"a\"",
			"11: 42 31",
			"5: 1 14 | 15 1",
			"19: 14 1 | 14 14",
			"12: 24 14 | 19 1",
			"16: 15 1 | 14 14",
			"31: 14 17 | 1 13",
			"6: 14 14 | 1 14",
			"2: 1 24 | 14 4",
			"0: 8 11",
			"13: 14 3 | 1 12",
			"15: 1 | 14",
			"17: 14 2 | 1 7",
			"23: 25 1 | 22 14",
			"28: 16 1",
			"4: 1 1",
			"20: 14 14 | 1 15",
			"3: 5 14 | 16 1",
			"27: 1 6 | 14 18",
			"14: \"b\"",
			"21: 14 1 | 1 14",
			"25: 1 1 | 1 14",
			"22: 14 14",
			"8: 42",
			"26: 14 22 | 1 20",
			"18: 15 15",
			"7: 14 5 | 1 21",
			"24: 14 1",
			"",
			"abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa",
			"bbabbbbaabaabba",
			"babbbbaabbbbbabbbbbbaabaaabaaa",
			"aaabbbbbbaaaabaababaabababbabaaabbababababaaa",
			"bbbbbbbaaaabbbbaaabbabaaa",
			"bbbababbbbaaaaaaaabbababaaababaabab",
			"ababaaaaaabaaab",
			"ababaaaaabbbaba",
			"baabbaaaabbaaaababbaababb",
			"abbbbabbbbaaaababbbbbbaaaababb",
			"aaaaabbaabaaaaababaa",
			"aaaabbaaaabbaaa",
			"aaaabbaabbaaaaaaabbbabbbaaabbaabaaa",
			"babaaabbbaaabaababbaabababaaab",
			"aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba" };

	@Test
	public void testPart2Example_withoutLoops() {
		final MonsterMessages messageRules = new MonsterMessages(PART2_EXAMPLE, false);
		final MessageRule rule = messageRules.getRule("0");
		Assertions.assertTrue(rule.isExactMatch("bbabbbbaabaabba"));
		Assertions.assertTrue(rule.isExactMatch("ababaaaaaabaaab"));
		Assertions.assertTrue(rule.isExactMatch("ababaaaaabbbaba"));
		Assertions.assertEquals(3, messageRules.match("0").size());
	}

	@Test
	public void testPart2Example_withLoops() {
		final MonsterMessages messageRules = new MonsterMessages(PART2_EXAMPLE, true);
		final MessageRule rule = messageRules.getRule("0");
		Assertions.assertTrue(rule.isExactMatch("bbabbbbaabaabba"));
		Assertions.assertTrue(rule.isExactMatch("ababaaaaaabaaab"));
		Assertions.assertTrue(rule.isExactMatch("ababaaaaabbbaba"));
		Assertions.assertTrue(rule.isExactMatch("baabbaaaabbaaaababbaababb"));
		Assertions.assertTrue(rule.isExactMatch("aaaabbaabbaaaaaaabbbabbbaaabbaabaaa"));
		Assertions.assertTrue(rule.isExactMatch("aaabbbbbbaaaabaababaabababbabaaabbababababaaa"));

		final Collection<String> matches = messageRules.match("0");
		System.out.println(matches);
		Assertions.assertTrue(rule.isExactMatch("aaaaabbaabaaaaababaa"));
		Assertions.assertTrue(rule.isExactMatch("bbbbbbbaaaabbbbaaabbabaaa"));
		Assertions.assertTrue(rule.isExactMatch("abbbbabbbbaaaababbbbbbaaaababb"));
		Assertions.assertTrue(rule.isExactMatch("babbbbaabbbbbabbbbbbaabaaabaaa"));
		Assertions.assertTrue(rule.isExactMatch("bbbababbbbaaaaaaaabbababaaababaabab"));
		Assertions.assertTrue(rule.isExactMatch("aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba"));

		Assertions.assertEquals(12, messageRules.match("0").size());
	}

}
