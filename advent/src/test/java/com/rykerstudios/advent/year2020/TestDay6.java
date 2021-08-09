package com.rykerstudios.advent.year2020;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay6 extends TestDay<Day6> {

	@Override
	public Day6 createDay() {
		return new Day6();
	}

	@Test
	public void testGroup1() {
		final List<CustomsForm> forms = CustomsForm.parse(new String[] { "abcx", "abcy", "abcz" });
		Assertions.assertEquals(6, forms.get(0).getAnswersCount());
		Assertions.assertEquals(3, forms.get(0).getAllSameCount());
	}

	@Test
	public void testGroup2() {
		final List<CustomsForm> forms = CustomsForm.parse(new String[] { "abc" });
		Assertions.assertEquals(3, forms.get(0).getAnswersCount());
		Assertions.assertEquals(3, forms.get(0).getAllSameCount());
	}

	@Test
	public void testGroup3() {
		final List<CustomsForm> forms = CustomsForm.parse(new String[] { "a", "b", "c" });
		Assertions.assertEquals(3, forms.get(0).getAnswersCount());
		Assertions.assertEquals(0, forms.get(0).getAllSameCount());
	}

	@Test
	public void testGroup4() {
		final List<CustomsForm> forms = CustomsForm.parse(new String[] { "ab", "ac" });
		Assertions.assertEquals(3, forms.get(0).getAnswersCount());
		Assertions.assertEquals(1, forms.get(0).getAllSameCount());
	}

	@Test
	public void testGroup5() {
		final List<CustomsForm> forms = CustomsForm.parse(new String[] { "a", "a", "a", "a" });
		Assertions.assertEquals(1, forms.get(0).getAnswersCount());
		Assertions.assertEquals(1, forms.get(0).getAllSameCount());
	}

	@Test
	public void testGroup6() {
		final List<CustomsForm> forms = CustomsForm.parse(new String[] { "b" });
		Assertions.assertEquals(1, forms.get(0).getAnswersCount());
		Assertions.assertEquals(1, forms.get(0).getAllSameCount());
	}

}
