package com.rykerstudios.advent.year2015;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay11 extends TestDay<Day11> {

	@Override
	public Day11 createDay() {
		return new Day11();
	}

	@Test
	public void testAllCharactersValid_whenAA_thenTrue() {
		assertTrue(day.allCharactersAllowed("aa"));
	}

	@Test
	public void testAllCharactersValid_whenABCDEFGHIJKMNPQRSTUVWXYZ_thenFalse() {
		assertFalse(day.allCharactersAllowed("abcdefghijkmnpqrstuvwxyz"));
	}

	@Test
	public void testAllCharactersValid_whenABCDEFGHJKLMNPQRSTUVWXYZ_thenFalse() {
		assertFalse(day.allCharactersAllowed("abcdefghjklmnpqrstuvwxyz"));
	}

	@Test
	public void testAllCharactersValid_whenABCDEFGHJKMNOPQRSTUVWXYZ_thenFalse() {
		assertFalse(day.allCharactersAllowed("abcdefghjkmnopqrstuvwxyz"));
	}

	@Test
	public void testAllCharactersValid_whenABCDEFGHJKMNPQRSTUVWXYZ_thenTrue() {
		assertTrue(day.allCharactersAllowed("abcdefghjkmnpqrstuvwxyz"));
	}

	@Test
	public void testAllCharactersValid_whenIJ_thenFalse() {
		assertFalse(day.allCharactersAllowed("ij"));
	}

	@Test
	public void testAllCharactersValid_whenLM_thenFalse() {
		assertFalse(day.allCharactersAllowed("lm"));
	}

	@Test
	public void testAllCharactersValid_whenNO_thenFalse() {
		assertFalse(day.allCharactersAllowed("no"));
	}

	@Test
	public void testAllCharactersValid_whenOIL_thenFalse() {
		assertFalse(day.allCharactersAllowed("boiling"));
	}

	@Test
	public void testHasTwoDoubledCharacters_whenAAZZ() {
		assertTrue(day.hasTwoDoubledCharacters("aazz"));
	}

	@Test
	public void testHasStraight_whenABC() {
		assertTrue(day.hasStraight("abc"));
	}

	@Test
	public void testHasStraight_whenSNOPE() {
		assertTrue(day.hasStraight("snope"));
	}

	@Test
	public void testNext_whenAAA_thenZZZ() {
		assertEquals("aaa", day.next("zzz"));
	}

	@Test
	public void testNext_whenXX_thenXY() {
		assertEquals("xy", day.next("xx"));
	}

	@Test
	public void testNext_whenXXZ_thenXYA() {
		assertEquals("xya", day.next("xxz"));
	}

	@Test
	public void testNext_whenXY_thenXZ() {
		assertEquals("xz", day.next("xy"));
	}

	@Test
	public void testNext_whenXZ_thenYA() {
		assertEquals("ya", day.next("xz"));
	}

	@Test
	public void testNext_whenYA_thenYB() {
		assertEquals("yb", day.next("ya"));
	}

	@Test
	public void testNext_whenYAA_thenXZZ() {
		assertEquals("yaa", day.next("xzz"));
	}

	@Test
	public void testValid_whenABBCEFFG_thenFalse() {
		assertFalse(day.valid("abbceffg"));
	}

	@Test
	public void testValid_whenABBCEGJK_thenFalse() {
		assertFalse(day.valid("abbcegjk"));
	}

	@Test
	public void testValid_whenABCDFFAA_thenTrue() {
		assertTrue(day.valid("abcdffaa"));
	}

	@Test
	public void testValid_whenGHJAABCC_thenTrue() {
		assertTrue(day.valid("ghjaabcc"));
	}

	@Test
	public void testValid_whenHIJKLMN_thenFalse() {
		assertFalse(day.valid("hijklmn"));
	}

}