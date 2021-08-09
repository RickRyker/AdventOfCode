package com.rykerstudios.advent.year2020;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;
import com.rykerstudios.advent.year2020.Day21.AllergenAssessment;

@RunWith(JUnitPlatform.class)
public class TestDay21 extends TestDay<Day21> {

	@Override
	public Day21 createDay() {
		return new Day21();
	}

	final String[] EXAMPLE1 = new String[] {
		"mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
		"trh fvjkl sbzzf mxmxvkd (contains dairy)",
		"sqjhc fvjkl (contains soy)",
		"sqjhc mxmxvkd sbzzf (contains fish)"
	};

	@Test
	public void testPart1_example1() {
		final AllergenAssessment assessment = new AllergenAssessment(EXAMPLE1);
		final List<String> safeIngredients = assessment.getSafeIngredients();
		Assertions.assertEquals(5, safeIngredients.size());
	}

	@Test
	public void testPart2_example1() {
		final AllergenAssessment assessment = new AllergenAssessment(EXAMPLE1);
		final String dangerousIngredients = assessment.getCanonicalDangerousIngredients();
		final String expected = "mxmxvkd,sqjhc,fvjkl";
		Assertions.assertEquals(expected, dangerousIngredients);
	}

	@Override
	@Test
	public void testPart2() {
		final AllergenAssessment assessment = new AllergenAssessment(createDay().getLines());
		final String dangerousIngredients = assessment.getCanonicalDangerousIngredients();
		final String expected = "xncgqbcp,frkmp,qhqs,qnhjhn,dhsnxr,rzrktx,ntflq,lgnhmx";
		Assertions.assertEquals(expected, dangerousIngredients);
	}

}
