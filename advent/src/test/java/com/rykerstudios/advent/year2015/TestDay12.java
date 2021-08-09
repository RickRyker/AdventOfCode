package com.rykerstudios.advent.year2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay12 extends TestDay<Day12> {

	@Override
	public Day12 createDay() {
		return new Day12();
	}

	@Test
	public void testRemoveRed_whenInArray_thenDoNothing() {
		assertEquals("{[1,\"red\",5]}", day.removeObjectsWithRedValues("{[1,\"red\",5]}"));
	}

	@Test
	public void testRemoveRed_whenInObject_thenRemove() {
		assertEquals("[1,,3]", day.removeObjectsWithRedValues("[1,{\"c\":\"red\",\"b\":2},3]"));
	}

	@Test
	public void testRemoveRed_whenInObject_thenRemoveJustObject() {
		assertEquals("", day.removeObjectsWithRedValues("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}"));
	}

	@Test
	public void testRemoveRed_whenInObject_thenRemoveEmbeddedObject() {
		assertEquals("[1,,3]",
				day.removeObjectsWithRedValues("[1,{\"c\":\"red\",\"b\":{\"c\":{\"d\":4}},\"orange\":[\"green\"]},3]"));
	}

	@Test
	public void testSumTheNumbers() {
		assertEquals(Integer.valueOf(9), day.sumTheNumbers("1,2,3,4,5,-6"));
	}

}