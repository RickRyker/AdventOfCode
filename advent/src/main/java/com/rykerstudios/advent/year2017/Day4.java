package com.rykerstudios.advent.year2017;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.rykerstudios.advent.Day;

public class Day4 extends Day<Integer> {

	public Day4() {
		super(0, 0);
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
		final Long n = 609043L;
		final String hash = createHash(key, n);
		if (hash.startsWith("00000")) {
			// return value!
		}
		return 0;
	}

	public Long findHash(final String key) {
		for (long n = 0; n < 1000000L; n++) {
			final String hash = createHash(key, n);
			if (hash.startsWith("00000")) {
				return n;
			}
		}
		return null;
	}

	private String createHash(final String key, final Long n) {
		try {
			String hash = createHash(key + Long.toString(n));
			if (hash.length() < 32) {
				hash = "0"+hash;
			}
			return hash;
		} catch (final NoSuchAlgorithmException e) {
			// ignore
		}
		return "";
	}

	private String createHash(final String text) throws NoSuchAlgorithmException {
		final MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(StandardCharsets.UTF_8.encode(text));
		return String.format("%032x", new BigInteger(1, md5.digest()));
	}

	/*
	 */
	@Override
	public Integer part2() {
		return 0;
	}

}
