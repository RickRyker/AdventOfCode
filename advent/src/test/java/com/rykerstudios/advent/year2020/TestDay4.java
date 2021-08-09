package com.rykerstudios.advent.year2020;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;

@RunWith(JUnitPlatform.class)
public class TestDay4 extends TestDay<Day4> {

	@Override
	public Day4 createDay() {
		return new Day4();
	}

	@Test
	public void testSample1() {
		final String[] sample1 = {
				"ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
				"byr:1937 iyr:2017 cid:147 hgt:183cm" };
		final List<Passport> passports = Passport.parse(sample1);
		Assertions.assertEquals(1, passports.size());
		final Passport passport = passports.get(0);
		Assertions.assertEquals(8, passport.getFieldValues().size());
		Assertions.assertTrue(passport.isValid());
	}

	@Test
	public void testSample2() {
		final String[] sample2 = {
				"iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
				"hcl:#cfa07d byr:1929" };
		final List<Passport> passports = Passport.parse(sample2);
		Assertions.assertEquals(1, passports.size());
		final Passport passport = passports.get(0);
		Assertions.assertEquals(7, passport.getFieldValues().size());
		Assertions.assertFalse(passport.isValid());
	}

	@Test
	public void testSample3() {
		final String[] sample3 = {
				"hcl:#ae17e1 iyr:2013",
				"eyr:2024",
				"ecl:brn pid:760753108 byr:1931",
				"hgt:179cm" };
		final List<Passport> passports = Passport.parse(sample3);
		Assertions.assertEquals(1, passports.size());
		final Passport passport = passports.get(0);
		Assertions.assertEquals(7, passport.getFieldValues().size());
		Assertions.assertTrue(passport.isValid());
	}

	@Test
	public void testSample4() {
		final String[] sample4 = {
				"hcl:#cfa07d eyr:2025 pid:166559648",
				"iyr:2011 ecl:brn hgt:59in" };
		final List<Passport> passports = Passport.parse(sample4);
		Assertions.assertEquals(1, passports.size());
		final Passport passport = passports.get(0);
		Assertions.assertEquals(6, passport.getFieldValues().size());
		Assertions.assertFalse(passport.isValid());
	}

}
