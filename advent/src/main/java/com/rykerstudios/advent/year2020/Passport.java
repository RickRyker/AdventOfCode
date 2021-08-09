package com.rykerstudios.advent.year2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Passport {

	private Map<PassportField, String> fieldValues = new HashMap<PassportField, String>();

	public Passport() {
	}

	public Map<PassportField, String> getFieldValues() {
		return fieldValues;
	}

	private int getNumber(final String s) {
		try {
			return Integer.parseInt(s);
		} catch (final NumberFormatException e) {
			return 0;
		}
	}

	public boolean hasCountryId() {
		final String passportId = getFieldValues().get(PassportField.cid);
		return passportId != null;
	}

	public boolean isValid() {
		final int fieldCount = getFieldValues().size();
		return ((fieldCount == 8) || (!hasCountryId() && fieldCount == 7));
	}

	public boolean isValidStrict() {
		if (!isValid()) {
			return false;
		}
		final int birthYear = getNumber(getFieldValues().get(PassportField.byr));
		if (birthYear < 1920 || birthYear > 2002) {
			return false;
		}
		final int issuedYear = getNumber(getFieldValues().get(PassportField.iyr));
		if (issuedYear < 2010 || issuedYear > 2020) {
			return false;
		}
		final int expireYear = getNumber(getFieldValues().get(PassportField.eyr));
		if (expireYear < 2020 || expireYear > 2030) {
			return false;
		}
		final String height = getFieldValues().get(PassportField.hgt);
		final int heightValue = getNumber(height.substring(0, height.length() - 2));
		final String heightUnits = height.substring(height.length() - 2);
		if (heightUnits.equals("cm")) {
			if (heightValue < 150 || heightValue > 193) {
				return false;
			}
		} else if (heightUnits.equals("in")) {
			if (heightValue < 59 || heightValue > 76) {
				return false;
			}
		} else {
			return false;
		}
		final String hairColor = getFieldValues().get(PassportField.hcl);
		if (!hairColor.matches("^#[0-9a-f]{6}$")) {
			return false;
		}
		final String eyeColor = getFieldValues().get(PassportField.ecl);
		try {
			if (PassportEyeColor.valueOf(eyeColor) == null) {
				return false;
			}
		} catch (final IllegalArgumentException e) {
			return false;
		}
		final String passportId = getFieldValues().get(PassportField.pid);
		if (!passportId.matches("^\\d{9}$")) {
			return false;
		}
		return true;
	}

	public void parseLine(final String line) {
		final String fieldDataArray[] = line.split(" ");
		for (final String fieldData : fieldDataArray) {
			final String[] fieldParts = fieldData.split(":");
			getFieldValues().put(PassportField.valueOf(fieldParts[0]), fieldParts[1]);
		}
	}

	public static List<Passport> parse(final String[] lines) {
		final List<Passport> passports = new ArrayList<Passport>();
		Passport passport = null;
		for (final String line : lines) {
			if (line.length() == 0) {
				passport = null;
				continue;
			}
			if (passport == null) {
				passport = new Passport();
				passports.add(passport);
			}
			passport.parseLine(line);
		}
		return passports;
	}

}
