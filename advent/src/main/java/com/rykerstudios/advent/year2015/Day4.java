package com.rykerstudios.advent.year2015;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import com.rykerstudios.advent.Day;

public class Day4 extends Day<Integer> {

	public Day4() {
		super(282749, 9962624);
	}

	private String createHashWithLength32(final String text) {
		String hash = getDigestAsString(text);
		if (hash.length() < 32) {
			hash = "0" + hash;
		}
		return hash;
	}

	/*
	 * Find MD5 hashes which, in hexadecimal, that start with at least five zeroes.
	 */
	public Integer findAcceptableHash(final String key, final String prefix) {
		for (int n = 0; n < Integer.MAX_VALUE; n++) {
			final String text = key + Long.toString(n);
			final String hash = createHashWithLength32(text);
			if (hash.startsWith(prefix)) {
				System.out.println(hash);
				return n;
			}
		}
		return null;
	}

	public String getDigestAsString(final String text) {
		try {
			final MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			final byte[] digest = md.digest();
			final String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
			return hash;
		} catch (final NoSuchAlgorithmException e) {
			throw new RuntimeException("Argh!", e);
		}
	}

	/*
	 * --- Day 4: The Ideal Stocking Stuffer ---
	 * 
	 * Santa needs help mining some AdventCoins (very similar to bitcoins) to use as
	 * gifts for all the economically forward-thinking little girls and boys.
	 * 
	 * To do this, he needs to find MD5 hashes which, in hexadecimal, start with at
	 * least five zeroes. The input to the MD5 hash is some secret key (your puzzle
	 * input, given below) followed by a number in decimal. To mine AdventCoins, you
	 * must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...)
	 * that produces such a hash.
	 * 
	 * For example:
	 * 
	 * If your secret key is abcdef, the answer is 609043, because the MD5 hash of
	 * abcdef609043 starts with five zeroes (000001dbbfa...), and it is the lowest
	 * such number to do so. If your secret key is pqrstuv, the lowest number it
	 * combines with to make an MD5 hash starting with five zeroes is 1048970; that
	 * is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....
	 * 
	 * Your puzzle input is yzbqklnj.
	 */
	@Override
	public Integer part1() {
		final String key = "yzbqklnj";
		return findAcceptableHash(key, "00000");
	}

	/*
	 * --- Part Two ---
	 * 
	 * Now find one that starts with six zeroes.
	 */
	@Override
	public Integer part2() {
		final String key = "yzbqklnj";
		return findAcceptableHash(key, "000000");
	}

}
