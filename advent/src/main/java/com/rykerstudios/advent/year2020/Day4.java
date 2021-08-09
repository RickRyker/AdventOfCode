package com.rykerstudios.advent.year2020;

import java.util.List;

import com.rykerstudios.advent.Day;

public class Day4 extends Day<Integer> {

	public Day4() {
		super(204, 0);
	}

	/**
	 * --- Day 4: Passport Processing ---
	 * 
	 * You arrive at the airport only to realize that you grabbed your North Pole
	 * Credentials instead of your passport. While these documents are extremely
	 * similar, North Pole Credentials aren't issued by a country and therefore
	 * aren't actually valid documentation for travel in most of the world.
	 * 
	 * It seems like you're not the only one having problems, though; a very long
	 * line has formed for the automatic passport scanners, and the delay could
	 * upset your travel itinerary.
	 * 
	 * Due to some questionable network security, you realize you might be able to
	 * solve both of these problems at the same time.
	 * 
	 * The automatic passport scanners are slow because they're having trouble
	 * detecting which passports have all required fields. The expected fields are
	 * as follows:
	 * 
	 * byr (Birth Year) <br/>
	 * iyr (Issue Year) <br/>
	 * eyr (Expiration Year) <br/>
	 * hgt (Height) <br/>
	 * hcl (Hair Color) <br/>
	 * ecl (Eye Color) <br/>
	 * pid (Passport ID) <br/>
	 * cid (Country ID) <br/>
	 * 
	 * Passport data is validated in batch files (your puzzle input). Each passport
	 * is represented as a sequence of key:value pairs separated by spaces or
	 * newlines. Passports are separated by blank lines.
	 * 
	 * Here is an example batch file containing four passports:
	 * 
	 * ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147
	 * hgt:183cm
	 * 
	 * iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929
	 * 
	 * hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm
	 * 
	 * hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in
	 * 
	 * The first passport is valid - all eight fields are present.
	 * 
	 * The second passport is invalid - it is missing hgt (the Height field).
	 * 
	 * The third passport is interesting; the only missing field is cid, so it looks
	 * like data from North Pole Credentials, not a passport at all! Surely, nobody
	 * would mind if you made the system temporarily ignore missing cid fields.
	 * Treat this "passport" as valid.
	 * 
	 * The fourth passport is missing two fields, cid and byr. Missing cid is fine,
	 * but missing any other field is not, so this passport is invalid.
	 * 
	 * According to the above rules, your improved system would report 2 valid
	 * passports.
	 * 
	 * Count the number of valid passports - those that have all required fields.
	 * Treat cid as optional. In your batch file, how many passports are valid?
	 */
	@Override
	public Integer part1() {
		int count = 0;
		final List<Passport> passports = Passport.parse(getLines());
		for (final Passport passport : passports) {
			if (passport.isValid()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * --- Part Two ---
	 * 
	 * The line is moving more quickly now, but you overhear airport security
	 * talking about how passports with invalid data are getting through. Better add
	 * some data validation, quick!
	 * 
	 * You can continue to ignore the cid field, but each other field has strict
	 * rules about what values are valid for automatic validation:
	 * 
	 * byr (Birth Year) - four digits; at least 1920 and at most 2002. <br/>
	 * iyr (Issue Year) - four digits; at least 2010 and at most 2020. <br/>
	 * eyr (Expiration Year) - four digits; at least 2020 and at most 2030. <br/>
	 * hgt (Height) - a number followed by either cm or in: <br/>
	 * If cm, the number must be at least 150 and at most 193. <br/>
	 * If in, the number must be at least 59 and at most 76. <br/>
	 * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f. <br/>
	 * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth. <br/>
	 * pid (Passport ID) - a nine-digit number, including leading zeroes. <br/>
	 * cid (Country ID) - ignored, missing or not. <br/>
	 * 
	 * Your job is to count the passports where all required fields are both present
	 * and valid according to the above rules. Here are some example values:
	 * 
	 * byr valid: 2002 <br/>
	 * byr invalid: 2003 <br/>
	 * 
	 * hgt valid: 60in <br/>
	 * hgt valid: 190cm <br/>
	 * hgt invalid: 190in <br/>
	 * hgt invalid: 190 <br/>
	 * 
	 * hcl valid: #123abc <br/>
	 * hcl invalid: #123abz <br/>
	 * hcl invalid: 123abc <br/>
	 * 
	 * ecl valid: brn <br/>
	 * ecl invalid: wat <br/>
	 * 
	 * pid valid: 000000001 <br/>
	 * pid invalid: 0123456789 <br/>
	 * Here are some invalid passports: <br/>
	 * 
	 * eyr:1972 cid:100 <br/>
	 * hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926 <br/>
	 * 
	 * iyr:2019 <br/>
	 * hcl:#602927 eyr:1967 hgt:170cm <br/>
	 * ecl:grn pid:012533040 byr:1946 <br/>
	 * 
	 * hcl:dab227 iyr:2012 <br/>
	 * ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277 <br/>
	 * 
	 * hgt:59cm ecl:zzz <br/>
	 * eyr:2038 hcl:74454a iyr:2023 <br/>
	 * pid:3556412378 byr:2007 <br/>
	 * 
	 * Here are some valid passports:
	 * 
	 * pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980 <br/>
	 * hcl:#623a2f <br/>
	 * 
	 * eyr:2029 ecl:blu cid:129 byr:1989 <br/>
	 * iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm <br/>
	 * 
	 * hcl:#888785 <br/>
	 * hgt:164cm byr:2001 iyr:2015 cid:88 <br/>
	 * pid:545766238 ecl:hzl <br/>
	 * eyr:2022 <br/>
	 * 
	 * iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719 <br/>
	 * 
	 * Count the number of valid passports - those that have all required fields and
	 * valid values. Continue to treat cid as optional. In your batch file, how many
	 * passports are valid?
	 */
	@Override
	public Integer part2() {
		int count = 0;
		final List<Passport> passports = Passport.parse(getLines());
		for (final Passport passport : passports) {
			if (passport.isValidStrict()) {
				count++;
			}
		}
		return count;
	}

}
